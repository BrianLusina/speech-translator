package com.speechtranslator.data.aws

import android.content.Context
import com.amazonaws.auth.CognitoCredentialsProvider
import com.amazonaws.regions.Regions
import com.speechtranslator.R

class AWSService(context: Context){
    val credentialsProvider :CognitoCredentialsProvider

    init {
        val config = AWSModel.fromResource(context, R.raw.aws)
        credentialsProvider = CognitoCredentialsProvider(
                config.accountId,
                config.identityPoolId,
                config.unauthRoleArn,
                config.authRoleArn,
                Regions.fromName(config.region)
        )
    }
}