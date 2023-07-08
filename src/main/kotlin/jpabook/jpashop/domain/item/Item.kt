package jpabook.jpashop.domain.item

import jakarta.persistence.*
import jpabook.jpashop.domain.Category
import jpabook.jpashop.exception.NotEnoughStockException

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
abstract class Item(
) {
    @Id @GeneratedValue
    @Column(name = "item_id")
    var id:Long? = null

    @ManyToMany(mappedBy = "items")
    var categories: MutableList<Category> = mutableListOf()

    var name: String = ""
    var price: Int = 0
    var stockQuantity: Int = 0
    fun addStock(quantity: Int) {
        this.stockQuantity += quantity
    }

    fun removeStock(quantity: Int) {
        val restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            println("restStock = ${restStock}")
            throw NotEnoughStockException("need more stock")
        }
        this.stockQuantity -= quantity
    }
}
