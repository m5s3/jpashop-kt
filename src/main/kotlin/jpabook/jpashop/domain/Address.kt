package jpabook.jpashop.domain

import jakarta.persistence.Embeddable

@Embeddable
data class Address(
    var city: String? = null,
    var street: String? = null,
    var zipcode: String? = null,
)
