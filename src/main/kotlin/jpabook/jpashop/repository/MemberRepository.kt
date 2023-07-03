package jpabook.jpashop.repository

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jpabook.jpashop.domain.Member
import org.springframework.stereotype.Repository

@Repository
class MemberRepository(
    @PersistenceContext
    val em: EntityManager,
) {
    fun save(member: Member) {
        em.persist(member)
    }

    fun findOne(id: Long): Member {
        return em.find(Member::class.java, id);
    }

    fun findAll(): List<Member> {
        return em.createQuery("SELECT m FROM Member m", Member::class.java).resultList;
    }

    fun findByName(name: String): List<Member> {
        return em.createQuery("SELECT m FROM Member m WHERE m.name = :name", Member::class.java)
            .setParameter("name", name)
            .resultList
    }
}