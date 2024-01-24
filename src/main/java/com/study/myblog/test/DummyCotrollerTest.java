package com.study.myblog.test;

import com.study.myblog.model.RoleType;
import com.study.myblog.model.User;
import com.study.myblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Supplier;

@RestController // html 파일이 아니라 Data를 리턴해주는 controller = RestController
public class DummyCotrollerTest {
    @Autowired  // 의존성 주입
    private UserRepository userRepository;  // DummyCotrollerTest가 메모리에 띄어줄 때 같이 UserRepository도 띄어준다.

    @GetMapping("/dummy/user/{id}") // {id} -> 주소로 파라미터를 전달 받을 수 있음
    public User detail(@PathVariable long id){
        // user/4를 찾으면 내가 디비에서 못 찾아오게 될 때 user가 null이 될 것 아냐?
        // 그럼 return null 이 리턴 돼! 그럼 프로그램에 문제가 생기지 않겠니?
        // Optional로 너의 User 객체를 감싸서 가져올테니 null 인지 아닌지 판단해서 return 해
//        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
//            @Override
//            public IllegalArgumentException get() {
//                return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);
//            }
//        });
        // 람다식
        User user = userRepository.findById(id).orElseThrow(()-> {
            return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);
        });
        // 요청 : 웹브라우저
        // User 객체 = 자바 오브젝트
        // 웹 브라우저가 이해할 수 있는 데이터로 변환 해줘야함 -> JSON
        // 스프링부트 = MessageConverter라는 애가 응답시 자동으로 작동
        // 만약 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson 라이브러리를 호출해서
        // user 오브젝트를 json으로 변환해서 브라우저에게 던져준다.
        return user;
    }

    @PostMapping("/dummy/join")
    public String join(User user){  // key=value (약속된 규칙)
        // http://localhost:8096/myblog/dummy/join(요청)
        // http의 body에 username, password, email 데이터를 가지고 (요청) -> 굳이 @RequestParam()할 필요가 없다.
        System.out.println("id : " + user.getId());
        System.out.println("username : " + user.getUsername());
        System.out.println("password : " + user.getPassword());
        System.out.println("email : " + user.getEmail());
        System.out.println("role : " + user.getRole());
        System.out.println("createDate : " + user.getCreateDate());

        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "회원가입이 완료되었습니다.";
    }
}
