package eu.hyvar.feature.graphical.editor.actions.group;

import org.eclipse.gef.Request;
import org.eclipse.gef.ui.actions.SelectionAction;

import eu.hyvar.feature.HyGroup;
import eu.hyvar.feature.HyGroupTypeEnum;
import eu.hyvar.feature.graphical.base.editor.GraphicalFeatureModelEditor;
import eu.hyvar.feature.graphical.base.editparts.HyGroupEditPart;
import eu.hyvar.feature.graphical.base.model.HyGroupWrapped;
import eu.hyvar.feature.graphical.editor.commands.HyGroupChangeGroupTypeCommand;

public abstract class HyGroupChangeGroupTypeAction extends SelectionAction{
	protected GraphicalFeatureModelEditor editor;
	
	protected Request request;
	
	private HyGroupChangeGroupTypeCommand command;
	
	public HyGroupChangeGroupTypeAction(GraphicalFeatureModelEditor editor) {
		super(editor);
		
		this.editor = editor;
	}


	@Override
	protected boolean calculateEnabled() {
		for(Object o : getSelectedObjects()){
			if(!(o instanceof HyGroupEditPart))
				return false;
		}		
		
		return true;
	}
	
	public abstract HyGroupTypeEnum getNewGroupType();
	
	@Override
	public void run(){
		for(Object o : getSelectedObjects()){
			HyGroupEditPart editpart = (HyGroupEditPart)o;
			HyGroup group = ((HyGroupWrapped)editpart.getModel()).getWrappedModelElement();
			
			command = new HyGroupChangeGroupTypeCommand(group, getNewGroupType(), editor);
			//command.execute();
			
			editor.executeCommand(command);
		}		
	}
}
