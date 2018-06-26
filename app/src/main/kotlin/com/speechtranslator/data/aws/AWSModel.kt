package com.speechtranslator.data.aws

import android.content.Context
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader

class AWSModel {
    var accountId = ""
    var region = ""
    var identityPoolId = ""
    var unauthRoleArn = ""
    var authRoleArn = ""

    companion object {
        fun fromJson(json: String): AWSModel {
            val o = JSONObject(json)
            val model = AWSModel().apply {
                accountId = o.getString("accountId")
                region = o.getString("region")
                identityPoolId = o.getString("identityPoolId")
                unauthRoleArn = o.getString("unauthRoleArn")
                authRoleArn = o.getString("authRoleArn")
            }
            return model
        }

        fun fromResource(context: Context, resourceId: Int): AWSModel {
            val inputStream = context.resources.openRawResource(resourceId)
            val inputReader = BufferedReader(InputStreamReader(inputStream))
            val json = inputReader.readText()
            inputReader.close()
            inputStream.close()
            return fromJson(json)
        }
    }
}