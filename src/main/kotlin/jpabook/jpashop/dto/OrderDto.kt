package jpabook.jpashop.dto

import jpabook.jpashop.domain.Address
import jpabook.jpashop.domain.Order
import java.time.LocalDateTime

data class OrderDto(
    val orderId: Long?,
    val name: String?,
    val orderDate: LocalDateTime?,
    val address: Address?,
    val orderItems: List<OrderItemDto?> = listOf()
) {
    companion object {
        fun of(order: Order): OrderDto {
            val orderItems = order.orderItems.map { orderItem ->
                orderItem.item?.let { item ->
                    OrderItemDto(item.name, orderItem.orderPrice, orderItem.count)
                }
            }
            return OrderDto(
                order.id!!,
                order.member!!.name,
                order.orderDate!!,
                order.delivery!!.address!!,
                orderItems
            )
        }
    }
}

//private val orderId: Long? = null
//private val name: String? = null
//private val orderDate: LocalDateTime? = null //주문시간 private OrderStatus orderStatus;
//
//private val address: Address? = null
