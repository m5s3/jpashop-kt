package jpabook.jpashop.domain

import jakarta.persistence.*
import jakarta.persistence.FetchType.*
import jpabook.jpashop.domain.item.Item

@Entity
class OrderItem(
    @Id @GeneratedValue
    @Column(name = "order_item_id")
    var id: Long? = null,

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    var item: Item,

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    var order: Order,

    var orderPrice: Int,
    var count: Int,
)
