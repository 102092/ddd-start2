package com.myshop.order.command.domain;

import com.myshop.member.command.domain.MemberId;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Orderer {

    // MemberId은  @Column(name = "member_id") 으로 id가 설정되어있음.
    // 그런데 Orderer에 설정된 @Column은 orderer_id이므로 위와 다르다!
    // 그래서 AttributeOverride 속성명을 재정의했음.
    // MemberId의 id라는 필드는 테이블의 컬럼명의 orderer_id와 매칭된다는 의미
    @AttributeOverrides(
            @AttributeOverride(name = "id", column = @Column(name = "orderer_id"))
    )
    private MemberId memberId;

    @Column(name = "orderer_name")
    private String name;

    protected Orderer() {
    }

    public Orderer(MemberId memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }

    public MemberId getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orderer orderer = (Orderer) o;
        return Objects.equals(memberId, orderer.memberId) &&
                Objects.equals(name, orderer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId, name);
    }
}
