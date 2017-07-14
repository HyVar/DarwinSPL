package de.darwinspl.feature.graphical.configurator.widgets;

import java.util.Date;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import de.darwinspl.feature.graphical.configurator.dialogs.DwFeatureSelectionDialog;
import eu.hyvar.feature.HyFeature;
import eu.hyvar.feature.HyFeatureModel;

/**
 * 
 * abstract class for fature model attribute criterias
 * 
 * @author Jeremias Wrensch
 *
 */
public abstract class DwAbstractMultiCriteriaComposite extends DwAbstractCriteriaComposite implements DwCriteriaComposite {

	private List<HyFeature> selectedFeatures;
	private Button selectFeaturesButton;
	
	public DwAbstractMultiCriteriaComposite(Composite parent, int style, HyFeatureModel featureModel, Date date) {
		super(parent, style);
		init(featureModel, date);
	}

	public DwAbstractMultiCriteriaComposite(String attributeName, Composite parent, int style,
			HyFeatureModel featureModel, Date date) {
		super(attributeName, parent, style);
		init(featureModel, date);
	}

	private void init(HyFeatureModel featureModel, Date date) {
		selectedFeatures = featureModel.getFeatures();

		selectFeaturesButton = new Button(this, SWT.NONE);
		selectFeaturesButton.setText("Select Features");
		selectFeaturesButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				DwFeatureSelectionDialog dialog = new DwFeatureSelectionDialog(getShell(), selectedFeatures,
						featureModel, date);
				if (dialog.open() == Dialog.OK) {
					selectedFeatures = dialog.getSelectedFeatureNames();
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

	}

	public List<HyFeature> getSelectedFeatures() {
		return selectedFeatures;
	}

}