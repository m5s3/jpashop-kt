package jpabook.jpashop

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
class MemberRepositoryTest {
    @Autowired
    lateinit var memberRepository: MemberRepository

    @Test
    @Transactional
    @Rollback(false)
    fun testMember() {
        // Given
        val member = Member()
        member.username = "memberA"

        // When
        val savedId = memberRepository.save(member)
        val findMember = memberRepository.find(savedId)

        // Then
        assertThat(findMember.id).isEqualTo(member.id)
        assertThat(findMember.username).isEqualTo(member.username)

        assertThat(findMember).isEqualTo(member)
    }
}