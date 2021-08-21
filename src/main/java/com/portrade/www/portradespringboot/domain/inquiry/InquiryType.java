package com.portrade.www.portradespringboot.domain.inquiry;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "inquiry_types")
public class InquiryType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String desc;

    @Builder
    public InquiryType(Long id, String desc) {
        this.id = id;
        this.desc = desc;
    }
}
