package net.ljw.jpa2.dto;

import java.time.LocalDateTime;

public interface BoardIf {
    public Integer getBoardId();
    public String getTitle();
    public String getContent();
    public Integer getUserId();
    public Integer getViewCnt();
    public LocalDateTime getRegDate();
}
