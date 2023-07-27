package jpabook.jpashop

import jakarta.annotation.PostConstruct
import jakarta.persistence.EntityManager
import jpabook.jpashop.domain.*
import jpabook.jpashop.domain.item.Book
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class InitDb(
    private val initService: InitService
) {

    //@PostConstruct
    @PostConstruct
    fun run() {
        initService.dbInit1()
        initService.dbInit2()
    }


    companion object {
        @Component
        @Transactional
        class InitService(
            private val em: EntityManager
        ) {
            fun dbInit1() {
                val member = createMember("userA", "서울", "1", "1111")
                em.persist(member)
                val book = Book();
                book.name = "JPA BOOK"
                book.price = 10000
                book.stockQuantity = 10
                em.persist(book)

                val book2 = Book();
                book2.name = "JPA BOOK"
                book2.price = 20000
                book2.stockQuantity = 200
                em.persist(book2)

                val orderItem1 = OrderItem.of(book, 10000, 2)
                val orderItem2 = OrderItem.of(book2, 20000, 1)

                val delivery = Delivery()
                delivery.address = member.address
                val order = Order.of(member, delivery, mutableListOf(orderItem1, orderItem2))
                em.persist(order);
            }

            fun dbInit2() {
                val member = createMember("userB", "대구", "2", "2222")
                em.persist(member)
                val book = Book();
                book.name = "BOOK1"
                book.price = 5000
                book.stockQuantity = 15
                em.persist(book)

                val book2 = Book();
                book2.name = "BOOK2"
                book2.price = 15000
                book2.stockQuantity = 10
                em.persist(book2)

                val orderItem1 = OrderItem.of(book, 5000, 2)
                val orderItem2 = OrderItem.of(book2, 15000, 1)

                val delivery = Delivery()
                delivery.address = member.address
                val order = Order.of(member, delivery, mutableListOf(orderItem1, orderItem2))
                em.persist(order);
            }

            private fun createMember(name: String, city: String, street: String, zipcode: String): Member {
                val member = Member(name);
                member.address = Address(city, street, zipcode)
                return member
            }
        }
    }
}