package de.darwinspl.importer.ui.wizards;


import java.io.File;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;

public class FileSelectionWizardPage extends WizardPage implements SelectionListener {

	protected int getSelectionType() {
		return SWT.SINGLE;
	}
	
	protected String modelName;
	protected Composite container;
	
	protected FileDialog fileDialog;
	
	protected IResource selectedResource;
	
	/**
	 * 
	 * @param pageName
	 * @param description
	 * @param fileExtensionFilter
	 *            Set the file extensions which the dialog will use to filter
	 *            the files it shows to the argument, which may be null. The
	 *            strings are platform specific. For example, on some platforms,
	 *            an extension filter string is typically of the form
	 *            "*.extension", where "*.*" matches all files. For filters with
	 *            multiple extensions, use semicolon as a separator, e.g.
	 *            "*.jpg;*.png". Note: On Mac, setting the file extension filter
	 *            affects how app bundles are treated by the dialog. When a
	 *            filter extension having the app extension (.app) is selected,
	 *            bundles are treated as files. For all other extension filters,
	 *            bundles are treated as directories. When no filter extension
	 *            is set, bundles are treated as files.
	 */
	public FileSelectionWizardPage(String pageName, String description, String[] fileExtensionFilter, String modelName, IResource selectedResource) {
		super(pageName);
		setDescription(description);
		this.fileExtensionFilter = fileExtensionFilter;
		
		this.modelName = modelName;
		this.selectedResource = selectedResource;
	}

	protected Label selectedSourceFileLabel;
//	protected Text sourceFilePathText;
	protected Button selectFileButton;
	protected String[] fileExtensionFilter;
	private String selectedSourceFile;

	@Override
	public void createControl(Composite parent) {
		
		container = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();
        container.setLayout(layout);
        layout.numColumns = 1;
		
		
		selectedSourceFileLabel = new Label(container, SWT.LEFT);
		selectedSourceFileLabel.setText(modelName+" selected: None");

//		sourceFilePathText = new Text(container, SWT.BORDER | SWT.SINGLE);

		selectFileButton = new Button(container, SWT.PUSH);
		selectFileButton.setText("Select File...");
		selectFileButton.addSelectionListener(this);
		
		setControl(container);
		// required to avoid an error in the system
		setPageComplete(false);
		
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		if (e.getSource() == selectFileButton) {
			fileDialog = new FileDialog(getShell(), getSelectionType());
			if(fileExtensionFilter != null && fileExtensionFilter.length > 0) {
				fileDialog.setFilterExtensions(fileExtensionFilter);				
			}
			if(selectedResource != null) {
				String selectedFilePath = "";
				
				if(selectedResource instanceof IFile) {
					selectedFilePath = selectedResource.getParent().getLocation().toString();
				}
				else {
					selectedFilePath = selectedResource.getLocation().toString();
				}
				
				fileDialog.setFilterPath(selectedFilePath);
			}
			
			if(fileDialog.open()!=null) {
				setSelectedFileText();
				this.selectedSourceFile = fileDialog.getFilterPath()+File.separator+fileDialog.getFileName();
				setPageComplete(true);				
			}
		}
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

	}

	public String getSelectedFilePath() {
		return selectedSourceFile;
	}
	
	protected void setSelectedFileText() {
		selectedSourceFileLabel.setText(modelName+ " selected: "+ fileDialog.getFileName());
	}

}
