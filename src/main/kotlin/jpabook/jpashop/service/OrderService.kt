package jpabook.jpashop.service

import jpabook.jpashop.domain.Delivery
import jpabook.jpashop.domain.Order
import jpabook.jpashop.domain.OrderItem
import jpabook.jpashop.repository.ItemRepository
import jpabook.jpashop.repository.OrderRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val memberService: MemberService,
    private val itemRepository: ItemRepository
) {

    @Transactional
    fun order(memberId: Long, itemId: Long, count: Int): Long {
        val member = memberService.findOne(memberId)
        val item = itemRepository.findOne(itemId)

        val delivery: Delivery = Delivery()
        val orderItem: OrderItem = OrderItem()
        orderItem.item = item
        orderItem.orderPrice = item.price
        orderItem.count = count

        val order:Order = Order()

        order.member = member
        order.delivery = delivery
        order.orderItems = mutableListOf(orderItem)

        orderRepository.save(order)
        return order.id!!
    }

    @Transactional
    fun cancelOrder(orderId: Long) {
        val order = orderRepository.findOne(orderId)
        order.cancel()
    }
}