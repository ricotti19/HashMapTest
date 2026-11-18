You are writing JUnit 5 tests for the Java class below (a hash map using separate
chaining with resizing at load factor 0.75; null keys throw NullPointerException,
null values are allowed).

Requirements:
- Cover: empty map, single element, duplicate keys (overwrite), null key, null value,
  hash collisions, negative hashCodes, resizing, and remove from head/middle/tail of a chain.
- Every test must assert on actual results (return values, size, get/containsKey),
  not just call methods.
- Use one behavior per test and descriptive test names.
- Use only the public API plus the package-private capacity() and checkInvariants().
- Do not use mocks.

[pasted MyHashMap.java here]