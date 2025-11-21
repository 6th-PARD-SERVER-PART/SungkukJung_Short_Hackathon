package com.backend.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "COMMENT")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENTID")
    private Long CommentId;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "ISCOMPLETE")
    private Boolean isComplete;

    @Column(name = "ISCHECKED")
    private Boolean isChecked;

    @ManyToOne
    @JoinColumn(name = "MEMBERID")
    private Member member;
}
