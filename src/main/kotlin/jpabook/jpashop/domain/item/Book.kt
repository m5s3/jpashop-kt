package jpabook.jpashop.domain.item

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue("B")
class Book(
    name: String,
    price: Int,
    stockQuantity: Int,
    author: String? = null,
    isbn: String? = null,
) : Item(name = name, price = price, stockQuantity = stockQuantity)

//class Book(name: String, price: Int, stockQuantity: Int, author: String, isbn: String) :
//    Item(name, price, stockQuantity) {
//    var author: String = author
//    var isbn: String = isbn
//}