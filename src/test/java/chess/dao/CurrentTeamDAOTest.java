package chess.dao;

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
        ChessBoard chessBoard = new ChessBoard(2);
        CurrentTeam currentTeam = new CurrentTeam("white");

        this.currentTeamDAO.addCurrentTeam(chessBoard, currentTeam);
    }

    @Disabled
    @DisplayName("현재 팀 정보 업데이트")
    @Test
    void updateCurrentTeamTest() throws Exception {
        ChessBoard chessBoard = new ChessBoard(2);
        CurrentTeam currentTeam = new CurrentTeam("black");

        this.currentTeamDAO.updateCurrentTeam(chessBoard, currentTeam);
    }

    @Disabled
    @DisplayName("현재 팀 삭제")
    @Test
    void deleteCurrentTeamTest() throws Exception {
        ChessBoard chessBoard = new ChessBoard(2);

        this.currentTeamDAO.deleteCurrentTeam(chessBoard);
    }

    @Disabled
    @DisplayName("현재 팀 검색")
    @Test
    void findCurrentTeamTest() throws Exception {
        ChessBoard chessBoard = new ChessBoard(3);
        CurrentTeam currentTeam = currentTeamDAO.findCurrentTeam(chessBoard);

        Assertions.assertThat(currentTeam).isNotNull();
        Assertions.assertThat(currentTeam.getCurrentTeam()).isEqualTo("white");
    }
}
