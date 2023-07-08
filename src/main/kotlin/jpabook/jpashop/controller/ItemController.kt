package jpabook.jpashop.controller

import jpabook.jpashop.domain.item.Book
import jpabook.jpashop.dto.form.BookForm
import jpabook.jpashop.service.ItemService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping


@Controller
class ItemController(
    private val itemService: ItemService
) {
    @GetMapping("/items/new")
    fun createForm(model: Model): String {
        model.addAttribute("form", BookForm())
        return "items/createItemForm"
    }

    @PostMapping("/items/new")
    fun create(bookForm: BookForm): String {
        val book = Book()
        book.id = bookForm.id
        book.name = bookForm.name!!
        book.price = bookForm.price!!
        book.stockQuantity = bookForm.stockQuantity!!
        book.author = bookForm.author
        book.isbn = bookForm.isbn
        itemService.saveItem(book)
        return "redirect:/"
    }

    @GetMapping("/items")
    fun list(model: Model): String {
        val items = itemService.findItems()
        model.addAttribute("items", items)
        return "items/itemList"
    }

    @GetMapping("/items/{itemId}/edit")
    fun updateItemForm(@PathVariable itemId: Long, model: Model): String {
        val book: Book = itemService.findOne(itemId) as Book
        val bookForm = BookForm()
        bookForm.id = book.id
        bookForm.name = book.name
        bookForm.price = book.price
        bookForm.stockQuantity = book.stockQuantity
        bookForm.author = book.author
        bookForm.isbn = book.isbn

        model.addAttribute("form", bookForm)

        return "items/updateItemForm";
    }

    @PostMapping("/items/{itemId}/edit")
    fun updateItem(@PathVariable itemId: Long, bookForm: BookForm): String {
        val book = Book()
        book.id = bookForm.id
        book.name = bookForm.name!!
        book.price = bookForm.price!!
        book.stockQuantity = bookForm.stockQuantity!!
        book.author = bookForm.author
        book.isbn = bookForm.isbn
        itemService.saveItem(book)
        return "redirect:/items"
    }
}