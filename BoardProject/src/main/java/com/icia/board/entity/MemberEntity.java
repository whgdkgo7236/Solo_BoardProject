package com.icia.board.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "member_table")
public class MemberEntity extends  BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long id;

    @Column
    private String memberEmail;

    @Column
    private String memberPassword;

    @Column
    private String memberName;
    // 부모를 지우면 전체적으로 지워진다. on delete cascade를 할때
//    @OneToMany(mappedBy = "memberEntity",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
//    private List<BoardEntity> boardEntityList = new ArrayList<>();
//
//    @OneToMany(mappedBy = "memberEntity",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
//    private List<BoardEntity> commentEntityList = new ArrayList<>();
///////////////////////////////////////////////////////////////////////////////////////////////////
    // on delete set null을 할때
    @OneToMany(mappedBy = "memberEntity",cascade = CascadeType.PERSIST,orphanRemoval = false,fetch = FetchType.LAZY)
    private List<BoardEntity> boardEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "memberEntity",cascade = CascadeType.PERSIST,orphanRemoval = false,fetch = FetchType.LAZY)
    private List<BoardEntity> commentEntityList = new ArrayList<>();
    
    @PreRemove//Remove가 실행되기전에 실행된다.
    private void preRemove(){
        System.out.println("MemberEntity.preRemove");
        boardEntityList.forEach(board -> board.setMemberEntity(null));
//        for(BoardEntity board: boardEntityList){     //같은뜻
//            board.setMemberEntity(null);
//        }
        commentEntityList.forEach(comment -> comment.setMemberEntity(null));
    }
//////////////////////////////////////////////////////////////////////////////////////////////////

}
