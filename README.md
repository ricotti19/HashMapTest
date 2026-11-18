# HashMap Mutation Testing (SE 3354 Extra Credit)

Separate-chaining hash map with resizing, tested with JUnit 5, measured with JaCoCo (coverage) and PIT (mutation testing).

## Setup (needs Java 17+ and Maven)

```
java -version
mvn -version
```

Open the folder in IntelliJ (File > Open > select `pom.xml` > Open as Project) or VS Code with the Java extension pack.

## Commands

| Goal | Command | Where to look |
|---|---|---|
| Run tests | `mvn test` | terminal |
| Coverage | `mvn test jacoco:report` | `target/site/jacoco/index.html` |
| Mutation testing | `mvn test-compile org.pitest:pitest-maven:mutationCoverage` | `target/pit-reports/index.html` |

## Files

- `src/main/java/hashmap/MyHashMap.java` : the data structure (the code you show for "The problem")
- `src/test/java/hashmap/MyHashMapGeneratedTest.java` : baseline, "as generated" style tests
- `src/test/java/hashmap/MyHashMapStrengthenedTest.java` : edge cases, invariants, randomized oracle test
- `PROMPT.md` : the AI prompt to show in class
- `DEMO_SCRIPT.md` : 5-minute walkthrough with timings

## IMPORTANT: do these before Thursday

1. Run all three commands once so Maven downloads everything (needs internet).
2. To get a clear before/after for PIT: temporarily move `MyHashMapStrengthenedTest.java` out of `src/test`, run PIT (baseline score and survivors), then put it back and rerun (improved score).
3. Write down the real numbers in DEMO_SCRIPT.md. Do not quote numbers you have not seen on your own screen.
4. Generate your own tests with the prompt in PROMPT.md and compare them to the baseline. The baseline file here is a starting point, not a substitute for your group's own tool run.
