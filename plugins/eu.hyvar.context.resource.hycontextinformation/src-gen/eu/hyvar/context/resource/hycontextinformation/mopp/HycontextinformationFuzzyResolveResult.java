/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package eu.hyvar.context.resource.hycontextinformation.mopp;

import java.util.Collection;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;

/**
 * <p>
 * A FuzzyResolveResult is an implementation of the
 * IHycontextinformationReferenceResolveResult interface that delegates all method
 * calls to a given IHycontextinformationReferenceResolveResult with ReferenceType
 * EObject. It is used by reference resolver switches to collect results from
 * different reference resolvers in a type safe manner.
 * </p>
 * 
 * @param <ReferenceType> the type of the reference that is resolved
 */
public class HycontextinformationFuzzyResolveResult<ReferenceType extends EObject> implements eu.hyvar.context.resource.hycontextinformation.IHycontextinformationReferenceResolveResult<ReferenceType> {
	
	private eu.hyvar.context.resource.hycontextinformation.IHycontextinformationReferenceResolveResult<EObject> delegate;
	
	public HycontextinformationFuzzyResolveResult(eu.hyvar.context.resource.hycontextinformation.IHycontextinformationReferenceResolveResult<EObject> delegate) {
		this.delegate = delegate;
	}
	
	public String getErrorMessage() {
		return delegate.getErrorMessage();
	}
	
	public Collection<eu.hyvar.context.resource.hycontextinformation.IHycontextinformationReferenceMapping<ReferenceType>> getMappings() {
		return null;
	}
	
	public boolean wasResolved() {
		return delegate.wasResolved();
	}
	
	public boolean wasResolvedMultiple() {
		return delegate.wasResolvedMultiple();
	}
	
	public boolean wasResolvedUniquely() {
		return delegate.wasResolvedUniquely();
	}
	
	public void setErrorMessage(String message) {
		delegate.setErrorMessage(message);
	}
	
	public void addMapping(String identifier, ReferenceType target) {
		delegate.addMapping(identifier, (EObject) target);
	}
	
	public void addMapping(String identifier, URI uri) {
		delegate.addMapping(identifier, uri);
	}
	
	public void addMapping(String identifier, ReferenceType target, String warning) {
		delegate.addMapping(identifier, (EObject) target, warning);
	}
	
	public void addMapping(String identifier, URI uri, String warning) {
		delegate.addMapping(identifier, uri, warning);
	}
	
	public Collection<eu.hyvar.context.resource.hycontextinformation.IHycontextinformationQuickFix> getQuickFixes() {
		return delegate.getQuickFixes();
	}
	
	public void addQuickFix(eu.hyvar.context.resource.hycontextinformation.IHycontextinformationQuickFix quickFix) {
		delegate.addQuickFix(quickFix);
	}
	
}
