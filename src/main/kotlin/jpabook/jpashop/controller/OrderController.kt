package jpabook.jpashop.controller

import jpabook.jpashop.dto.OrderSearch
import jpabook.jpashop.service.ItemService
import jpabook.jpashop.service.MemberService
import jpabook.jpashop.service.OrderService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class OrderController(
    private val memberService: MemberService,
    private val itemService: ItemService,
    private val orderService: OrderService
) {
    @GetMapping("/order")
    fun createOrderForm(model: Model): String {
        val members = memberService.findMembers()
        val items = itemService.findItems()

        model.addAttribute("members", members)
        model.addAttribute("items", items)

        return "order/orderForm"
    }

    @PostMapping("/order")
    fun order(@RequestParam("memberId") memberId: Long,
              @RequestParam("itemId") itemId: Long,
              @RequestParam("count") count: Int): String {
        orderService.order(memberId, itemId, count)
        return "redirect:/orders"
    }

    @GetMapping("/orders")
    fun orderList(@ModelAttribute("orderSearch") orderSearch: OrderSearch, model: Model): String {
        val orders = orderService.findOrder(orderSearch)
        model.addAttribute("orders", orders)
        return "order/orderList"
    }

    @PostMapping("/orders/{orderId}/cancel")
    fun cancelOrder(@PathVariable orderId: Long): String {
        orderService.cancelOrder(orderId)
        return "redirect:/orders"
    }
}