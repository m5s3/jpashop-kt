package jpabook.jpashop.domain

import jakarta.persistence.*
import jakarta.persistence.FetchType.*
import jpabook.jpashop.domain.item.Item

@Entity
class OrderItem {
    @Id @GeneratedValue
    @Column(name = "order_item_id")
    var id: Long? = null

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    var item: Item? = null

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    var order: Order? = null

    var orderPrice = 0
    var count = 0

    //==비지니스 로직==//
    fun cancel() {
        item?.addStock(count)
    }

    //==조회 로직==/
    /**
     * 주문 상품 전체 가격 조회
     */
    fun getTotalPrice(): Int {
        return orderPrice * count
    }
}
