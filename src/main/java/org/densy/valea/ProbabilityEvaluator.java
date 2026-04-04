package org.densy.valea;

/**
 * A functional interface for defining custom probability logic.
 *
 * @param <C> the type of the probability context
 */
@FunctionalInterface
public interface ProbabilityEvaluator<C extends ProbabilityContext> {

    /**
     * Evaluates the probability condition based on the provided context.
     *
     * @param context the context data
     * @return {@code true} if the condition is met, otherwise {@code false}
     */
    boolean evaluate(C context);

    /**
     * Combines this evaluator with another using a logical AND.
     */
    default ProbabilityEvaluator<C> and(ProbabilityEvaluator<C> other) {
        return new AllMatchEvaluator<>(this, other);
    }

    /**
     * Combines this evaluator with another using a logical OR.
     */
    default ProbabilityEvaluator<C> or(ProbabilityEvaluator<C> other) {
        return new AnyMatchEvaluator<>(this, other);
    }

    /**
     * Returns an evaluator that represents the logical negation of this evaluator.
     */
    default ProbabilityEvaluator<C> negate() {
        return new NotMatchEvaluator<>(this);
    }
}
