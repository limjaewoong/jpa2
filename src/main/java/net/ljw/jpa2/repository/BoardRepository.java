package net.ljw.jpa2.repository;

import net.ljw.jpa2.domain.Board;
import net.ljw.jpa2.dto.BoardIf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer> {

    //jpql 사용 가능
    //jpql 은 SQL과 모양이 비슷하지만, SQL이 아니다.
    //jpql 은 객체지향 언어이다.
    //from 뒤에는 entity이다.
    // Board 엔티티들을 조회하라.
    // fetch조인 성능향상 (user 엔티티의 컬럼값도 같이 조회함) 일반조인에서는 사용x
    @Query(value = "select b from Board b inner join fetch b.user")
    List<Board> getBoards();

    @Query(value = "select count(b) from Board b")
    Long getBoardsCount();

    //관리자 권한을 가진 게시물 건수
    @Query(value = "select b,u from Board b inner join b.user u inner join u.roles r where r.name = :roleName ")
    List<Board> getBoardsCountByAdmin(@Param("roleName") String roleName);

    @Query(value = "select b.*" +
            " from board b inner join users u " +
            " on b.user_id = u.user_id " +
            " left outer join user_role ur " +
            " on u.user_id = ur.user_id " +
            " inner join role r " +
            " on ur.role_id = r.role_id " +
            " where r.name =:roleName2 ", nativeQuery = true)
    List<BoardIf> getBoardsWithNativeQuery(@Param("roleName2") String roleName);
}
