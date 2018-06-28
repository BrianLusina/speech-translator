#!/usr/bin/env bash

# after cloning the repository, download the required files for build to succeed. Files needed are:
# 1. aws.json file
# 2. keystore.properties files
# 3. speech-translator-api.json
# 4. gradle.properties file

export GRADLE_PROPERTIES=${APPCENTER_SOURCE_DIRECTORY}/gradle.properties
export KEYSTORE_PROPERTIES=${APPCENTER_SOURCE_DIRECTORY}/keystores/keystore.properties
export AWS_JSON_FILE=${APPCENTER_SOURCE_DIRECTORY}/app/src/main/res/raw/aws.json
export GOOGLE_SERVICE_JSON_FILE=${APPCENTER_SOURCE_DIRECTORY}/keystores/speech-translator-api.json

# set the keystore.properties file to the keystores/ directory as keystore.properties
# set the gradle.properties file and save to root of project
function copyEnvToProperties {
    echo "Gradle Properties should exist at $GRADLE_PROPERTIES"
    echo "Keystore Properties should exist at $KEYSTORE_PROPERTIES"

    if [ ! -f "$KEYSTORE_PROPERTIES" ];
    then
        echo "${KEYSTORE_PROPERTIES} does not exist...Creating file"

        touch ${KEYSTORE_PROPERTIES}

        echo "keyAlias=$KEY_ALIAS" >> ${KEYSTORE_PROPERTIES}
        echo "keyPassword=$KEY_PASSWORD" >> ${KEYSTORE_PROPERTIES}
        echo "storeFile=$STORE_FILE" >> ${KEYSTORE_PROPERTIES}
        echo "storePassword=$STORE_PASSWORD" >> ${KEYSTORE_PROPERTIES}
    fi

    if [ ! -f "$GRADLE_PROPERTIES" ]
    then
        echo "${GRADLE_PROPERTIES} does not exist...Creating Properties file"

        touch ${GRADLE_PROPERTIES}
        echo "APP_CENTER_KEY=$APP_CENTER_KEY" >> ${GRADLE_PROPERTIES}
        echo "SERVICE_ACCOUNT_EMAIL=$SERVICE_ACCOUNT_EMAIL" >> ${GRADLE_PROPERTIES}
        echo "KEYSTORE_PROPERTIES_FILE=$KEYSTORE_PROPERTIES_FILE" >> ${GRADLE_PROPERTIES}
        echo "JSON_KEY_FILE=$JSON_KEY_FILE" >> ${GRADLE_PROPERTIES}

        if [ "$APPCENTER_BRANCH" == "master" ];
        then
            echo "RELEASE_TRACK=\"production\"" >> ${GRADLE_PROPERTIES}
        else
            echo "RELEASE_TRACK=\"beta\"" >> ${GRADLE_PROPERTIES}
        fi
    fi
}

# set the aws.json file, which is an environment variable to app/src/main/res/raw/aws.json
function setUpAwsJsonFile {
    echo "aws.json file should exist at $AWS_JSON_FILE"

    if [ ! -f "$AWS_JSON_FILE" ];
    then
        echo "${AWS_JSON_FILE} does not exist...Creating file"

        touch ${AWS_JSON_FILE}

        echo ${AWS_JSON} > ${AWS_JSON_FILE}
    fi
}

# set the Google Service API json file to the speech-translator-api.json file in the keystores/ dir
function setUpGoogleServicesJsonFile {
    echo "google services json file should exist $GOOGLE_SERVICE_JSON_FILE"

    if [ ! -f "$GOOGLE_SERVICE_JSON_FILE" ];
    then
        echo "${GOOGLE_SERVICE_JSON_FILE} does not exist...Creating file"

        touch ${GOOGLE_SERVICE_JSON_FILE}

        echo ${GOOGLE_SERVICE_API_JSON} > ${GOOGLE_SERVICE_JSON_FILE}
    fi
}

copyEnvToProperties
setUpAwsJsonFile
setUpGoogleServicesJsonFile