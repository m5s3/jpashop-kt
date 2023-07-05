package jpabook.jpashop.domain

import jakarta.persistence.*
import jakarta.persistence.FetchType.*

@Entity
class Delivery(
) {
    @Id @GeneratedValue
    @Column(name = "delivery_id")
    var id: Long? = null

    @OneToOne(mappedBy = "delivery", fetch = LAZY)
    var order: Order? = null

    @Embedded
    var address: Address? = null

    @Enumerated(EnumType.STRING)
    var status: DeliveryStatus? = null
}
