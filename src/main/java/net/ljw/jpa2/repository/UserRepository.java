package net.ljw.jpa2.repository;

import net.ljw.jpa2.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    //SELECT문
    Optional<User> findByNameAndEmail(String name, String email); // 이름이랑 이메일이 같은
    Optional<User> findByEmail(String email); // 이메일이 같은
    List<User> findByNameOrEmail(String name, String email);    // 이름 또는 이메일이 같은
    List<User> findByUserIdBetween(Integer start, Integer end); // userid 범위
    List<User> findByUserIdLessThan(Integer start); // 작다
    List<User> findByUserIdLessThanEqual(Integer start); // 작거나 같다
    List<User> findByUserIdGreaterThan(Integer start); //크다
    List<User> findByUserIdGreaterThanEqual(Integer start); //크거나 같다
    List<User> findByRegDateAfter(LocalDateTime date); //날짜 이후
    List<User> findByRegDateBefore(LocalDateTime date); //날짜 이전
    List<User> findByRegDateIsNull(); //날짜 null
    List<User> findByRegDateIsNotNull(); //날짜 not null
    List<User> findByNameLike(String name); // 이름 %
    List<User> findByNameStartingWith(String name); //입력한 값으로 시작 임%
    List<User> findByNameEndingWith(String name); //입력한 값으로 끝남 %임
    List<User> findByNameContaining(String name); // 입력한 이름이 포함된 %임%
    List<User> findByNameOrderByNameDesc(String name);  // 이름 조건인 값 DESC 정렬
    List<User> findByNameNot(String name);  // 이름 조건이 아닌값 * null은 비교하지 않는다
    List<User> findByUserIdIn(List<Integer> ids);   // in 쿼리
    List<User> findByUserIdNotIn(List<Integer> ids);   // not in 쿼리
    //List<User> findByFlagTrueOrderByUserIdDesc();   // flag값이 true인 값만 userid desc정렬

    // select distinct * from users where user_id = ?
    List<User> findDistinctByName(String name); // distinct 다음 조회 컬럼 값이 없어서 중복 제거 안됨
    // select * from user3 where name = ? limit 2
    List<User> findFirst2ByName(String name);

    //pageing 처리
    Page<User> findBy(Pageable pageable);
    Page<User> findByName(String name, Pageable pageable);
    Page<User> findByNameContaining(String name, Pageable pageable);
    Page<User> findByNameContainingOrEmailContaining(String name,String email,Pageable pageable);   // 이름+이메일 검색 페이지

    // Count 함수

    long count();   // 전체 건수
    int countByName(String name);   // 이름인것 건수
    int countByNameLike(String name);   // 이름% 인것 건수
    boolean existsByName(String name);  // 이름이 존재하는지 여부

    // delete
    int deleteByName(String name);  //이름인것 삭제 / 삭제 건수 리턴
}
