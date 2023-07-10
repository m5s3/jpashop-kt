package jpabook.jpashop.repository

import jakarta.persistence.EntityManager
import jakarta.persistence.TypedQuery
import jakarta.persistence.criteria.*
import jpabook.jpashop.domain.Order
import jpabook.jpashop.dto.OrderSearch
import org.springframework.stereotype.Repository
import org.springframework.util.StringUtils

@Repository
class OrderRepository(
    private val em: EntityManager
) {
    fun save(order: Order) {
        em.persist(order)
    }

    fun findOne(orderId: Long): Order {
        return em.find(Order::class.java, orderId)
    }

    fun findAllByString(orderSearch: OrderSearch): List<Order?>? {
        var jpql = "select o from Order o join o.member m"
        var isFirstCondition = true

        //주문 상태 검색
        if (orderSearch.orderStatus != null) {
            if (isFirstCondition) {
                jpql += " where"
                isFirstCondition = false
            } else {
                jpql += " and"
            }
            jpql += " o.status = :status"
        }

        //회원 이름 검색
        if (StringUtils.hasText(orderSearch.memberName)) {
            if (isFirstCondition) {
                jpql += " where"
                isFirstCondition = false
            } else {
                jpql += " and"
            }
            jpql += " m.name like :name"
        }
        var query: TypedQuery<Order?> = em.createQuery(jpql, Order::class.java)
            .setMaxResults(1000)
        if (orderSearch.orderStatus != null) {
            query = query.setParameter("status", orderSearch.orderStatus)
        }
        if (StringUtils.hasText(orderSearch.memberName)) {
            query = query.setParameter("name", orderSearch.memberName)
        }
        return query.resultList
    }

    /**
     * JPA Criteria
     */
    fun findAllByCriteria(orderSearch: OrderSearch): List<Order?>? {
        val cb: CriteriaBuilder = em.criteriaBuilder
        val cq: CriteriaQuery<Order> = cb.createQuery(Order::class.java)
        val o: Root<Order> = cq.from(Order::class.java)
        val m: Join<Any, Any> = o.join("member", JoinType.INNER)
        val criteria: MutableList<Predicate> = ArrayList<Predicate>()

        //주문 상태 검색
        if (orderSearch.orderStatus != null) {
            val status: Predicate = cb.equal(o.get<String>("status"), orderSearch.orderStatus)
            criteria.add(status)
        }
        //회원 이름 검색
        if (StringUtils.hasText(orderSearch.memberName)) {
            val name: Predicate = cb.like(m.get<String>("name"), "%${orderSearch.memberName}%")
            criteria.add(name)
        }
        cq.where(cb.and(*criteria.toTypedArray()))
        val query: TypedQuery<Order> = em.createQuery(cq).setMaxResults(1000)
        return query.resultList
    }
}