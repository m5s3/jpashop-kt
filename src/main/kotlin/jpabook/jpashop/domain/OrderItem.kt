package jpabook.jpashop.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jpabook.jpashop.domain.item.Item

@Entity
class OrderItem(
    @Id @GeneratedValue
    @Column(name = "order_item_id")
    var id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "item_id")
    var item: Item,

    @ManyToOne
    @JoinColumn(name = "order_id")
    var order: Order,

    var orderPrice: Int,
    var count: Int,
)
