package com.example.chattingroom.repository;

import com.example.chattingroom.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
