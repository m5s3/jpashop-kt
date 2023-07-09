package jpabook.jpashop.domain

import jakarta.persistence.*
import jakarta.persistence.FetchType.*
import java.time.LocalDateTime
import java.util.function.ToIntFunction

@Entity
@Table(name = "orders")
class Order {
    @Id @GeneratedValue
    @Column(name = "order_id")
    var id: Long? = null

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    var member: Member? = null

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL])
    var orderItems: MutableList<OrderItem> = mutableListOf()

    @OneToOne(fetch = LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "delivery_id")
    var delivery: Delivery? = null

    var orderDate: LocalDateTime? = null

    @Enumerated(EnumType.STRING)
    var status: OrderStatus? = null

    //==연관 관계 메서드==//
    fun addMember(member: Member) {
        this.member = member
        member.orders.add(this)
    }

    //==연관 관계 메서드==//
    fun addOrderItems(orderItem: OrderItem) {
        this.orderItems.add(orderItem)
        orderItem.order = this
    }

    //==연관 관계 메서드==//
    fun addDelivery(delivery: Delivery) {
        this.delivery = delivery
        delivery.order = this
    }


    //==비지니스 로직==//
    fun cancel() {
        if (delivery?.status == DeliveryStatus.COMP) {
            throw IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다")
        }
        status = OrderStatus.CANCEL
        orderItems.map {
            it.cancel()
        }
    }

    //==조회 로직==//
    /**
     * 전체 주문 가격 조회
     */
    fun getTotalPrice(): Int = orderItems.sumOf { it.getTotalPrice() }

    //==생성자 메서드==//
    companion object {
        fun of(member: Member, delivery: Delivery, orderItems: MutableList<OrderItem>): Order {
            return Order().apply {
                this.member = member
                this.delivery = delivery
                orderItems.map {
                    this.addOrderItems(it)
                }
                this.status = OrderStatus.ORDER
            }
        }
    }
}
