package com.mysite.sbb3;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
//@Setter
@RequiredArgsConstructor
// hello, lombok을 필요로 하는 생성자가 롬복에 의해 자동 생성
public class HelloLombok {
    // final : 뒤에 따라오는 자료형과 변수 등 변경 불가
    // 값 변경 불가 --> @Setter 사용 불가
    private final String hello;
    private final int Lombok;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok("헬로", 5);
//        helloLombok.setHello("헬로");
//        helloLombok.setLombok(5);

        System.out.println(helloLombok.getHello());
        System.out.println(helloLombok.getLombok());
    }
}
