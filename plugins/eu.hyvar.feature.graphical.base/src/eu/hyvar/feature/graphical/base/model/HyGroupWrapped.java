package eu.hyvar.feature.graphical.base.model;

import java.util.Date;
import java.util.HashSet;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import eu.hyvar.evolution.HyEvolutionUtil;
import eu.hyvar.feature.HyFeature;
import eu.hyvar.feature.HyFeatureFactory;
import eu.hyvar.feature.HyGroup;
import eu.hyvar.feature.HyGroupComposition;
import eu.hyvar.feature.HyGroupType;
import eu.hyvar.feature.HyGroupTypeEnum;

public class HyGroupWrapped extends HyEditorChangeableElement {
	/*
	 * Group related properties
	 */
	public final static String PROPERTY_CHILD_FEATURES = "PropertyChildFeatures";

	private HashSet<HyFeatureWrapped> features;

	public boolean isAnd(Date date){
		HyGroupType type = HyEvolutionUtil.getValidTemporalElement(getWrappedModelElement().getTypes(), date);

		return type.getType() == HyGroupTypeEnum.AND;		
	}

	public boolean isOr(Date date){
		HyGroupType type = HyEvolutionUtil.getValidTemporalElement(getWrappedModelElement().getTypes(), date);

		return type.getType() == HyGroupTypeEnum.OR;			
	}

	public boolean isAlternative(Date date){
		HyGroupType type = HyEvolutionUtil.getValidTemporalElement(getWrappedModelElement().getTypes(), date);

		return type.getType() == HyGroupTypeEnum.ALTERNATIVE;			
	}

	@Override 
	public HyGroup getWrappedModelElement(){
		return (HyGroup)wrappedModelElement;
	}

	/*
	public HyFeatureWrapped getParentFeature(Date date) {
		return parentFeature;
	}
	 */
	/*
	public void setParentFeature(HyFeatureWrapped parentFeature) {
		this.parentFeature = parentFeature;
	}
	 */
	public void setFeatures(HashSet<HyFeatureWrapped> features) {
		this.features = features;
	}

	public HyGroupWrapped(EObject wrappedModelElement) {
		super(wrappedModelElement);

		features = new HashSet<HyFeatureWrapped>();
	}

	public HyGroupComposition getComposition(Date date){
		return HyEvolutionUtil.getValidTemporalElement(this.getWrappedModelElement().getParentOf(), date);

	}
	public EList<HyFeature> getFeatures(Date date){
		if(date == null){
			if(this.getWrappedModelElement().getParentOf().size() > 0)
				return this.getWrappedModelElement().getParentOf().get(0).getFeatures();
			else 
				return null;
		}

		HyGroupComposition composition = getComposition(date);
		if(composition == null)
			return new BasicEList<HyFeature>();
		else
			return composition.getFeatures();
	}
	public HashSet<HyFeatureWrapped> getFeatures() {
		return features;
	}	
	public HashSet<HyFeatureWrapped> getFeaturesWrapped(Date date) {
		HashSet<HyFeatureWrapped> set = new HashSet<HyFeatureWrapped> ();
		for(HyFeatureWrapped featureWrapped : features){
			if(featureWrapped.isValid(date))
				set.add(featureWrapped);
		}

		return set;
	}


	/**
	 * This function is only needed to display the relation between a group and its features. 
	 * Be aware that the feature will not be added to the model with this function.
	 * @param childFeature
	 */
	public void addChildFeature(HyFeatureWrapped childFeature){
		HyGroupWrapped old = clone();
		HyGroupComposition composition = getWrappedModelElement().getParentOf().get(0);

		childFeature.getWrappedModelElement().getGroupMembership().add(composition);
		childFeature.setParent(this);

		if(features.add(childFeature)){
			listeners.firePropertyChange(PROPERTY_CHILD_FEATURES, old, this);
		}else{
		}
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	public HyGroupWrapped clone(){
		HyGroupWrapped deepCopy = new HyGroupWrapped(EcoreUtil.copy(this.getWrappedModelElement()));
		deepCopy.setFeatures(getFeatures());
		//deepCopy.setParentFeature(parentFeature);

		return deepCopy;
	}

	/**
	 * Removes a feature which is child of this group. Use this function to delete the feature temporarily since the selected date.
	 * @param childFeature the feature to remove
	 * @param date
	 */
	public void removeChildFeature(HyFeatureWrapped childFeature, Date date){
		if(date != null && date.equals(new Date(Long.MIN_VALUE)))
			date = null;

		HyGroupWrapped old = clone();

		HyGroupComposition composition = HyEvolutionUtil.getValidTemporalElement(getWrappedModelElement().getParentOf(), date);			
		if(composition != null){
			HyGroupType type = HyEvolutionUtil.getValidTemporalElement(getWrappedModelElement().getTypes(), date);
			
			// split composition to allow evolution
			if(date != null){
				composition = HyGroupWrapped.splitComposition(composition, null, date);

				// split group types in case that no and type is selected at the selected date
				if(composition.getFeatures().size() == 2 && type.getType() != HyGroupTypeEnum.AND){
					splitGroupType(date, HyGroupTypeEnum.AND);
				}
			}else{	
				if(composition.getFeatures().size() == 2)
					type.setType(HyGroupTypeEnum.AND);
			}
			composition.getFeatures().remove(childFeature.getWrappedModelElement());

			// Inform editparts about the changes made to the model
			listeners.firePropertyChange(PROPERTY_CHILD_FEATURES, old, this);
		}
	}

	public HyGroup getGroup() {
		return this.getWrappedModelElement();
		/*
		if(features.iterator().next().getWrappedModelElement().getGroupMembership().size() == 0){
			return null;
		}

		return features.iterator().next().getWrappedModelElement().getGroupMembership().get(0).getCompositionOf();
		 */
	}

	/**
	 * Transfers a feature from this group to another. Use this function only to update the internal relationship
	 * from the model. Be aware that this function does not cause a rerender and because of that you won't be able
	 * to see a change in the editor. If you want to update the editor view use instead the function @addChildFeature.
	 * @param wrappedFeature
	 * @param anotherGroup
	 */
	public void transferFeatureToAnotherGroup(HyFeatureWrapped wrappedFeature, HyGroupWrapped anotherGroup){
		anotherGroup.addChildFeature(wrappedFeature);

		HyFeature feature = wrappedFeature.getWrappedModelElement();
		feature.getGroupMembership().clear();

		HyGroup group = anotherGroup.getWrappedModelElement();
		group.getParentOf().get(0).getFeatures().add(feature);

		getWrappedModelElement().getParentOf().get(0).getFeatures().remove(feature);
	}

	/**
	 * Split the group type at a given date. The new group type is set with the variable @param newTypeEnum.
	 * @param date
	 * @param newTypeEnum
	 */
	protected void splitGroupType(Date date, HyGroupTypeEnum newTypeEnum){
		HyGroupType type = HyEvolutionUtil.getValidTemporalElement(getWrappedModelElement().getTypes(), date);
		HyGroupType newType = HyFeatureFactory.eINSTANCE.createHyGroupType();
		newType.setValidSince(date);
		newType.setType(newTypeEnum);
		type.setValidUntil(date);

		if(type.getValidSince() != null &&
				type.getValidUntil() != null &&
				type.getValidSince().equals(type.getValidUntil())){
			getWrappedModelElement().getTypes().remove(type);
		}

		getWrappedModelElement().getTypes().add(newType);
	}

	public static HyGroupComposition splitComposition(HyGroupComposition composition, HyFeatureWrapped feature, Date date){
		HyGroup group = composition.getCompositionOf();

		// copy the composition in order to replace the old composition since selected date
		HyGroupComposition newComposition = HyFeatureFactory.eINSTANCE.createHyGroupComposition();
		newComposition.setCompositionOf(composition.getCompositionOf());
		newComposition.setSupersededElement(composition.getSupersededElement());
		newComposition.setSupersedingElement(composition.getSupersedingElement());


		// update validation of old composition (until) and new composition (since) selected date
		composition.setValidUntil(date);
		newComposition.setValidSince(date);

		for(HyFeature f : composition.getFeatures()){
			if(feature != null){
				if(!f.equals(feature.getWrappedModelElement())){
					newComposition.getFeatures().add(f);
					f.getGroupMembership().add(newComposition);
				}
			}else{
				newComposition.getFeatures().add(f);
				f.getGroupMembership().add(newComposition);
			}			
		}

		group.getParentOf().add(newComposition);

		removeUnlogicalComposition(composition);

		return newComposition;

	}

	private static void removeUnlogicalComposition(HyGroupComposition composition){
		if(composition.getValidSince() != null && 
				composition.getValidUntil() != null &&
				composition.getValidSince().equals(composition.getValidUntil())){

			composition.getFeatures().clear();

			composition.getCompositionOf().getParentOf().remove(composition);
		}		
	}


}
