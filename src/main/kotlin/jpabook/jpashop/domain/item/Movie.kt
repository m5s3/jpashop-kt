package jpabook.jpashop.domain.item

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue("M")
class Movie() : Item() {
    val director: String = ""
    val actor: String = ""
}
