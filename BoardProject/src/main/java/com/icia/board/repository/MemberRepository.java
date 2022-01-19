package com.icia.board.repository;

import com.icia.board.entity.CommentEntity;
import com.icia.board.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity,Long> {
    MemberEntity findByMemberEmail(String b_writer);
}
