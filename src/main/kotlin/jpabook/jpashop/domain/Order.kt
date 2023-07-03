package jpabook.jpashop.domain

import jakarta.persistence.*
import jakarta.persistence.FetchType.*
import java.time.LocalDateTime

@Entity
@Table(name = "orders")
class Order(
    @Id @GeneratedValue
    @Column(name = "order_id")
    var id: Long? = null,

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    var member: Member,

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL])
    var orderItem: MutableList<OrderItem> = mutableListOf(),

    @OneToOne(fetch = LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "delivery_id")
    var delivery: Delivery,

    var localDateTime: LocalDateTime,

    @Enumerated(EnumType.STRING)
    var status: OrderStatus,
) {
    //==연관 관계 메서드==//
    fun setMember(member: Member) {
        this.member = member
        member.orders.add(this)
    }

    //==연관 관계 메서드==//
    fun setOrderItems(orderItem: OrderItem) {
        this.orderItem.add(orderItem)
        orderItem.order = this
    }

    //==연관 관계 메서드==//
    fun setDelivery(delivery: Delivery) {
        this.delivery = delivery
        delivery.order = this
    }
}
