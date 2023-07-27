package jpabook.jpashop.api

import jpabook.jpashop.dto.OrderSearch
import jpabook.jpashop.dto.SimpleOrderDto
import jpabook.jpashop.service.OrderService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class OrderSimpleApiController(
    val orderService: OrderService
) {
    @GetMapping("/v2/simple-orders")
    fun orderV2(): List<SimpleOrderDto> {
        val orders = orderService.findOrder(OrderSearch())
        println("orders = ${orders}")
        return orders!!.map {
            SimpleOrderDto(
                orderId = it?.id!!,
                name = it.member?.name!!,
                orderStatus = it.status!!,
                address = it.member?.address!!
            )
        }
    }

    @GetMapping("/v3/simple-orders")
    fun orderV3(): List<SimpleOrderDto> {
        val orders = orderService.findAllWithMemberDelivery()
        return orders.map {
            SimpleOrderDto(
                orderId = it.id!!,
                name = it.member?.name!!,
                orderStatus = it.status!!,
                address = it.member?.address!!
            )
        }
    }
}