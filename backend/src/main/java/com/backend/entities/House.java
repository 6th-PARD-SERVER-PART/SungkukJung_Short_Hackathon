package com.backend.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "HOUSE")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HOUSEID")
    private Long houseId;

    @Column(name = "HOUSENAME")
    private String houseName;

    @Column(name = "INVITECODE")
    private String inviteCode;

    @OneToMany(mappedBy = "house", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Member> members = new ArrayList<>();
}
