package com.ask.modulecore.repository

import com.ask.modulecore.vo.UserVO

interface UserRepositoryCustom {

    fun findAllByCompanyName(companyName: String): MutableList<UserVO>

}
