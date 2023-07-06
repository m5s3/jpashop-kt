package jpabook.jpashop.domain

import jakarta.persistence.Embeddable

@Embeddable
data class Address(
    val city: String? = null,
    val street: String? = null,
    val zipcode: String? = null,
)
