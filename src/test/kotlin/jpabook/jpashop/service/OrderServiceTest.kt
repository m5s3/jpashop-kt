package jpabook.jpashop.service

import jakarta.persistence.EntityManager
import jpabook.jpashop.domain.Address
import jpabook.jpashop.domain.Member
import jpabook.jpashop.domain.OrderStatus
import jpabook.jpashop.domain.item.Book
import jpabook.jpashop.exception.NotEnoughStockException
import jpabook.jpashop.repository.OrderRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.fail
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
class OrderServiceTest(
    @Autowired val em: EntityManager,
    @Autowired val orderService: OrderService,
    @Autowired val orderRepository: OrderRepository
) {
    @DisplayName("상품주문")
    @Test
    fun order() {

        // Given
        val member = createMember()
        val book = createBook("시골 JPA", 10000, 10)

        val orderCount = 2

        // When
        val orderId: Long = orderService.order(member.id!!, book.id!!, orderCount)

        // Then
        val getOrder = orderRepository.findOne(orderId)
        println("getOrder.orderItems[0] = ${getOrder.orderItems[0]}")
        println("getOrder.statusadfadsfasds = ${getOrder.status?.name}")
        assertEquals(OrderStatus.ORDER, getOrder.status, "주문 상품 상태는 ORDER")
        assertEquals(1, getOrder.orderItems.size, "주문 상품 종류 수가 정확해야 한다.")
        assertEquals("시골 JPA", getOrder.orderItems[0].item?.name, "주문 상품명에 접근 가능해야한다.")
        assertEquals(10000 * orderCount, getOrder.getTotalPrice(), "주문 가격은 가격 * 수량이다.")
        assertEquals(8, book.stockQuantity, "주문 수량만큼 재고가 줄어야 한다.")
    }

    @DisplayName("상품주문 재고수량초과")
    @Test
    fun productOrderExceedingStockQuantity()  {
        // Given
        val member = createMember()
        val item = createBook("시골 JPA", 10000, 10)

        val orderCount = 11

        // When & Then
        val exception = assertThrows<NotEnoughStockException> {
            orderService.order(member.id!!, item.id!!, orderCount)
        }

        assertEquals("need more stock", exception.message)
    }

    @DisplayName("주문 취소")
    @Test
    fun cancelOrder() {
        // Given
        val member: Member = createMember()
        val item = createBook("시골 JPA", 10000, 10)

        val orderCount: Int = 2
        val orderId = orderService.order(member.id!!, item.id!!, orderCount)

        // When
        orderService.cancelOrder(orderId)

        // Then
        val getOrder = orderRepository.findOne(orderId)
        assertEquals(OrderStatus.CANCEL, getOrder.status, "주문 취소시 상태는 CANCEL 이다.")
        assertEquals(10, item.stockQuantity, "주문이 취소가 된 상품은 그만큼 재고가 증가해야 한다.")

    }

    private fun createBook(name: String, price: Int, quantity: Int): Book {
        val book = Book()
        book.name = name
        book.price = price
        book.stockQuantity = quantity
        em.persist(book)
        return book
    }

    private fun createMember(): Member {
        val member: Member = Member("회원1")
        member.address = Address("서울", "강가", "123-123")
        em.persist(member)
        return member
    }
}