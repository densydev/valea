package org.densy.valea;

/**
 * An evaluator that returns {@code true} only if all provided evaluators return {@code true}.
 */
public final class AllMatchEvaluator<C extends ProbabilityContext> implements ProbabilityEvaluator<C> {

    private final ProbabilityEvaluator<C>[] evaluators;

    @SafeVarargs
    public AllMatchEvaluator(ProbabilityEvaluator<C>... evaluators) {
        this.evaluators = evaluators;
    }

    @Override
    public boolean evaluate(C context) {
        for (ProbabilityEvaluator<C> evaluator : evaluators) {
            if (!evaluator.evaluate(context)) {
                return false;
            }
        }
        return true;
    }
}
