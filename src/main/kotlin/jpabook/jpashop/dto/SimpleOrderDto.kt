package jpabook.jpashop.dto

import jpabook.jpashop.domain.Address
import jpabook.jpashop.domain.OrderStatus
import java.time.LocalDateTime

data class SimpleOrderDto(
    val orderId: Long,
    val name: String,
    val orderStatus: OrderStatus,
    val address: Address
)

//Long orderId,
//String name,
//LocalDateTime orderDate,
//OrderStatus orderStatus,
//Address address
