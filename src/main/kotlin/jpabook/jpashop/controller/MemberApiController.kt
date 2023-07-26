package jpabook.jpashop.controller

import jakarta.validation.Valid
import jpabook.jpashop.domain.Member
import jpabook.jpashop.dto.*
import jpabook.jpashop.service.MemberService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class MemberApiController(
    private val memberService: MemberService,
) {

    @GetMapping("/v2/members")
    fun member(): ResultDto<List<MemberDto>> {
        val members = memberService.findMembers()
        val memberDtos = members.map { it ->
            MemberDto(it.name)
        }
        return ResultDto(
            memberDtos.size,
            memberDtos
        )
    }

    @PostMapping("/v2/members")
    fun saveMember(@RequestBody @Valid request: CreateMemberRequest): CreateMemberResponse {
        val member = Member(request.name)
        val memberId = memberService.join(member)
        return CreateMemberResponse(memberId)
    }

    @PutMapping("/v2/members/{id}")
    fun updateMember(
        @PathVariable id: Long,
        @RequestBody request: UpdateMemberRequest,
        ): UpdateMemberResponse {
        memberService.update(id, request.name)
        val member = memberService.findOne(id)
        return UpdateMemberResponse(
            member.id!!,
            member.name
        )
    }
}