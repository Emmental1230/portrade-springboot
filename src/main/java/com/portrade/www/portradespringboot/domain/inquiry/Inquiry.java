package com.portrade.www.portradespringboot.domain.inquiry;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "inquiries")
public class Inquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inquiry_type_id")
    private InquiryType category;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = false, name = "phone_number")
    private String phoneNumber;

    @Column(nullable = false, name = "email")
    private String email;

    @Column(nullable = false, name = "title")
    private String title;

    @Column(nullable = false, name = "contents")
    private String contents;

    @Builder
    public Inquiry(Long id, InquiryType category, String name, String phoneNumber, String email, String title, String contents) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.title = title;
        this.contents = contents;
    }
}
