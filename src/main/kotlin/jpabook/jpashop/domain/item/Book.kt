package jpabook.jpashop.domain.item

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue("B")
class Book() : Item() {
//    override var price: Int = 0
//    override var name: String = ""
//    override var stockQuantity: Int = 0
    var author: String? = null
    var isbn: String? = null
}
