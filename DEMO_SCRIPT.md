# 5-Minute Demo Script (fill in YOUR numbers)

Tabs to open beforehand, in this order:
1. MyHashMap.java
2. PROMPT.md
3. MyHashMapGeneratedTest.java
4. MyHashMapStrengthenedTest.java
5. target/site/jacoco/index.html
6. target/pit-reports/index.html (fresh run from the baseline-only suite saved as backup)

Terminal ready with: `mvn test-compile org.pitest:pitest-maven:mutationCoverage`

## 1. The problem (0:00 - 0:30)
In MyHashMap.java point at:
- `indexFor` : negative hashCodes (floorMod)
- `put` : overwrite must not increase size; resize check `>` vs `>=`
- `remove` : head vs middle/tail of the chain
- `resize` : rehash with the new capacity

## 2. Test generation (0:30 - 1:15)
- Tool used: ______ (e.g. Claude / ChatGPT / Copilot)
- Show PROMPT.md
- Show MyHashMapGeneratedTest.java: kept / fixed / added: ______

## 3. Why the suite is good (1:15 - 2:45)
- Coverage (JaCoCo): line ___% branch ___%. Uncovered: ______ because ______
- Edge cases: `newMapIsEmpty`, `putAndGetSingleElement`, `putExistingKeyOverwritesValue`, `nullKeyThrows`, `nullValueAllowed`, `capacityOneMapWorks`, `negativeHashCodeStillWorks`
- Behavior/invariants: `checkInvariants()` (size matches node count, keys in the right bucket, no duplicates, load factor respected); `randomOperationsMatchJavaHashMap` compares against java.util.HashMap
- Weakness found in generated tests: ______ (e.g. only checks size, never checks which bucket/chain; never tests collisions or capacity)

## 4. Mutation testing (2:45 - 4:15)
- Baseline-only score: ___ killed / ___ total = ___%
- Surviving mutant 1: line ___ , mutator ______ , why it survived: ______
- Test added to kill it: ______
- Rerun: ___ killed / ___ total = ___%

## 5. Takeaway (4:15 - 4:30)
Generated tests reach high coverage fast but assert shallowly; mutation testing shows what the assertions miss. Edge cases that need domain knowledge (forced collisions, resize boundary) still need a human.
