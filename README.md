# Speech Translator

Small and basic speech translator Android app that uses AWS Speech recognition service to translate text.

## Getting started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. 

Simply clone the repo:

```bash
git clone git@github.com:BrianLusina/speech-translator.git
# or if using https
git clone https://github.com/BrianLusina/speech-translator.git 
```

That should get you the copy of the project locally

## Prerequisites
A couple of things you will need to setup and run this project:

1. [Android Studio](https://developer.android.com/studio/)

Download and install the latest version of Android Studio.

2. [Gradle](https://gradle.org/)

Install and download the latest version of Gradle build system. This is used to build the application.

To install gradle, you can simply use [Sdk](https://sdkman.io/). Installation instructions are found [here](https://sdkman.io/install). After which you can simply install gradle:

```bash
sdk install gradle
```

3. [Kotlin](https://kotlinlang.org/)

This project is build using Kotlin as the source language, an understanding of the language is required.

Kotlin can be installed using sdk as well:

```bash
sdk install kotlin
```

This will install and set the Kotlin version for you

4. [AWS Account](https://aws.amazon.com/)

Ensure you have an account with Amazon Web Services. A [free account](https://aws.amazon.com/free/) can be used for this.

## Starting up the application

To start up the application you will need to first setup and configure AWS.

## Built With

1. [Kotlin](https://kotlinlang.org/) Source language
2. [Koin](https://insert-koin.io) Used for dependency injection
3. [AWS Mobile](https://docs.aws.amazon.com/aws-mobile/latest/developerguide/getting-started.html) Used to configure and interact with AWS services
4. [AWS Sdk](https://github.com/aws/aws-sdk-android) - Library used to interact with AWS services
4. [Anko Commons](https://github.com/Kotlin/anko) - used to add _sugar_ to common Android boilerplate code.

## Versioning

[SemVer](https://semver.org/) is used for versioning. For the versions available, see the [tags on this repository](https://github.com/BrianLusina/speech-translator/tags).

## License

This project is licensed under the MIT License - see the [LICENSE.md](./LICENSE.md) file for details

[![forthebadge](https://forthebadge.com/images/badges/built-for-android.svg)](https://forthebadge.com)
[![forthebadge](https://forthebadge.com/images/badges/built-with-love.svg)](https://forthebadge.com)
