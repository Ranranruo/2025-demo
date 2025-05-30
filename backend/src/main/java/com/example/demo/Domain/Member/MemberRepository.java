package com.example.demo.Domain.Member;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query(value = "SELECT m FROM Member m WHERE m.username = :username")
    public Optional<Member> findByUsername(@Param("username") String username);

    @Query(value = """
    SELECT EXISTS(
        SELECT 1 FROM Member WHERE username = :username
    )
    """, nativeQuery = true)
    boolean existsMemberByUsername(String username);

    @Query(value = """
    SELECT EXISTS(
        SELECT 1 FROM Member WHERE email = :email
    )
    """, nativeQuery = true)
    boolean existsMemberByEmail(String email);
}
