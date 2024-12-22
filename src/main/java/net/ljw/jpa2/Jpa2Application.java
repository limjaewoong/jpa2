package net.ljw.jpa2;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import net.ljw.jpa2.domain.Board;
import net.ljw.jpa2.domain.Role;
import net.ljw.jpa2.domain.User;
import net.ljw.jpa2.dto.BoardIf;
import net.ljw.jpa2.repository.BoardRepository;
import net.ljw.jpa2.repository.RoleRepository;
import net.ljw.jpa2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class Jpa2Application{

    public static void main(String[] args) {
        SpringApplication.run(Jpa2Application.class, args);
    }

 /*
    @Autowired
    EntityManagerFactory entityManagerFactory;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    BoardRepository boardRepository;


    @Override
    @Transactional // 메소드가 시작할때 트랜잭션이 실행되고, 메소드가 종료될때 트랜잭션이 commit
    public void run(String... args) throws Exception {
        // stream filter
        List<User> user = userRepository.findAll().stream()
                .filter(x -> x.getEmail().length() > 5).collect(Collectors.toList());
        System.out.println("user: "+user);
        // map 사용법
        String email = userRepository.findAll().stream()
                .map(User::getEmail).findFirst().orElseThrow();
        System.out.println("email: "+email);

        User user1 = userRepository.findByNameAndEmail("임재웅2","bb@naver.com").orElseThrow(null);
        List<User> listUser = userRepository.findByNameOrEmail("임재웅2","bb@naver.com");
        List<User> listUser2 = userRepository.findByUserIdBetween(1,4);
        List<User> listUser3 = userRepository.findByUserIdLessThan(3);
        List<User> listUser4 = userRepository.findByUserIdLessThanEqual(3);
        List<User> listUser5 = userRepository.findByUserIdGreaterThanEqual(2);
        List<User> listUser6 = userRepository.findByRegDateAfter(LocalDateTime.now().minusDays(26));
        List<User> listUser7 = userRepository.findByRegDateIsNull();
        List<User> listUser8 = userRepository.findByNameLike("임%");
        List<User> listUser9 = userRepository.findByNameStartingWith("임");
        List<User> listUser10 = userRepository.findByNameContaining("임");
        List<User> listUser11 = userRepository.findByUserIdIn(List.of(1,2,3));
        System.out.println("user1: "+user1);
        System.out.println("listUser: "+listUser);
        System.out.println("listUser2: "+listUser2);
        System.out.println("listUser3: "+listUser3);
        System.out.println("listUser4: "+listUser4);
        System.out.println("listUser5: "+listUser5.stream().toList());
        System.out.println("listUser7: "+listUser7.stream().toList());
        System.out.println("listUser8: "+listUser8.stream().toList());
        System.out.println("listUser9: "+listUser9.stream().toList());
        System.out.println("listUser10: "+listUser10.stream().toList());
        System.out.println("listUser11: "+listUser11.stream().toList());
        //EntityManager entityManager = entityManagerFactory.createEntityManager();

        //EntityTransaction transaction = entityManager.getTransaction();

        long allCnt = userRepository.count();
        int allCnt2 = userRepository.countByName("임재웅2");
        boolean nameYn = userRepository.existsByName("임재웅3");
        System.out.println("allCnt: "+allCnt);
        System.out.println("allCnt2: "+allCnt2);
        System.out.println("nameYn: "+(nameYn ? "Y" : "N"));

        //paging
        Page<User> pageList = userRepository.findBy(
                PageRequest.of(0,2,Sort.by(Sort.Direction.DESC,"regDate"))
        );

        Page<User> pageList2 = userRepository.findByName("임재웅2",
                PageRequest.of(0,2,Sort.by(Sort.Direction.DESC,"regDate"))
        );

        System.out.println("pageList: "+pageList.stream().collect(Collectors.toList()));
        System.out.println("pageList2: "+pageList.stream().collect(Collectors.toList()));

        List<User> userList = userRepository.findAll();
        for (User users : userList) {
            System.out.println("user: "+users);
            for (Role role :users.getRoles()){
                System.out.println("role: "+role);
            }
            System.out.println("=========================");
        }

        boardRepository.findAll();
        //List<Board> boardList = boardRepository.findAll();
        List<Board> boardList = boardRepository.getBoards();
        for (Board board : boardList) {
            System.out.println("board: "+board);
            System.out.println("user: "+board.getUser());
        }
        System.out.println(boardRepository.getBoardsCount());

        User user2 = userRepository.findById(3).get();
        Board board2 = new Board();
        board2.setUser(user2);
        board2.setRegDate(LocalDateTime.now());
        board2.setTitle("관리자님의 글");
        board2.setContent("내용입니다.");

        boardRepository.save(board2);

        //System.out.println(boardRepository.getBoardsCountByAdmin("ROLE_ADMIN"));

        List<BoardIf> bList = boardRepository.getBoardsWithNativeQuery("ROLE_MASTER");
        for (BoardIf board : bList) {
            System.out.println("board: "+board.getTitle());
        }

        System.out.println();
        try {
            //transaction.begin();
            // jpa 코드
            //User user = new User();
            //user.setUserId(2);
            //user.setName("임재웅2");
            //user.setEmail("bb@naver.com");
            //user.setPassword("12356");

            //entityManager.persist(user);

            //User user = entityManager.find(User.class,2);
            //user.setName("김또깡");
            //entityManager.persist(user);
            //entityManager.remove(user);
            //System.out.println(user);

            //transaction.commit();

            //User user2 = userRepository.findById(2).orElseThrow(); //Java Optional 이라고 불리는 문법.  null을 다루는 기술
            //System.out.println(user2);

            //입력
            User member = new User();
            member.setEmail("cc@naver.com");
            member.setPassword("123456");
            member.setName("임재웅2");
            member.setUserId(6);
            User saveUser = userRepository.save(member);

            User member3 = userRepository.findById(6).orElseThrow(null);
            member3.setName("김김김");

            //삭제
            //Optional<User> member2 = userRepository.findById(5);
            //userRepository.delete(member2.get());

        } catch (Exception e) {
            //transaction.rollback();
        }finally {
            //entityManager.close();
        }
    }

    */
}
