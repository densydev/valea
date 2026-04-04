package org.densy.valea;

/**
 * An evaluator that inverts the result of the provided evaluator.
 */
public final class NotMatchEvaluator<C extends ProbabilityContext> implements ProbabilityEvaluator<C> {

    private final ProbabilityEvaluator<C> evaluator;

    public NotMatchEvaluator(ProbabilityEvaluator<C> evaluator) {
        this.evaluator = evaluator;
    }

    @Override
    public boolean evaluate(C context) {
        return !evaluator.evaluate(context);
    }
}
