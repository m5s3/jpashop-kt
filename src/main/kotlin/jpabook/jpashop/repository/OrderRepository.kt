package jpabook.jpashop.repository

import jakarta.persistence.EntityManager
import jpabook.jpashop.domain.Order
import org.springframework.stereotype.Repository

@Repository
class OrderRepository(
    private val em: EntityManager
) {
    fun save(order: Order) {
        em.persist(order)
    }

    fun findOne(orderId: Long): Order {
        return em.find(Order::class.java, orderId)
    }

    // fun List<Order> findOne()
}