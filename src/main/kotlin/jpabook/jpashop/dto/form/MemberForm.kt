package jpabook.jpashop.dto.form

import jakarta.validation.constraints.NotEmpty


data class MemberForm(
    @field:NotEmpty(message = "Member name is required.")
    val name: String? = null,

    val city: String? = null,
    val street: String? = null,
    val zipcode: String? = null,
)
