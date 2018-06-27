# Speech Translator

[![Build status](https://build.appcenter.ms/v0.1/apps/930b811e-4f76-40aa-8ab4-7398cbc8b5c4/branches/master/badge)](https://appcenter.ms)

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

To start up the application you will need to first setup and configure AWS. The following will run you through the steps to take to have AWS setup on your development environment:

1. First install the [aws cli tool](https://docs.aws.amazon.com/cli/latest/userguide/installing.html) locally. Installation instructions are provided in the link.

2. You will now need to configure aws to get the credentials you need to communicate with AWS. To do this, run the following locally:

```bash
$ aws configure
AWS Access Key ID: <YOUR_AWS_ACCESS_KEY_ID>
AWS Secret Access Key: <YOUR_AWS_SECRET_ACCESS_KEY>
Default region name:<YOUR_REGION>
Default output format:<YOUR_PREFERED_DEFAULT_OUTPUT>
```  

Running `aws configure` will run you through the steps to configure aws. 
First output is the AWS Access Key ID, which can be found [here](https://console.aws.amazon.com/iam/home?region=us-east-1#/security_credential) under the __Access keys (access key ID and secret access key)__ dropdown option. If there is none, you can create one by clicking the `Create New Access Key` button.

You will then get a Popup, which will have the Access Key Id and the Secret Access Key. You can save these values in your home directory(if on Linux) under the _.aws_ folder in a file called _credentials_ as below:

```text
[default]
aws_access_key_id = <YOUR_AWS_ACCESS_KEY_ID>
aws_secret_access_key = <YOUR_AWS_SECRET_ACCESS_KEY>
```

Now to configure the default region which can be the region you used when creating the AWS account. You can refer to [this](https://docs.aws.amazon.com/AmazonRDS/latest/UserGuide/Concepts.RegionsAndAvailabilityZones.html) for more information about regions.

For this, you can use __us-east-1__ as your region. You can then set this in the _.aws/config_ file in your home directory:

```text
[default]
region = us-east-1
output = json
```

This includes the default output format preferred, which in this case is json.

3. After the configuration step is complete, next you will need to setup a [Cloud formation template](https://aws.amazon.com/cloudformation/aws-cloudformation-templates/) or can use the one provided [here](./speech-translator.yaml). You can create an [S3](https://s3.console.aws.amazon.com/s3) bucket and store the yaml file there, although this is optional. After which run the following command:

```bash
$ aws cloudformation create-stack --stack-name speech-translator --template-url https://s3-us-west-2.amazonaws.com/<S3_BUCKET_NAME>/<YOUR_CLOUD_FORMATION_TEMPLATE> --capabilities CAPABILITY_NAMED_IAM
{
"StackId": "<STACK_ID>"
}
```
> Set the template-url according to your needs

The translate service doesn’t need anything special, but it does need an IAM role to approve the request. The [CloudFormation template](./speech-translator.yaml) sets up an unauthenticated IAM role, then associates that unauthenticated IAM role to a newly created Amazon Cognito identity pool. The identity pool will give us temporary credentials to access the Amazon Translate service later on.

4. After all this is setup, now run the following command to get the credentials we need to configure the application:

```bash
$ aws cloudformation describe-stack --stack-name speech-translator
{
    "Stacks": [
        {
            "StackId": "<STACK_ID>",
            "StackName": "speech-translator",
            "Description": "CloudFormation Stack to create Amazon Cognito Federated Identity Pool, IAM Roles, and Policies for Mobile Speech Translator app",
            "CreationTime": "2018-06-27T06:13:33.273Z",
            "RollbackConfiguration": {},
            "StackStatus": "CREATE_COMPLETE",
            "DisableRollback": false,
            "NotificationARNs": [],
            "Capabilities": [
                "CAPABILITY_NAMED_IAM"
            ],
            "Outputs": [
                {
                    "OutputKey": "authRoleArn",
                    "OutputValue": "<YOUR_AUTH_ROLE_ARN>"
                },
                {
                    "OutputKey": "unauthRoleArn",
                    "OutputValue": "<YOUT_UNAUTH_ROLE_ARN>"
                },
                {
                    "OutputKey": "identityPoolId",
                    "OutputValue": "<YOUR_IDENTITY_POOL_ID>"
                }
            ],
            "Tags": [],
            "EnableTerminationProtection": false
        }
    ]
}
``` 

The values in <> brackets are what we need to allow the aws sdk communicate with the translator service.

5. Now we can create a json file, call it aws.json under the [raw resource folder](./app/src/main/res/raw/) with the following input:

```json
{
  "region": "<YOUR_PREFERRED_REGION>",
  "accountId": "<YOUR_ACCOUNT_ID>",
  "identityPoolId": "<YOUR_IDENTITY_POOL_ID>",
  "unauthRoleArn": "<YOUR_UNAUTH_ROLE_ARN>",
  "authRoleArn": "<YOUR_AUTH_ROLE_ARN>"
}
```

Enter the missing fields(in angle <> brackets) as from above, the <YOUR_ACCOUNT_ID> is the 12 digit number for your account — available in the top banner of the AWS console, which can be found [here](https://docs.aws.amazon.com/IAM/latest/UserGuide/console_account-alias.html).

Ensure this is not pushed to an open VCS, add it to [.gitignore](./.gitignore) if it is not already there.

## Deployment

Deployment has been automated

## Built With

1. [Kotlin](https://kotlinlang.org/) Source language
2. [Koin](https://insert-koin.io) Used for dependency injection
3. [AWS Mobile](https://docs.aws.amazon.com/aws-mobile/latest/developerguide/getting-started.html) Used to configure and interact with AWS services
4. [AWS Sdk](https://github.com/aws/aws-sdk-android) - Library used to interact with AWS services
5. [Anko Commons](https://github.com/Kotlin/anko) - used to add _sugar_ to common Android boilerplate code.
6. [App Center](https://appcenter.ms) - for automated deployment and analytics

## Versioning

[SemVer](https://semver.org/) is used for versioning. For the versions available, see the [tags on this repository](https://github.com/BrianLusina/speech-translator/tags).

## License

This project is licensed under the MIT License - see the [LICENSE.md](./LICENSE.md) file for details

[![forthebadge](https://forthebadge.com/images/badges/built-for-android.svg)](https://forthebadge.com)
[![forthebadge](https://forthebadge.com/images/badges/built-with-love.svg)](https://forthebadge.com)
