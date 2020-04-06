package chess.controller.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CurrentTeamDAOTest {
    private CurrentTeamDAO currentTeamDAO;

    @BeforeEach
    private void setUp() {
        this.currentTeamDAO = new CurrentTeamDAO();
    }

    @Disabled
    @DisplayName("현재 팀 정보 추가")
    @Test
    void addCurrentTeamTest() throws Exception {
        this.currentTeamDAO.addCurrentTeam(2, "white");
    }

    @Disabled
    @DisplayName("현재 팀 정보 업데이트")
    @Test
    void updateCurrentTeamTest() throws Exception {
        this.currentTeamDAO.updateCurrentTeam(2, "black");
    }

    @Disabled
    @DisplayName("현재 팀 삭제")
    @Test
    void deleteCurrentTeamTest() throws Exception {
        this.currentTeamDAO.deleteCurrentTeam(2);
    }
}
