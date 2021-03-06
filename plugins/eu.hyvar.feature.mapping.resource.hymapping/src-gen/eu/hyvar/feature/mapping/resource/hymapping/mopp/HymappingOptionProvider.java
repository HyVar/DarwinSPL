/**
 * <copyright>
 * </copyright>
 *
 * 
 */
/**
 * This class can be used to configure options that must be used when resources
 * are loaded. This is similar to using the extension point
 * 'eu.hyvar.feature.mapping.resource.hymapping.default_load_options' with the
 * difference that the options defined in this class are used even if no Eclipse
 * platform is running.
 */
package eu.hyvar.feature.mapping.resource.hymapping.mopp;

import java.util.Collections;
import java.util.Map;

public class HymappingOptionProvider implements eu.hyvar.feature.mapping.resource.hymapping.IHymappingOptionProvider {
	
	public Map<?,?> getOptions() {
		// create a map with static option providers here
		return Collections.emptyMap();
	}
	
}
