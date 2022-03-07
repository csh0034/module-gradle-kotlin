package com.ask.modulecore.repository

import com.ask.modulecore.entity.QCompany.company
import com.ask.modulecore.entity.QUser.user
import com.ask.modulecore.vo.QUserVO
import com.ask.modulecore.vo.UserVO
import com.querydsl.jpa.impl.JPAQueryFactory

class UserRepositoryCustomImpl(
    private val queryFactory: JPAQueryFactory
) : UserRepositoryCustom {

    override fun findAllByCompanyName(companyName: String): MutableList<UserVO> {
        return queryFactory
            .select(
                QUserVO(
                    user.id,
                    user.name,
                    company.name
                )
            )
            .from(user)
            .join(user.company, company)
            .where(company.name.eq(companyName))
            .fetch()
    }
}
