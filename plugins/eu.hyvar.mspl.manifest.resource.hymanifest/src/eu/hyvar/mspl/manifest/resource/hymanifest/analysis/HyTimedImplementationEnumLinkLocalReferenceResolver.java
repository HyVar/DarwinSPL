/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package eu.hyvar.mspl.manifest.resource.hymanifest.analysis;

import java.util.Map;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EReference;

import eu.hyvar.dataValues.HyEnum;
import eu.hyvar.evolution.HyInterval;
import eu.hyvar.evolution.util.HyEvolutionUtil;
import eu.hyvar.feature.HyFeature;
import eu.hyvar.feature.expression.util.HyExpressionResolverUtil;

public class HyTimedImplementationEnumLinkLocalReferenceResolver implements eu.hyvar.mspl.manifest.resource.hymanifest.IHymanifestReferenceResolver<eu.hyvar.mspl.manifest.HyTimedImplementationEnumLink, eu.hyvar.dataValues.HyEnum> {
	
	private eu.hyvar.mspl.manifest.resource.hymanifest.analysis.HymanifestDefaultResolverDelegate<eu.hyvar.mspl.manifest.HyTimedImplementationEnumLink, eu.hyvar.dataValues.HyEnum> delegate = new eu.hyvar.mspl.manifest.resource.hymanifest.analysis.HymanifestDefaultResolverDelegate<eu.hyvar.mspl.manifest.HyTimedImplementationEnumLink, eu.hyvar.dataValues.HyEnum>();
	
	public void resolve(String identifier, eu.hyvar.mspl.manifest.HyTimedImplementationEnumLink container, EReference reference, int position, boolean resolveFuzzy, final eu.hyvar.mspl.manifest.resource.hymanifest.IHymanifestReferenceResolveResult<eu.hyvar.dataValues.HyEnum> result) {
		//System.out.println("UNITO DEBUG EnumLinkLocal: identifier= "+identifier);

		HyEnum elementEnum = HyExpressionResolverUtil.resolveEnum(identifier, container);
		if(elementEnum!=null) {

			EList<eu.hyvar.evolution.HyTemporalElement> elements = new BasicEList<eu.hyvar.evolution.HyTemporalElement>();
			elements.add(container);
			elements.add((eu.hyvar.evolution.HyTemporalElement)container.eContainer());
			HyInterval interval = HyEvolutionUtil.computeTemporalIntersection(elements);
			
			if(HyEvolutionUtil.isWithinValidityOf(interval, elementEnum)) {
				//System.out.println("UNITO DEBUG EnumLinkLocal: enum= "+elementEnum);

				result.addMapping(identifier, elementEnum);
			}
		}

		//delegate.resolve(identifier, container, reference, position, resolveFuzzy, result);
	}
	
	public String deResolve(eu.hyvar.dataValues.HyEnum element, eu.hyvar.mspl.manifest.HyTimedImplementationEnumLink container, EReference reference) {
		return delegate.deResolve(element, container, reference);
	}
	
	public void setOptions(Map<?,?> options) {
		// save options in a field or leave method empty if this resolver does not depend
		// on any option
	}
	
}
