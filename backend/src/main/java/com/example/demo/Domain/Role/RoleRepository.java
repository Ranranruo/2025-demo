package com.example.demo.Domain.Role;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query(value = """
    SELECT
        r.*
    FROM
        Role r 
        INNER JOIN
        Member_Role_Bridge mrb
        ON 
        r.id = mrb.role_id
        INNER JOIN
        Member m
        ON
        m.id = mrb.member_id
    WHERE
        m.id = :id
    """, nativeQuery = true)
    public Set<Role> findAllByMemberId(@Param("id") Long id);

    @Query(value = """
    SELECT
        *
    FROM
        Role
    WHERE
        name = :name
    """, nativeQuery = true)
    public Role findByName(@Param("name") String name);
}
