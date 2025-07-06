## Question 1:

If your software has many bugs (defects) when you give it to users, the support team will get more questions and problems to fix. 

This makes support slower, more expensive, and customers become unhappy.If you fix most bugs before delivery, support becomes easier and cheaper.

## Question 2:

For:
    -Developers know their code best.
    -They can test quickly.
    -They find bugs early.
    -No need to wait for testers.
    -Helps improve their code quality.

Against:
    -Developers may miss bugs because they think their code is fine.
    -They can be biased.
    -Less experience in testing than QA testers.
    -Might skip some test cases.
    -Not good for very big or complex systems.

## Question 3:

## Compile All Java Files

in the `tests/exp6` directory

```
javac -cp "src/main;lib/*" src/main/*.java src/test/*.java
```

## Run All JUnit Unit Tests

java -jar lib/junit-platform-console-standalone-1.9.2.jar --class-path "src/main;src/test" --scan-class-path

Test run finished after 1240 ms
[        15 containers found      ]
[         0 containers skipped    ]
[        15 containers started    ]
[         0 containers aborted    ]
[        15 containers successful ]
[         0 containers failed     ]
[       194 tests found           ]
[         0 tests skipped         ]
[       194 tests started         ]
[         0 tests aborted         ]
[       168 tests successful      ]
[        26 tests failed          ]

## Question 4:

Regression testing mean testing the software again after you change something, to make sure the old parts still work.

It checks that new updates didn’t break old features.

## Question 5:

Black Box Testing:
We test the outside of the program. We don’t look inside the code. We give input and check output.

White Box Testing:
We look inside the code. We test logic, paths, loops, and how the program works inside.

Difference:
Black box: test from outside, no code knowledge.
White box: test from inside, need to know the code.