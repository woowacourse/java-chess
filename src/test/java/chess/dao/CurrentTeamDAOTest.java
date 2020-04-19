package chess.dao;

import chess.domain.piece.Team;
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
    void addCurrentTeamTest() {
        ChessBoard chessBoard = new ChessBoard(10);
        CurrentTeam currentTeam = new CurrentTeam(Team.valueOf("BLACK"));

        this.currentTeamDAO.addCurrentTeam(chessBoard, currentTeam);
    }

    @Disabled
    @DisplayName("현재 팀 정보 업데이트")
    @Test
    void updateCurrentTeamTest() {
        ChessBoard chessBoard = new ChessBoard(10);
        CurrentTeam currentTeam = new CurrentTeam(Team.valueOf("WHITE"));

        this.currentTeamDAO.updateCurrentTeam(chessBoard, currentTeam);
    }

    @Disabled
    @DisplayName("현재 팀 삭제")
    @Test
    void deleteCurrentTeamTest() {
        ChessBoard chessBoard = new ChessBoard(10);

        this.currentTeamDAO.deleteCurrentTeam(chessBoard);
    }

    @Disabled
    @DisplayName("현재 팀 검색")
    @Test
    void findCurrentTeamTest() {
        ChessBoard chessBoard = new ChessBoard(10);
        CurrentTeam currentTeam = currentTeamDAO.findCurrentTeam(chessBoard);

        Assertions.assertThat(currentTeam).isNotNull();
        Assertions.assertThat(currentTeam.getCurrentTeam()).isEqualTo("WHITE");
    }
}
