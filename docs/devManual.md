# Development manual

### Introduction
This manual should help you getting started on working in this project. The manual references software that is needed to start development. All links to the download pages of these softwares can ne found at the bottom of the manual. You will need to install git, gradle and java before getting everything up and running.

### Repository
All of our code is kept track of in a git repository.

You can use the following git command to clone the git repository to your machine:
```
git clone https://github.com/CoolPeople/tictactoe.git
```
The github link to the repository can also be found at the bottom of the document. All commits to the repository must be done via pull requests and either approved by someone else or integrated using travis.

### Build scripts
For build scripts we use Gradle. Gradle allows us to run all tests when compiling our code. Gradle is ran through a shell. We recommend using the bash shell. For Gradle to be able to compile our code we first need to have the java development kit installed. Gradle should also handle all imported packages we are using in the project and download them from maven central.

The following gradle command will compile all java files and run tests:
```
gradle build
```
If all tests are passed and the files all compile gradle will prompt a message saying that the build was successful. This means that the code should be eligible for merging.

Since we are using spring boot the gradle command we use to run the code is not the usual "gradle run" but instead we use the following:
```
gradle bootRun
```
This will run the website on http://localhost:4567/.

### Test driven development
 All code submitted must be tried and tested using TDD. That means that we write tests before we write any code. The test should then initally be failing. Then we write the code to make the test pass and try to write the minimum amount of code for the test to pass. After we have gotten our inital test to pass we write a new test that will again make our current code fail. We repeat this process until we are certain that no tests should be able to fail.


### Links

* [Github repo]
* [Git]
* [Gradle]
* [Java JDK]

[Github repo]: <https://github.com/CoolPeople/tictactoe>
[Git]: <https://git-scm.com/downloads>
[Gradle]: <https://gradle.org/install/>
[Java JDK]: <http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html>
