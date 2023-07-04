package jpabook.jpashop.repository

import jakarta.persistence.EntityManager
import jpabook.jpashop.domain.item.Item
import org.springframework.stereotype.Repository

@Repository
class ItemRepository(
    private val em: EntityManager
) {
    fun save(item: Item) {
        if (item.id == null) {
            em.persist(item)
        } else {
            em.merge(item)
        }
    }

    fun findOne(itemId: Long): Item {
        return em.find(Item::class.java, itemId)
    }

    fun findAll(): List<Item> {
        return em.createQuery("SELECT i FROM Item i", Item::class.java)
            .resultList
    }
}