package jpabook.jpashop.domain.item

import jakarta.persistence.*
import jpabook.jpashop.domain.Category
import jpabook.jpashop.exception.NotEnoughStockException

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
class Item(
) {
    @Id @GeneratedValue
    @Column(name = "item_id")
    var id:Long? = null
    var name = ""

    var price = 0
    var stockQuantity = 0

    @ManyToMany(mappedBy = "items")
    var categories: MutableList<Category> = mutableListOf()
    fun addStock(quantity: Int) {
        this.stockQuantity += quantity
    }

    fun removeStock(quantity: Int) {
        val restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw NotEnoughStockException("need more stock")
        }
        this.stockQuantity -= quantity
    }
}
