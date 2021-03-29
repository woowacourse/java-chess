package chess.db.dao;

import static chess.TestFixture.TEST_TITLE;
import static chess.beforedb.domain.player.type.TeamColor.BLACK;
import static chess.beforedb.domain.player.type.TeamColor.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.beforedb.domain.player.type.TeamColor;
import chess.db.domain.game.GameStatusEntity;
import chess.db.entity.ChessGameEntity;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameDAOTest {
    private final ChessGameDAO chessGameDAO = new ChessGameDAO();

    @AfterEach
    void tearDown() throws SQLException {
        chessGameDAO.removeAll();
    }

    @DisplayName("체스 게임 저장 및 조회")
    @Test
    void saveAndFind() throws SQLException {
        ChessGameEntity chessGame1 = new ChessGameEntity(TEST_TITLE);
        chessGameDAO.save(chessGame1);
        ChessGameEntity foundChessGame = chessGameDAO.findById(chessGame1.getId());

        assertThat(foundChessGame.getTitle()).endsWith(chessGame1.getTitle());

        String testTitle2 = TEST_TITLE + "2";

        ChessGameEntity chessGame2 = new ChessGameEntity(testTitle2);
        chessGameDAO.save(chessGame2);

        List<ChessGameEntity> foundAllChessGames = chessGameDAO.findAll();
        List<Long> foundAllChessGameIds = foundAllChessGames.stream()
            .map(ChessGameEntity::getId)
            .collect(Collectors.toList());

        assertThat(foundAllChessGameIds)
            .containsExactlyInAnyOrder(chessGame1.getId(), chessGame2.getId());
    }

    @DisplayName("체스 게임 이름, 현재 턴 팀 색깔 가져오기")
    @Test
    void findStatusByGameId() throws SQLException {
        ChessGameEntity chessGame = new ChessGameEntity(TEST_TITLE);
        ChessGameEntity savedChessGame = chessGameDAO.save(chessGame);

        GameStatusEntity gameStatus = chessGameDAO.findStatusByGameId(savedChessGame.getId());

        assertThat(gameStatus.getCurrentTurnTeamColor()).isSameAs(WHITE);
        assertThat(gameStatus.getTitle()).isEqualTo(TEST_TITLE);
    }

    @DisplayName("현재 턴 팀 색깔 업데이트")
    @Test
    void updateCurrentTurnTeamColor() throws SQLException {
        ChessGameEntity chessGame = new ChessGameEntity(TEST_TITLE);
        chessGameDAO.save(chessGame);
        ChessGameEntity foundChessGame = chessGameDAO.findById(chessGame.getId());

        assertThat(foundChessGame.getCurrentTurnTeamColor()).isSameAs(WHITE);

        chessGame.setCurrentTurnTeamColor(BLACK);
        chessGameDAO.updateCurrentTurnTeamColor(chessGame);

        ChessGameEntity foundChessGameAfterChange = chessGameDAO.findById(chessGame.getId());

        assertThat(foundChessGameAfterChange.getCurrentTurnTeamColor()).isEqualTo(BLACK);
    }

    @DisplayName("1개 삭제")
    @Test
    void remove() throws SQLException {
        ChessGameEntity chessGame1 = new ChessGameEntity(TEST_TITLE);
        chessGameDAO.save(chessGame1);

        String testTitle2 = TEST_TITLE + "2";
        ChessGameEntity chessGame2 = new ChessGameEntity(testTitle2);
        chessGameDAO.save(chessGame2);

        chessGameDAO.remove(chessGame1.getId());

        List<ChessGameEntity> foundAllChessGames = chessGameDAO.findAll();

        assertThat(foundAllChessGames).hasSize(1);
        assertThat(foundAllChessGames.get(0).getId()).isEqualTo(chessGame2.getId());
    }

    @DisplayName("전체 삭제")
    @Test
    void removeAll() throws SQLException {
        ChessGameEntity chessGame1 = new ChessGameEntity(TEST_TITLE);
        chessGameDAO.save(chessGame1);

        String testTitle2 = TEST_TITLE + "2";
        ChessGameEntity chessGame2 = new ChessGameEntity(testTitle2);
        chessGameDAO.save(chessGame2);

        chessGameDAO.removeAll();

        List<ChessGameEntity> foundAllChessGames = chessGameDAO.findAll();

        assertThat(foundAllChessGames).hasSize(0);
    }
}