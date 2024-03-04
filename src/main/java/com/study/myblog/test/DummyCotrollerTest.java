package com.study.myblog.test;

import com.study.myblog.model.RoleType;
import com.study.myblog.model.User;
import com.study.myblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

@RestController // html 파일이 아니라 Data를 리턴해주는 controller = RestController
public class DummyCotrollerTest {
    @Autowired  // 의존성 주입
    private UserRepository userRepository;  // DummyCotrollerTest가 메모리에 띄어줄 때 같이 UserRepository도 띄어준다.

    @Transactional  // 함수 종료시에 자동 commit이 됨.
    @PutMapping("/dummy/user/{id}") // detail 메소드와 주소가 동일한데 괜찮은 이유는 PutMapping이라서 ! 상관 없음
    public User updateUser(@PathVariable int id, @RequestBody User requestUser){    // email, password
        // json 데이터를 요청 => Java Object(MessageConverter의 Jackson 라이브러리가 변환해서 받아줌)
        System.out.println("id : " + id);
        System.out.println("password : " + requestUser.getPassword());
        System.out.println("email : " + requestUser.getEmail());
        // form으로 안받고 json으로 받을 때는 @RequestBody를 사용

        // update 할 때 사용 유저를 먼저 찾기
        User user = userRepository.findById(id).orElseThrow(()->{
            // 없다면?
            return new IllegalArgumentException("수정에 실패하였습니다.");
        });
        // 있으면? 유저 객체 데이터 변경
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());

        // userRepository에 user를 save
//        userRepository.save(user);
        // save 함수는 id를 전달하지 않으면 insert 해주고
        // save 함수는 id를 전달하면 해당 id에 대한 데이터가 있을 때 update, 없을 때 insert를 한다.

        // 더티 체킹
        return user;
    }

    @GetMapping("/dummy/users")  // http://localhost:8096/myblog/dummy/users
    public List<User> list(){
        return userRepository.findAll();
    }

    @GetMapping("/dummy/user")    // 한 페이지당 2건의 데이터를 리턴받아 볼 예정
    public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        Page<User> pagingUser = userRepository.findAll(pageable);
        // 밑에 나오는 total page 수 이런 거 보기 싫으면?
        List<User> users = pagingUser.getContent();
        return users;
    }

    @GetMapping("/dummy/user/{id}") // {id} -> 주소로 파라미터를 전달 받을 수 있음
    public User detail(@PathVariable int id){
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

    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable int id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다.";
        }

        return "삭제되었습니다. id : " + id;
    }

}
