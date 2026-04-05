package org.densy.valea;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

/**
 * The core engine used to evaluate probabilities based on a provided context.
 *
 * @param <C> the type of the probability context
 */
public final class Valea<C extends ProbabilityContext> implements Predicate<C> {
    private final List<ProbabilityEvaluator<C>> evaluators;

    private Valea(Collection<ProbabilityEvaluator<C>> evaluators) {
        this.evaluators = List.copyOf(evaluators);
    }

    /**
     * Checks if the context satisfies all registered evaluators.
     *
     * @param context the context data
     * @return {@code true} if all evaluators pass, otherwise {@code false}
     */
    @Override
    public boolean test(C context) {
        if (evaluators.isEmpty()) {
            return false;
        }
        return evaluators.stream().allMatch(e -> e.evaluate(context));
    }

    /**
     * Creates a new instance with the given evaluators.
     */
    @SafeVarargs
    public static <C extends ProbabilityContext> Valea<C> create(ProbabilityEvaluator<C>... evaluators) {
        return new Valea<>(List.of(evaluators));
    }

    /**
     * Creates a new instance with the given evaluators.
     */
    public static <C extends ProbabilityContext> Valea<C> create(Collection<ProbabilityEvaluator<C>> evaluators) {
        return new Valea<>(evaluators);
    }

    /**
     * Returns a new builder for Valea.
     */
    public static <C extends ProbabilityContext> Builder<C> builder() {
        return new Builder<>();
    }

    /**
     * A builder for {@link Valea} instances.
     */
    public static class Builder<C extends ProbabilityContext> {

        private final List<ProbabilityEvaluator<C>> evaluators = new ArrayList<>();

        /**
         * Adds an evaluator to the chain.
         */
        public Builder<C> evaluator(ProbabilityEvaluator<C> evaluator) {
            this.evaluators.add(evaluator);
            return this;
        }

        /**
         * Builds the Valea instance.
         */
        public Valea<C> build() {
            return new Valea<>(evaluators);
        }
    }
}