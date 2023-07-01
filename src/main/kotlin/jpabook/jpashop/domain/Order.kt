package jpabook.jpashop.domain

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "orders")
class Order(
    @Id @GeneratedValue
    @Column(name = "order_id")
    var id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "member_id")
    var member: Member,

    @OneToMany(mappedBy = "order")
    var orderItem: MutableList<OrderItem> = mutableListOf(),

    @OneToOne
    @JoinColumn(name = "delivery_id")
    var delivery: Delivery,

    var localDateTime: LocalDateTime,

    @Enumerated(EnumType.STRING)
    var status: OrderStatus,
)
