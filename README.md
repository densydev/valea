# Valea

A lightweight and flexible Java library for rule-based probability evaluation and logic composition

## Example of usage
Basic usage with a simple probability
```java
Valea<ProbabilityContext> valea = Valea.builder()
    .evaluator(new FixedChanceEvaluator<>(0.05)) // 5% chance
    .build();

boolean win = valea.test(null); 
```

Advanced usage with custom context and "Pity" logic
```java
// Define your custom context
public record CasinoContext(int rolls) implements ProbabilityContext {

}

// Build complex logic
Valea<CasinoContext> casino = Valea.<CasinoContext>builder()
    .evaluator(new RandomValueEvaluator(1, 100)) // Base random
    .evaluator(ctx -> ctx.rolls() >= 50)         // Guaranteed win at 50 rolls
    .build();

boolean result = casino.test(new CasinoContext(50)); // Returns true
```

Combining evaluators using logical operators
```java
Valea<?> valea = Valea.create(
        new RandomValueEvaluator(1, 100).or(new FixedChanceEvaluator<>(0.05))
);
```

Creating a custom evaluator
```java
public class MyCustomEvaluator implements ProbabilityEvaluator<MyContext> {
    @Override
    public boolean evaluate(MyContext context) {
        // Your custom logic here
        return context.getSomeValue() > 10;
    }
}
```
___

## Maven
Adding repository:
```xml
<repositories>
    <repository>
        <id>densy-repository-snapshots</id>
        <url>https://repo.densy.org/snapshots</url>
    </repository>
</repositories>
```

Adding the library:
```xml
<dependency>
    <groupId>org.densy.valea</groupId>
    <artifactId>valea</artifactId>
    <version>1.0.1-SNAPSHOT</version>
</dependency>
```

## Gradle
Adding repository:
```groovy
maven {
    name = "densyRepositorySnapshots"
    url = uri("https://repo.densy.org/snapshots")
}
```

Adding the library:
```groovy
implementation "org.densy.valea:valea:1.0.1-SNAPSHOT"
```