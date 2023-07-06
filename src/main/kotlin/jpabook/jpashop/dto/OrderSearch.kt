package jpabook.jpashop.dto

import jpabook.jpashop.domain.OrderStatus

data class OrderSearch(
    val memberName: String?,
    val orderStatus: OrderStatus?
)
