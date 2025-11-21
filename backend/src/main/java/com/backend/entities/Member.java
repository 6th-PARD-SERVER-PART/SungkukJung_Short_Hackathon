package com.backend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "MEMBER")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBERID")
    private Long memberId;

    @Column(name = "MEMBERNAME")
    private String memberName;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "IMAGEURL")
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "HOUSEID")
    private House house;

    @OneToMany(mappedBy = "member", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    @Builder.Default
    private List<Comment> comments = new ArrayList<>();
}
