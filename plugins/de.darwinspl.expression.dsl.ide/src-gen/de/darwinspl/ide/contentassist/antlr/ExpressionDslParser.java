/*
 * generated by Xtext 2.12.0
 */
package de.darwinspl.ide.contentassist.antlr;

import com.google.inject.Inject;
import de.darwinspl.ide.contentassist.antlr.internal.InternalExpressionDslParser;
import de.darwinspl.services.ExpressionDslGrammarAccess;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.ide.editor.contentassist.antlr.AbstractContentAssistParser;

public class ExpressionDslParser extends AbstractContentAssistParser {

	@Inject
	private ExpressionDslGrammarAccess grammarAccess;

	private Map<AbstractElement, String> nameMappings;

	@Override
	protected InternalExpressionDslParser createParser() {
		InternalExpressionDslParser result = new InternalExpressionDslParser(null);
		result.setGrammarAccess(grammarAccess);
		return result;
	}

	@Override
	protected String getRuleName(AbstractElement element) {
		if (nameMappings == null) {
			nameMappings = new HashMap<AbstractElement, String>() {
				private static final long serialVersionUID = 1L;
				{
					put(grammarAccess.getTerminalExpressionAccess().getAlternatives(), "rule__TerminalExpression__Alternatives");
					put(grammarAccess.getHyVersionRestrictionAccess().getAlternatives(), "rule__HyVersionRestriction__Alternatives");
					put(grammarAccess.getHyVersionRangeRestrictionAccess().getAlternatives(), "rule__HyVersionRangeRestriction__Alternatives");
					put(grammarAccess.getHyBooleanValueExpressionAccess().getAlternatives_1(), "rule__HyBooleanValueExpression__Alternatives_1");
					put(grammarAccess.getHyBinaryArithmeticalComparisonExpressionAccess().getAlternatives(), "rule__HyBinaryArithmeticalComparisonExpression__Alternatives");
					put(grammarAccess.getHyValueAccess().getAlternatives(), "rule__HyValue__Alternatives");
					put(grammarAccess.getHyBooleanValueAccess().getAlternatives_1(), "rule__HyBooleanValue__Alternatives_1");
					put(grammarAccess.getHyRelativeVersionRestrictionOperatorAccess().getAlternatives(), "rule__HyRelativeVersionRestrictionOperator__Alternatives");
					put(grammarAccess.getHyImpliesExpressionAccess().getGroup(), "rule__HyImpliesExpression__Group__0");
					put(grammarAccess.getHyImpliesExpressionAccess().getGroup_1(), "rule__HyImpliesExpression__Group_1__0");
					put(grammarAccess.getHyEquivalenceExpressionAccess().getGroup(), "rule__HyEquivalenceExpression__Group__0");
					put(grammarAccess.getHyEquivalenceExpressionAccess().getGroup_1(), "rule__HyEquivalenceExpression__Group_1__0");
					put(grammarAccess.getHyAndExpressionAccess().getGroup(), "rule__HyAndExpression__Group__0");
					put(grammarAccess.getHyAndExpressionAccess().getGroup_1(), "rule__HyAndExpression__Group_1__0");
					put(grammarAccess.getHyOrExpressionAccess().getGroup(), "rule__HyOrExpression__Group__0");
					put(grammarAccess.getHyOrExpressionAccess().getGroup_1(), "rule__HyOrExpression__Group_1__0");
					put(grammarAccess.getHyNestedExpressionAccess().getGroup(), "rule__HyNestedExpression__Group__0");
					put(grammarAccess.getHyNotExpressionAccess().getGroup(), "rule__HyNotExpression__Group__0");
					put(grammarAccess.getHyFeatureReferenceExpressionAccess().getGroup(), "rule__HyFeatureReferenceExpression__Group__0");
					put(grammarAccess.getHyConditionalFeatureReferenceExpressionAccess().getGroup(), "rule__HyConditionalFeatureReferenceExpression__Group__0");
					put(grammarAccess.getHyRelativeVersionRestrictionAccess().getGroup(), "rule__HyRelativeVersionRestriction__Group__0");
					put(grammarAccess.getHyVersionRangeRestrictionAccess().getGroup_0(), "rule__HyVersionRangeRestriction__Group_0__0");
					put(grammarAccess.getHyVersionRangeRestrictionAccess().getGroup_1(), "rule__HyVersionRangeRestriction__Group_1__0");
					put(grammarAccess.getHyVersionRangeRestrictionAccess().getGroup_2(), "rule__HyVersionRangeRestriction__Group_2__0");
					put(grammarAccess.getHyBooleanValueExpressionAccess().getGroup(), "rule__HyBooleanValueExpression__Group__0");
					put(grammarAccess.getHyGreaterExpressionAccess().getGroup(), "rule__HyGreaterExpression__Group__0");
					put(grammarAccess.getHyLessExpressionAccess().getGroup(), "rule__HyLessExpression__Group__0");
					put(grammarAccess.getHyGreaterOrEqualExpressionAccess().getGroup(), "rule__HyGreaterOrEqualExpression__Group__0");
					put(grammarAccess.getHyLessOrEqualExpressionAccess().getGroup(), "rule__HyLessOrEqualExpression__Group__0");
					put(grammarAccess.getHyEqualExpressionAccess().getGroup(), "rule__HyEqualExpression__Group__0");
					put(grammarAccess.getHyNotEqualExpressionAccess().getGroup(), "rule__HyNotEqualExpression__Group__0");
					put(grammarAccess.getHyAdditionExpressionAccess().getGroup(), "rule__HyAdditionExpression__Group__0");
					put(grammarAccess.getHyAdditionExpressionAccess().getGroup_1(), "rule__HyAdditionExpression__Group_1__0");
					put(grammarAccess.getHySubtractionExpressionAccess().getGroup(), "rule__HySubtractionExpression__Group__0");
					put(grammarAccess.getHySubtractionExpressionAccess().getGroup_1(), "rule__HySubtractionExpression__Group_1__0");
					put(grammarAccess.getHyMultiplicationExpressionAccess().getGroup(), "rule__HyMultiplicationExpression__Group__0");
					put(grammarAccess.getHyMultiplicationExpressionAccess().getGroup_1(), "rule__HyMultiplicationExpression__Group_1__0");
					put(grammarAccess.getHyDivisionExpressionAccess().getGroup(), "rule__HyDivisionExpression__Group__0");
					put(grammarAccess.getHyDivisionExpressionAccess().getGroup_1(), "rule__HyDivisionExpression__Group_1__0");
					put(grammarAccess.getHyBooleanValueAccess().getGroup(), "rule__HyBooleanValue__Group__0");
					put(grammarAccess.getHyEnumValueAccess().getGroup(), "rule__HyEnumValue__Group__0");
					put(grammarAccess.getHyNestedArithmeticalValueExpressionAccess().getGroup(), "rule__HyNestedArithmeticalValueExpression__Group__0");
					put(grammarAccess.getHyAttributeReferenceExpressionAccess().getGroup(), "rule__HyAttributeReferenceExpression__Group__0");
					put(grammarAccess.getEIntAccess().getGroup(), "rule__EInt__Group__0");
					put(grammarAccess.getHyImpliesExpressionAccess().getOperand2Assignment_1_2(), "rule__HyImpliesExpression__Operand2Assignment_1_2");
					put(grammarAccess.getHyEquivalenceExpressionAccess().getOperand2Assignment_1_2(), "rule__HyEquivalenceExpression__Operand2Assignment_1_2");
					put(grammarAccess.getHyAndExpressionAccess().getOperand2Assignment_1_2(), "rule__HyAndExpression__Operand2Assignment_1_2");
					put(grammarAccess.getHyOrExpressionAccess().getOperand2Assignment_1_2(), "rule__HyOrExpression__Operand2Assignment_1_2");
					put(grammarAccess.getHyNestedExpressionAccess().getOperandAssignment_1(), "rule__HyNestedExpression__OperandAssignment_1");
					put(grammarAccess.getHyNotExpressionAccess().getOperandAssignment_1(), "rule__HyNotExpression__OperandAssignment_1");
					put(grammarAccess.getHyFeatureReferenceExpressionAccess().getFeatureAssignment_0(), "rule__HyFeatureReferenceExpression__FeatureAssignment_0");
					put(grammarAccess.getHyFeatureReferenceExpressionAccess().getVersionRestrictionAssignment_1(), "rule__HyFeatureReferenceExpression__VersionRestrictionAssignment_1");
					put(grammarAccess.getHyConditionalFeatureReferenceExpressionAccess().getFeatureAssignment_1(), "rule__HyConditionalFeatureReferenceExpression__FeatureAssignment_1");
					put(grammarAccess.getHyConditionalFeatureReferenceExpressionAccess().getVersionRestrictionAssignment_2(), "rule__HyConditionalFeatureReferenceExpression__VersionRestrictionAssignment_2");
					put(grammarAccess.getHyRelativeVersionRestrictionAccess().getOperatorAssignment_1(), "rule__HyRelativeVersionRestriction__OperatorAssignment_1");
					put(grammarAccess.getHyRelativeVersionRestrictionAccess().getVersionAssignment_2(), "rule__HyRelativeVersionRestriction__VersionAssignment_2");
					put(grammarAccess.getHyVersionRangeRestrictionAccess().getLowerIncludedAssignment_0_1(), "rule__HyVersionRangeRestriction__LowerIncludedAssignment_0_1");
					put(grammarAccess.getHyVersionRangeRestrictionAccess().getLowerVersionAssignment_1_1(), "rule__HyVersionRangeRestriction__LowerVersionAssignment_1_1");
					put(grammarAccess.getHyVersionRangeRestrictionAccess().getUpperIncludedAssignment_1_3(), "rule__HyVersionRangeRestriction__UpperIncludedAssignment_1_3");
					put(grammarAccess.getHyVersionRangeRestrictionAccess().getUpperVersionAssignment_2_1(), "rule__HyVersionRangeRestriction__UpperVersionAssignment_2_1");
					put(grammarAccess.getHyBooleanValueExpressionAccess().getValueAssignment_1_0(), "rule__HyBooleanValueExpression__ValueAssignment_1_0");
					put(grammarAccess.getHyGreaterExpressionAccess().getOperand1Assignment_0(), "rule__HyGreaterExpression__Operand1Assignment_0");
					put(grammarAccess.getHyGreaterExpressionAccess().getOperand2Assignment_2(), "rule__HyGreaterExpression__Operand2Assignment_2");
					put(grammarAccess.getHyLessExpressionAccess().getOperand1Assignment_0(), "rule__HyLessExpression__Operand1Assignment_0");
					put(grammarAccess.getHyLessExpressionAccess().getOperand2Assignment_2(), "rule__HyLessExpression__Operand2Assignment_2");
					put(grammarAccess.getHyGreaterOrEqualExpressionAccess().getOperand1Assignment_0(), "rule__HyGreaterOrEqualExpression__Operand1Assignment_0");
					put(grammarAccess.getHyGreaterOrEqualExpressionAccess().getOperand2Assignment_2(), "rule__HyGreaterOrEqualExpression__Operand2Assignment_2");
					put(grammarAccess.getHyLessOrEqualExpressionAccess().getOperand1Assignment_0(), "rule__HyLessOrEqualExpression__Operand1Assignment_0");
					put(grammarAccess.getHyLessOrEqualExpressionAccess().getOperand2Assignment_2(), "rule__HyLessOrEqualExpression__Operand2Assignment_2");
					put(grammarAccess.getHyEqualExpressionAccess().getOperand1Assignment_0(), "rule__HyEqualExpression__Operand1Assignment_0");
					put(grammarAccess.getHyEqualExpressionAccess().getOperand2Assignment_2(), "rule__HyEqualExpression__Operand2Assignment_2");
					put(grammarAccess.getHyNotEqualExpressionAccess().getOperand1Assignment_0(), "rule__HyNotEqualExpression__Operand1Assignment_0");
					put(grammarAccess.getHyNotEqualExpressionAccess().getOperand2Assignment_2(), "rule__HyNotEqualExpression__Operand2Assignment_2");
					put(grammarAccess.getHyAdditionExpressionAccess().getOperand2Assignment_1_2(), "rule__HyAdditionExpression__Operand2Assignment_1_2");
					put(grammarAccess.getHySubtractionExpressionAccess().getOperand2Assignment_1_2(), "rule__HySubtractionExpression__Operand2Assignment_1_2");
					put(grammarAccess.getHyMultiplicationExpressionAccess().getOperand2Assignment_1_2(), "rule__HyMultiplicationExpression__Operand2Assignment_1_2");
					put(grammarAccess.getHyDivisionExpressionAccess().getOperand2Assignment_1_2(), "rule__HyDivisionExpression__Operand2Assignment_1_2");
					put(grammarAccess.getHyValueExpressionAccess().getValueAssignment(), "rule__HyValueExpression__ValueAssignment");
					put(grammarAccess.getHyNumberValueAccess().getValueAssignment(), "rule__HyNumberValue__ValueAssignment");
					put(grammarAccess.getHyStringValueAccess().getValueAssignment(), "rule__HyStringValue__ValueAssignment");
					put(grammarAccess.getHyBooleanValueAccess().getValueAssignment_1_0(), "rule__HyBooleanValue__ValueAssignment_1_0");
					put(grammarAccess.getHyEnumValueAccess().getEnumAssignment_0(), "rule__HyEnumValue__EnumAssignment_0");
					put(grammarAccess.getHyEnumValueAccess().getEnumLiteralAssignment_2(), "rule__HyEnumValue__EnumLiteralAssignment_2");
					put(grammarAccess.getHyNestedArithmeticalValueExpressionAccess().getOperandAssignment_1(), "rule__HyNestedArithmeticalValueExpression__OperandAssignment_1");
					put(grammarAccess.getHyContextInformationReferenceExpressionAccess().getContextInformationAssignment(), "rule__HyContextInformationReferenceExpression__ContextInformationAssignment");
					put(grammarAccess.getHyAttributeReferenceExpressionAccess().getFeatureAssignment_0(), "rule__HyAttributeReferenceExpression__FeatureAssignment_0");
					put(grammarAccess.getHyAttributeReferenceExpressionAccess().getAttributeAssignment_2(), "rule__HyAttributeReferenceExpression__AttributeAssignment_2");
				}
			};
		}
		return nameMappings.get(element);
	}
			
	@Override
	protected String[] getInitialHiddenTokens() {
		return new String[] { "RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT" };
	}

	public ExpressionDslGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}

	public void setGrammarAccess(ExpressionDslGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}
