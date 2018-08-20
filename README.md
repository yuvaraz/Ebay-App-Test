Project Title

eBay Mobile automation task

Getting Started

This project designed using Page Object Model with Page Factory (POM). All page objects are places in src/main/pages and testcases are place in src/test/testcases

Why we used Page ObjectModel?

Page Object Model is a Design Pattern which has become popular in Selenium Test Automation. It is widely used design pattern in Selenium for enhancing test maintenance and reducing code duplication. Page object model (POM) can be used in any kind of framework such as modular, data-driven, keyword driven, hybrid framework etc. The benefit is that if the UI changes for the page, the tests themselves don’t need to change, only the code within the page object needs to change. Subsequently, all changes to support that new UI is located in one place.

Prerequisites

List of softwares/dependencies to execute the project

IDE: Eclipse/STS/Any IDE
Language Binding:Java 
Maven depedencies: Selenium 3.13.0, Appium-JavaClient 6.1.0,TestNG 6.11
Appium Server : 1.7.1
Unit TestFramework & Build Plugin: TestNG, Maven

Installing
A step by step series of examples that tell you how to get a development env running

Project Import
Step1: Download and install any IDE (Eclipse/STS)
Step2: Install TestNG, Maven plugins
Step3: Import this project as type a Maven Project
Appium Install
Step1: Download latest Appium server from URL: http://appium.io/
Step2: Install downloaded Appium
Step3: Run the appium server

Running the tests
Run from IDE:
Step1: Select testng.xml file 
Step2: Run as TestNG Suite

Run as batch file:
Step1: Create a batch for testng.xml 
E.g:
set myProjectpath=project location path
cd=%myProjectpath%
set classpath=%myProjectpath%\bin;%myProjectpath%\libs\*
echo %classpath%
java org.testng.TestNG %myProjectpath%\testng.xml
pause
Run from Jenkins
Step1: Trigger the Jenkins job as Clean test
And coding style tests

Created reusable methods in corresponding page class

Methods in LoginPage: userLogin(),clickOnSignInButton(),clickOnLoginButton(),getLoginDataFromSheet()

Methods in OrderProductPage: searchProduct(),selectProduct(),clickOnBuyButton(),clickOnReviewButton(),provideContactInfo(),getDataFromSheet(),clickOnContinue()

Methods in CommonMethod class:findElement(),isElementPresent(),click(),sendKeys(),getText(),getSizeElements(),scrollPageUp(),
swipeLeftToRight(),swipeRightToLeft(),swipeFirstCarouselFromRightToLeft(),performTapAction(),scrollToText(),
takeScreenshot()

Authors
Yuvaraj - Initial work - yuvaraj
