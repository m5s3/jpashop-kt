package jpabook.jpashop.controller

import jakarta.validation.Valid
import jpabook.jpashop.domain.Address
import jpabook.jpashop.domain.Member
import jpabook.jpashop.dto.form.MemberForm
import jpabook.jpashop.service.MemberService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class MemberController(
    private val memberService: MemberService,
) {

    @RequestMapping("/members/new")
    fun createForm(model: Model): String {
        model.addAttribute("memberForm", MemberForm())
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    fun create(@Valid form: MemberForm, result: BindingResult): String {
        if (result.hasErrors()) {
            return "members/createMemberForm";
        }
        val address: Address = Address(form.city, form.street, form.zipcode)
        val member: Member = Member()
        member.name = form.name
        member.address = address

        memberService.join(member)

        return "redirect:/"
    }
}