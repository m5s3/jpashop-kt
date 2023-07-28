package jpabook.jpashop.api

import jpabook.jpashop.dto.OrderDto
import jpabook.jpashop.service.OrderService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api")
class OrderApiController(
    val orderService: OrderService
) {
    @GetMapping("/v3.1/orders")
    fun orderV3_page(
        @RequestParam(value = "offset", defaultValue = "0") offset: Int,
        @RequestParam(value = "limit", defaultValue = "20") limit: Int
    ): List<OrderDto> {
        val orders = orderService.findAllWithMemberDelivery(offset, limit)
        return  orders.map {
            OrderDto.of(it)
        }
    }
}