/**
 * 
 */
package de.darwinspl.feature.evolution.basic.operations;

import java.util.Date;

import de.darwinspl.feature.evolution.Invoker.EvolutionOperation;
import de.darwinspl.feature.evolution.atomic.operations.AddFeatureType;
import de.darwinspl.feature.evolution.atomic.operations.DeleteFeatureType;
import eu.hyvar.feature.HyFeature;
import eu.hyvar.feature.HyFeatureType;
import eu.hyvar.feature.HyFeatureTypeEnum;

/**
 *
 */
public class ChangeFeatureType extends ComplexOperation {

	private HyFeature feature;
	private HyFeatureTypeEnum type;
	private Date timestamp;
	
	private HyFeatureType oldFeatureType, newFeatureType;
	
	public ChangeFeatureType(HyFeature feature, HyFeatureTypeEnum type, Date timestamp) {
		
		this.feature = feature;
		this.type = type;
		this.timestamp = timestamp;
		
	}
	/* (non-Javadoc)
	 * @see de.darwinspl.feature.evolution.basic.operations.ComplexOperation#execute()
	 */
	@Override
	public void execute() {
		
		//get the valid featureType object
		for (HyFeatureType featureType : feature.getTypes()) {
			if (featureType.getValidUntil() == null) {
				this.oldFeatureType = featureType;
			}
		}

		DeleteFeatureType deleteFeatureType = new DeleteFeatureType(oldFeatureType, timestamp);
		AddFeatureType addFeatureType = new AddFeatureType(type, feature, timestamp);
		
		addToComposition(deleteFeatureType);
		addToComposition(addFeatureType);
		
		for (EvolutionOperation evolutionOperation : evoOps) {
			evolutionOperation.execute();
		}
		
		newFeatureType = addFeatureType.getFeatureTyp();
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
	public HyFeatureType getOldFeatureType() {
		return oldFeatureType;
	}
	public HyFeatureType getNewFeatureType() {
		return newFeatureType;
	}

}
