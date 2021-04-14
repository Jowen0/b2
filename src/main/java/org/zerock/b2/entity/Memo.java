package org.zerock.b2.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString //JPA를 할때는 조심해서 사용해야 한다. 원하지 않는 동작들이 일어날 수 있다.
public class Memo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno; //기본자료형 int가 아니라 null이 나올 수 있는 Long을 사용한다.

    private String memoText;

    public void changeText(String text) {

        this.memoText = text;

    }

}
