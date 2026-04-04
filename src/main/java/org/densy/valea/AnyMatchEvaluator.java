package org.densy.valea;

/**
 * An evaluator that returns {@code true} if at least one of the provided evaluators returns {@code true}.
 */
public final class AnyMatchEvaluator<C extends ProbabilityContext> implements ProbabilityEvaluator<C> {

    private final ProbabilityEvaluator<C>[] evaluators;

    @SafeVarargs
    public AnyMatchEvaluator(ProbabilityEvaluator<C>... evaluators) {
        this.evaluators = evaluators;
    }

    @Override
    public boolean evaluate(C context) {
        for (ProbabilityEvaluator<C> evaluator : evaluators) {
            if (evaluator.evaluate(context)) {
                return true;
            }
        }
        return false;
    }
}
