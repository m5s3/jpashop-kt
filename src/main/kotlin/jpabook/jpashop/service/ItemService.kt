package jpabook.jpashop.service

import jpabook.jpashop.domain.item.Item
import jpabook.jpashop.repository.ItemRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ItemService(
    private val itemRepository: ItemRepository
) {
    @Transactional
    fun saveItem(item: Item) {
        itemRepository.save(item)
    }

    @Transactional(readOnly = true)
    fun findItems(): List<Item> {
        return itemRepository.findAll()
    }

    @Transactional(readOnly = true)
    fun findOne(itemId: Long): Item {
        return itemRepository.findOne(itemId)
    }
}