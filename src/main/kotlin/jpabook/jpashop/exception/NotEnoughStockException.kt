package jpabook.jpashop.exception

class NotEnoughStockException(
    override val message: String
) : RuntimeException(message = message)