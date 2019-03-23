### __VH Traction Guest Framework :)__


This is a framework project to attend to the Van Hack Hackaton for Traction Guest.

---
#### This framework is able to automate tests for:


+ Web 
  - Using selenium (TDD)
+ API - Using Rest Assured (BDD)
  - Using Rest assured and BDD
+ Mobile - Using appium
  - Using Android and iOS (TDD)

##### This framework uses Java as the main language to script the tests.
___

##### Before we get started, here is the tools you will use on this project:

1. Android Studio must be installed (Windows or Mac) 
2. XCode must be installed (latest version for Mac only) 
3. Eclipse or another prefered IDE
4. Jenkins server set up on machine
5. Node.js
6. Java

##### Here the environment settings:

1. Maven for project dependency management 
2. TestNG as dependency 
3. PATH configured with ANDROID_HOME and JAVA_HOME 
4. Android emulator named as NEXUS_5X_API_25 
5. "chmod +x chromedriver" command on chromedrive file (Mac Only) 
6. Eclipse environment variable correctly set per class (PATH and ANDROID_HOME)
7. brew install carthage (Mac only)

#### What you should know about the integrations:

1. This framework uses testNG.xml, so that you must add your tests there.
2. This framework uses maven as dependency management, so use "mvn clean test" for start testing.
3. This framework can be trigger by a jenkins job. Use jekins to create job, download de repository from git and use mvn commands for start testing on shell script box of the job.
4. This framework uses Page-Object Model, so that you must first create your pages before scripting your tests.
5. This framework uses Data-Driven Testing, so that you can changes the input of your test and change nothing on the code side.

Developed by Gabriel Aguido Fraga


