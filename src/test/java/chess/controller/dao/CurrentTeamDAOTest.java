package chess.controller.dao;

import org.assertj.core.api.Assertions;
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
        CurrentTeam currentTeam = new CurrentTeam("white");

        this.currentTeamDAO.addCurrentTeam(2, currentTeam);
    }

    @Disabled
    @DisplayName("현재 팀 정보 업데이트")
    @Test
    void updateCurrentTeamTest() throws Exception {
        CurrentTeam currentTeam = new CurrentTeam("black");

        this.currentTeamDAO.updateCurrentTeam(2, currentTeam);
    }

    @Disabled
    @DisplayName("현재 팀 삭제")
    @Test
    void deleteCurrentTeamTest() throws Exception {
        this.currentTeamDAO.deleteCurrentTeam(2);
    }

    @Disabled
    @DisplayName("현재 팀 검색")
    @Test
    void findCurrentTeamTest() throws Exception {
        CurrentTeam currentTeam = currentTeamDAO.findCurrentTeam(3);

        Assertions.assertThat(currentTeam).isNotNull();
        Assertions.assertThat(currentTeam.getCurrentTeam()).isEqualTo("white");
    }
}
