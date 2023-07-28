package jpabook.jpashop.dto

data class OrderItemDto(
    val itemName: String,
    val orderPrice: Int,
    val count: Int,
)

