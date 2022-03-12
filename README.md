Tutorial for test driven development from LinkedIn: 
https://www.linkedin.com/learning/practical-test-driven-development-for-java-programmers/
I was lucky to get free 48 hour access to this tutorial. This also turned out to be a good lesson on refactoring.

In the tutorial test directory was outside the src. The highest JUnit version offered to me was 4.0 (tutorial used 4.12 
or something). It wasn't working for me, so I recreated this project as a Maven project and took the dependencies for 
JUnit from another project (JUnit5CrashCourse, it uses JUnit 5.7). It placed tests in src directory. Later on the 
tutorial said to set the BuildPath for the test directory - right click test folder -> Build Path -> Use as source 
folder. Maybe that would have gotten the project going the first time I was attempting to run it (I received "no such 
method" error, and the test wasn't able to start). I also took Mockito dependency from another project 
(Mockito_JUnit_example), while tutorial used a jar file with a different version of Mockito. 

* It seems I can't download files for tautologies and legacy code(chapter 10) because I don't have a subscription

* nineDigitISBNsAreNotAllowed() test looks quite different in the tutorial, because of the differences between JUnit4
  and JUnit5
  
* Stubs and mocks override external dependencies. Stubs are used to test data, mocks are used to test behaviour. 
Lighter versions of stubs are fakes and dummies. Mock without an implementation is a fake or dummy: 
  SomeClass someClass = mock(SomeClass.class) and that's it, no method calls etc.

\ISBNValidatorJUnit\stubs mocks fakes.jpg

* Tautology - when code that we're testing is repeated in a test. To avoid tautologies, tests normally should not 
  include any logic, calculations. Tests should be based on examples and expected outcomes. 



