package jpabook.jpashop.domain

import jakarta.persistence.*
import jakarta.persistence.FetchType.*

@Entity
class Delivery(
    @Id @GeneratedValue
    @Column(name = "delivery_id")
    var id: Long? = null,

    @OneToOne(mappedBy = "delivery", fetch = LAZY)
    var order: Order,

    @Embedded
    var address: Address,

    @Enumerated(EnumType.STRING)
    var status: DeliveryStatus
)
