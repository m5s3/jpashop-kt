package jpabook.jpashop.dto

data class ResultDto<T>(
    var count: Int,
    var data: T
)
