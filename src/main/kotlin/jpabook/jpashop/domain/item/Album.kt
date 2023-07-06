package jpabook.jpashop.domain.item

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue("A")
class Album (
    name: String,
    price: Int,
    stockQuantity: Int,
    artist: String,
    etc: String,
) : Item(name = name, price = price, stockQuantity = stockQuantity)