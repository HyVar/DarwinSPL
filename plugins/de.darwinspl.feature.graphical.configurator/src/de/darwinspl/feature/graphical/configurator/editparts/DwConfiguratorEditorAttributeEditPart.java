package de.darwinspl.feature.graphical.configurator.editparts;

import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;

import de.darwinspl.feature.graphical.base.editor.DwGraphicalFeatureModelViewer;
import de.darwinspl.feature.graphical.base.editparts.DwAttributeEditPart;
import de.darwinspl.feature.graphical.base.model.DwFeatureModelWrapped;
import de.darwinspl.feature.graphical.configurator.editor.DwFeatureModelConfiguratorEditor;
import de.darwinspl.feature.graphical.configurator.util.DwConfiguratorEditorUtil;
import eu.hyvar.dataValues.HyBooleanValue;
import eu.hyvar.dataValues.HyDataValuesFactory;
import eu.hyvar.dataValues.HyEnumValue;
import eu.hyvar.dataValues.HyNumberValue;
import eu.hyvar.dataValues.HyStringValue;
import eu.hyvar.dataValues.HyValue;
import eu.hyvar.feature.HyBooleanAttribute;
import eu.hyvar.feature.HyEnumAttribute;
import eu.hyvar.feature.HyFeatureAttribute;
import eu.hyvar.feature.HyNumberAttribute;
import eu.hyvar.feature.HyStringAttribute;
import eu.hyvar.feature.configuration.HyAttributeValueAssignment;
import eu.hyvar.feature.configuration.HyConfiguration;
import eu.hyvar.feature.configuration.HyConfigurationElement;
import eu.hyvar.feature.configuration.HyFeatureSelected;

public class DwConfiguratorEditorAttributeEditPart extends DwAttributeEditPart {
	HyValue attributeValue = null;

	IInputValidator numberValidator = new IInputValidator() {

		@Override
		public String isValid(String newText) {
			try {
				int newInt = Integer.parseInt(newText);
				return null;

			} catch (Exception e) {

				return "Enter a valid number";

			}

		}
	};

	IInputValidator booleanValidator = new IInputValidator() {

		@Override
		public String isValid(String newText) {
			if (newText.equals("false") || newText.equals("true")) {
				return null;
			}
			return "Enter a 'false' or 'true'";

		}
	};

	public DwConfiguratorEditorAttributeEditPart(DwGraphicalFeatureModelViewer editor,
			DwFeatureModelWrapped featureModel) {
		super(editor, featureModel);
	}

	@Override
	public void performRequest(Request request) {
		// React to double click
		if (request.getType() == RequestConstants.REQ_OPEN) {
			
			
			HyFeatureAttribute attribute = (HyFeatureAttribute) getModel();

			DwFeatureModelConfiguratorEditor editor = (DwFeatureModelConfiguratorEditor) getEditor();
			HyConfiguration configuration = editor.getSelectedConfiguration();
			
			boolean isFeatureSelected = false;
			for(HyConfigurationElement configElement: configuration.getElements()){
				if(configElement instanceof HyFeatureSelected){
					if(((HyFeatureSelected) configElement).getSelectedFeature().equals(attribute.getFeature())){
						isFeatureSelected = true;
						break;
					}
				}
			}
			
			if(isFeatureSelected== false){
				return;
			}
			

			InputDialog dialog;
			String initialValue= "";

			if (attribute instanceof HyNumberAttribute) {
				HyAttributeValueAssignment assignment = DwConfiguratorEditorUtil.getValueAssignmentForFeatureAttribute(configuration, attribute);
				if(assignment!=null){
					initialValue = Integer.toString(((HyNumberValue) assignment.getValue()).getValue());
				}
				dialog = new InputDialog(this.getViewer().getControl().getShell(), "Specify Attribute Value",
						"Please give the attribute a value:", initialValue, numberValidator);
				

			} else if (attribute instanceof HyBooleanAttribute) {
				HyAttributeValueAssignment assignment = DwConfiguratorEditorUtil.getValueAssignmentForFeatureAttribute(configuration, attribute);
				if(assignment!=null){
					initialValue = Boolean.toString(((HyBooleanValue) assignment.getValue()).isValue());
				}

				dialog = new InputDialog(this.getViewer().getControl().getShell(), "Specify Attribute Value",
						"Please give the attribute a value:", initialValue, booleanValidator);
			} else if(attribute instanceof HyStringAttribute){
				HyAttributeValueAssignment assignment = DwConfiguratorEditorUtil.getValueAssignmentForFeatureAttribute(configuration, attribute);
				if(assignment!=null){
					initialValue = ((HyStringValue) assignment.getValue()).getValue();
				}
				dialog = new InputDialog(this.getViewer().getControl().getShell(), "Specify Attribute Value",
						"Please give the attribute a value:", initialValue, null);
			} else{
				HyAttributeValueAssignment assignment = DwConfiguratorEditorUtil.getValueAssignmentForFeatureAttribute(configuration, attribute);
				if(assignment!=null){
					initialValue = Integer.toString(((HyEnumValue) assignment.getValue()).getEnumLiteral().getValue());
				}
				dialog = new InputDialog(this.getViewer().getControl().getShell(), "Specify Attribute Value",
						"Please give the attribute a value:", initialValue, null);
			}

			if (dialog.open() == InputDialog.OK) {

				String dialogValue = dialog.getValue();
				if (attribute instanceof HyBooleanAttribute) {

					attributeValue = HyDataValuesFactory.eINSTANCE.createHyBooleanValue();
					if (dialogValue.equals("false")) {
						((HyBooleanValue) attributeValue).setValue(false);
					} else {
						((HyBooleanValue) attributeValue).setValue(true);
					}
				} else if (attribute instanceof HyNumberAttribute) {
					attributeValue = HyDataValuesFactory.eINSTANCE.createHyNumberValue();
					int intValue = Integer.parseInt(dialogValue);
					((HyNumberValue) attributeValue).setValue(intValue);
				} else if (attribute instanceof HyStringAttribute) {
					attributeValue = HyDataValuesFactory.eINSTANCE.createHyStringValue();
					((HyStringValue) attributeValue).setValue(dialogValue);
				} else if (attribute instanceof HyEnumAttribute){
					//TODO: Needs to be implemented - a different dialog with the possibility to select valid enum literals needs to be provided
				}

				if (attributeValue != null) {
					DwConfiguratorEditorUtil.changeValueAssignmentOfAttribute(configuration, attribute, attributeValue);

				}

				editor.refreshView();
			}
		}
	}
}
