package com.ask.modulecore.vo

import com.querydsl.core.annotations.QueryProjection

data class UserVO @QueryProjection constructor(
    val id: String,
    val name: String,
    val companyName: String
)
