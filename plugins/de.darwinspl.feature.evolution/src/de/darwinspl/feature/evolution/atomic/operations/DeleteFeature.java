/**
 * 
 */
package de.darwinspl.feature.evolution.atomic.operations;

import java.util.Date;

import de.darwinspl.feature.evolution.Invoker.EvolutionOperation;
import de.darwinspl.feature.evolution.basic.operations.ComplexOperation;
import eu.hyvar.evolution.HyName;
import eu.hyvar.feature.HyFeature;
import eu.hyvar.feature.HyFeatureType;
import eu.hyvar.feature.HyGroupComposition;

/**
 *
 */
public class DeleteFeature extends ComplexOperation {

	private HyFeature feature;
	
	private HyFeatureType featureType;
	private HyName name;
	
	public DeleteFeature(HyFeature feature, Date timestamp) {
		
		this.feature = feature;
		this.timestamp = timestamp;
		
	}
	/* (non-Javadoc)
	 * @see de.darwinspl.feature.evolution.basic.operations.ComplexOperation#execute()
	 */
	@Override
	public void execute() {
		
		//Iterate through the feature types and name list of the feature and find the element where no until is that. This element must by the current valid type or name
		for (HyFeatureType type : feature.getTypes()) {
			if (type.getValidUntil() == null) {
				featureType = type; 
				break;
			}
		}
		for (HyName name : feature.getNames()) {
			if (name.getValidUntil() == null) {
				this.name = name;
				break;
			}
		}
		
		DeleteName deleteName = new DeleteName(name, timestamp);
		DeleteFeatureType deleteFeatureType = new DeleteFeatureType(featureType, timestamp);
		addToComposition(deleteName);
		addToComposition(deleteFeatureType);
	
		for (EvolutionOperation evolutionOperation : evoOps) {
			evolutionOperation.execute();
		}
		//set valid until from feature to the timestamp
		feature.setValidUntil(timestamp);

	}

	/* (non-Javadoc)
	 * @see de.darwinspl.feature.evolution.basic.operations.ComplexOperation#undo()
	 */
	@Override
	public void undo() {
		// TODO Auto-generated method stub

	}
	
	//Getter
	public HyFeature getFeature() {
		return feature;
	}
	public HyFeatureType getFeatureType() {
		return featureType;
	}
	public HyName getName() {
		return name;
	}

}
