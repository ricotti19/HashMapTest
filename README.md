# HashMap Mutation Testing (SE 3354 Extra Credit)

Separate-chaining hash map with resizing, tested with JUnit 5, measured with JaCoCo (coverage) and PIT (mutation testing).

Group: Shriya Shenoj, Sakethram Vedula, Ethan Diep, Melissa Mejia, Soham Kulkarni

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

- `src/main/java/hashmap/MyHashMap.java` : the data structure (the code for "The problem")
- `src/test/java/hashmap/MyHashMapGeneratedTest.java` : baseline, "as generated" style tests
- `src/test/java/hashmap/MyHashMapStrengthenedTest.java` : edge cases, invariants, randomized oracle test
- `PROMPT.md` : the AI prompt to show in class
