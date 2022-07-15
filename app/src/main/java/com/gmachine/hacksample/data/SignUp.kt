package com.gmachine.hacksample.data

import com.gmachine.hacksample.ui.feed.NewsType

data class UserSignUpResponse(val message: String)

data class SignUpInfo(val username: String, val email: String, val firstName: String, val lastName: String, val credentials: List<Credential>, val enabled: Boolean = true)

data class Credential(val value: String, val type: String = "password", val temporary: Boolean = false)

data class SignInInfo(val client_id: String = "test-client", val grant_type: String = "password", val username: String, val password: String)

data class SignInResponse(val access_token: String)

data class UserResponse(val username: String, val email: String, val firstName: String, val lastName: String, val sub: String, val emailVerified: Boolean, val name: String)

data class FeedNewsResponse(val feedNews:List<BackNews>)

data class BackNews(val title: String, val subTitle: String, val date: String, val newsType: String, val image: String)