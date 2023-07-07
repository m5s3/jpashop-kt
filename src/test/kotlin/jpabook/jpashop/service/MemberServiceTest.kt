package jpabook.jpashop.service

import jpabook.jpashop.domain.Member
import jpabook.jpashop.repository.MemberRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import java.lang.IllegalStateException

@SpringBootTest
@Transactional
class MemberServiceTest(
    @Autowired
    val memberService: MemberService,
    @Autowired
    val memberRepository: MemberRepository,
) {
    @DisplayName("회원가입")
    @Test
    fun join() {
        // Given
        val member: Member = Member("park")

        // When
        val savedId: Long = memberService.join(member)

        // Then
        assertEquals(member, memberRepository.findOne(savedId))
    }

    @DisplayName("중복 회원 예회")
    @Test
    fun validateDuplicateName() {
        // Given
        val member1: Member = Member()
        member1.name = "park"
        val member2: Member = Member()
        member2.name = "park"

        // When
        memberService.join(member1)
        try {
            memberService.join(member2)
        } catch (e: IllegalStateException) {
            return
        }

        // Then
        fail("예외가 발생해야 한다.")
    }
}