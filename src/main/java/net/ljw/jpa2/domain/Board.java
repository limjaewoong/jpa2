package net.ljw.jpa2.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "board")
public class Board {

    @Id
    @Column(name = "board_id", length = 11)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer boardId;

    @Column(name = "title", length = 100)
    private String title;

    @Lob
    private String content;

    @Column(name = "view_cnt")
    private Integer viewCnt;

    @CreationTimestamp  //현재시간이 저장될 때 자동으로 생성
    private LocalDateTime regDate;

    @ManyToOne(fetch = FetchType.LAZY)
    // FetchType.EAGER 무조건 데이터를 가지고와라
    // FetchType.LAZY 필요시 쿼리 실행하여 가지고 옴
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public String toString() {
        return "Board{" +
                "boardId=" + boardId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", viewCnt=" + viewCnt +
                ", regDate=" + regDate +
                '}';
    }
}
