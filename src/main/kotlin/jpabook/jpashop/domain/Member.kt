package jpabook.jpashop.domain

import jakarta.persistence.*

@Entity
class Member(
    @OneToMany(mappedBy = "member")
    var orders: MutableList<Order> = mutableListOf(),

) {
    @Id @GeneratedValue
    @Column(name = "member_id")
    var id: Long? = null

    @Embedded var address: Address? = null

    var name: String? = null
}
