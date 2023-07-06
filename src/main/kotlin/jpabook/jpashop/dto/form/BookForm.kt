package jpabook.jpashop.dto.form

data class BookForm(
    val id: Long? = null,
    val name: String? = null,
    val price: Int? = 0,
    val stockQuantity: Int? = 0,
    val author: String? = null,
    val isbn: String? = null,
)
