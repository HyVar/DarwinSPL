package de.darwinspl.anomalies.explanations;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import de.darwinspl.feature.evolution.editoroperation.EditorOperationExplanation;
import eu.hyvar.context.contextValidity.HyValidityFormula;
import eu.hyvar.evolution.util.HyEvolutionUtil;
import eu.hyvar.feature.HyFeature;
import eu.hyvar.feature.HyGroup;
import eu.hyvar.feature.HyGroupComposition;
import eu.hyvar.feature.HyRootFeature;
import eu.hyvar.feature.constraint.HyConstraint;
import eu.hyvar.feature.util.HyFeatureEvolutionUtil;

public class AnomalyConstraintExplanation {
	
	private enum EditorOperationType {
		EVOLUTION,
		CAUSING
	}

	private EObject objReference; // TODO: rename properly
	private String stringReference; // TODO: rename properly
	private Map<EditorOperationType, List<EditorOperationExplanation>> editorOperationExplanations = new HashMap<EditorOperationType, List<EditorOperationExplanation>>();
	private Date date;

	public String explain() {
		String constraintStringExplanation = "";
		if (objReference instanceof HyRootFeature) {
			HyRootFeature rootFeature = (HyRootFeature) objReference;
			constraintStringExplanation = explain(rootFeature);
		} else if (objReference instanceof HyFeature) {
			HyFeature feature = (HyFeature) objReference;
			constraintStringExplanation = explain(feature);
		} else if (objReference instanceof HyConstraint) {
			HyConstraint constraint = (HyConstraint) objReference;
			constraintStringExplanation = explain(constraint);
		} else if (objReference instanceof HyValidityFormula) {
			HyValidityFormula validityFormula = (HyValidityFormula) objReference;
			constraintStringExplanation = explain(validityFormula);
		} else if (objReference instanceof HyGroup) {
			HyGroup group = (HyGroup) objReference;
			constraintStringExplanation = explain(group);
		}

		String editorOperationExplanation = "";
		if (editorOperationExplanations.size() > 0) {
			for (EditorOperationType type : editorOperationExplanations.keySet()) {
				for (EditorOperationExplanation opExplanation : editorOperationExplanations.get(type)) {
					if (!editorOperationExplanation.isEmpty()) {
						editorOperationExplanation += "\n";
					}
					editorOperationExplanation += type.name() + " - " + opExplanation.explain();
				}
			}
			editorOperationExplanation = "\n>Editor Operations:\n" + editorOperationExplanation;
		}
		if (constraintStringExplanation.isEmpty()) {
			return stringReference;
		} else {
			return stringReference + " -> " + constraintStringExplanation + editorOperationExplanation;
		}
	}

	private String explain(HyRootFeature rootFeature) {
		String rootFeatureName = HyEvolutionUtil.getValidTemporalElement(rootFeature.getFeature().getNames(), date).getName();
		return rootFeatureName + " is root feature.";
	}

	private String explain(HyFeature feature) {
		// TODO HyFeature may stand for: version constraints, parent -> mandatory, feature = 0 if invalidFeature or invalidVersionOfValidFeature
		String featureName = HyEvolutionUtil.getValidTemporalElement(feature.getNames(), date).getName();
		return featureName + " is mandatory.";
	}

	private String explain(HyConstraint constraint) {
		return "constraint";
	}

	private String explain(HyValidityFormula validityFormula) {
		return "validity formula";
	}

	private String explain(HyGroup group) {
		// TODO HyGroup may mean: f1 or f2 ... -> parent, parent -> f1 alt/and/or f2 ... , feature that is optional+hasnochild -> parent
		String featurenames = "";
		List<HyFeature> featureList = HyFeatureEvolutionUtil.getChildsOfGroup(group, date);
		for (HyFeature feature : featureList) {
			if (!featurenames.isEmpty()) {
				featurenames += ", ";
			}
			featurenames += HyEvolutionUtil.getValidTemporalElement(feature.getNames(), date).getName();
		}

		HyFeature parentFeature = HyFeatureEvolutionUtil.getParentOfGroup(group, date);
		String parentName = HyEvolutionUtil.getValidTemporalElement(parentFeature.getNames(), date).getName();

		String groupType = HyFeatureEvolutionUtil.getType(group, date).getType().getName();

		String beWord = (featureList.size() > 1) ? "are" : "is";
		return featurenames + " " + beWord + " in an " + groupType + "-group under " + parentName;
	}

	public EObject getObjReference() {
		return objReference;
	}

	public void setObjReference(EObject objReference) {
		this.objReference = objReference;
	}

	public String getStringReference() {
		return stringReference;
	}

	public void setStringReference(String stringReference) {
		this.stringReference = stringReference;
	}
	
	public void addEvolutionEditorOperations(EditorOperationExplanation editorOperation) {
		if (!editorOperationExplanations.containsKey(EditorOperationType.EVOLUTION)) {
			editorOperationExplanations.put(EditorOperationType.EVOLUTION, new ArrayList<EditorOperationExplanation>());
		}
		editorOperationExplanations.get(EditorOperationType.EVOLUTION).add(editorOperation);
	}
	
	public void addCausingEditorOperations(EditorOperationExplanation editorOperation) {
		if (!editorOperationExplanations.containsKey(EditorOperationType.CAUSING)) {
			editorOperationExplanations.put(EditorOperationType.CAUSING, new ArrayList<EditorOperationExplanation>());
		}
		editorOperationExplanations.get(EditorOperationType.CAUSING).add(editorOperation);
	}
	
	public List<EditorOperationExplanation> getEvolutionEditorOperations() {
		if (editorOperationExplanations.containsKey(EditorOperationType.EVOLUTION)) {
			return editorOperationExplanations.get(EditorOperationType.EVOLUTION);
		}
		return new ArrayList<EditorOperationExplanation>();
	}
	
	public List<EditorOperationExplanation> getCausingEditorOperations() {
		if (editorOperationExplanations.containsKey(EditorOperationType.CAUSING)) {
			return editorOperationExplanations.get(EditorOperationType.CAUSING);
		}
		return new ArrayList<EditorOperationExplanation>();
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return date;
	}
}
