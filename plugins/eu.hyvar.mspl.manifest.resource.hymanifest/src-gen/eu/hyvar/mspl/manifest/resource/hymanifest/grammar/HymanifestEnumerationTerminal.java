/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package eu.hyvar.mspl.manifest.resource.hymanifest.grammar;

import java.util.LinkedHashMap;
import java.util.Map;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * A class to represent an enumeration terminal in the grammar.
 */
public class HymanifestEnumerationTerminal extends eu.hyvar.mspl.manifest.resource.hymanifest.grammar.HymanifestTerminal {
	
	private Map<String, String> mapping = new LinkedHashMap<String, String>();
	
	public HymanifestEnumerationTerminal(EStructuralFeature attribute, String[] literalMappings, eu.hyvar.mspl.manifest.resource.hymanifest.grammar.HymanifestCardinality cardinality, int mandatoryOccurrencesAfter) {
		super(attribute, cardinality, mandatoryOccurrencesAfter);
		assert attribute instanceof EAttribute;
		assert literalMappings.length % 2 == 0;
		for (int i = 0; i < literalMappings.length; i += 2) {
			String literalName = literalMappings[i];
			String text = literalMappings[i + 1];
			this.mapping.put(literalName, text);
		}
	}
	
	public Map<String, String> getLiteralMapping() {
		return this.mapping;
	}
	
	public EAttribute getAttribute() {
		return (EAttribute) getFeature();
	}
	
	public String getText(String literalName) {
		return this.mapping.get(literalName);
	}
	
}
