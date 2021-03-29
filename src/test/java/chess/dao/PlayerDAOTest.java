package chess.dao;

import static chess.domain.player.type.TeamColor.BLACK;
import static chess.domain.player.type.TeamColor.WHITE;
import static chess.utils.TestFixture.TEST_TITLE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.game.ChessGameEntity;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerDAOTest {
    private final PlayerDAO playerDAO = new PlayerDAO();
    private final ChessGameDAO chessGameDAO = new ChessGameDAO();

    @AfterEach
    void tearDown() throws SQLException {
        playerDAO.removeAll();
        chessGameDAO.removeAll();
    }

    @DisplayName("저장 및 조회")
    @Test
    void saveAndFindById() throws SQLException {
        ChessGameEntity chessGame = chessGameDAO.save(new ChessGameEntity(TEST_TITLE));
        Long gameId = chessGame.getId();

        playerDAO.save(WHITE, gameId);

        Long whitePlayerId = playerDAO.findIdByGameIdAndTeamColor(gameId, WHITE);

        assertThat(whitePlayerId).isNotNull();
    }

    @DisplayName("점수 업데이트 및 조회")
    @Test
    void updateScoreAndFindByPlayerId() throws SQLException {
        ChessGameEntity chessGame = chessGameDAO.save(new ChessGameEntity(TEST_TITLE));
        Long gameId = chessGame.getId();

        playerDAO.save(WHITE, gameId);

        Long whitePlayerIdBeforeUpdateScore = playerDAO.findIdByGameIdAndTeamColor(gameId, WHITE);
        double scoreBeforeUpdate = playerDAO.findScoreByPlayerId(whitePlayerIdBeforeUpdateScore);

        assertThat(scoreBeforeUpdate).isEqualTo(38.0);

        double newScore = 2.54;
        playerDAO.updateScore(whitePlayerIdBeforeUpdateScore, newScore);

        double scoreAfterUpdate = playerDAO.findScoreByPlayerId(whitePlayerIdBeforeUpdateScore);

        assertThat(scoreAfterUpdate).isEqualTo(newScore);
    }

    @DisplayName("특정 체스 게임의 모든 플레이어들 삭제")
    @Test
    void removeAllByChessGame() throws SQLException {
        ChessGameEntity chessGame1 = chessGameDAO.save(new ChessGameEntity(TEST_TITLE));
        ChessGameEntity chessGame2 = chessGameDAO.save(new ChessGameEntity(TEST_TITLE + "2"));
        Long game1Id = chessGame1.getId();
        Long game2Id = chessGame2.getId();

        playerDAO.save(WHITE, game1Id);
        playerDAO.save(BLACK, game1Id);
        playerDAO.save(WHITE, game2Id);
        playerDAO.save(BLACK, game2Id);

        playerDAO.removeAllByChessGame(game1Id);

        Long game1WhitePlayer = playerDAO.findIdByGameIdAndTeamColor(game1Id, WHITE);
        Long game1BlackPlayer = playerDAO.findIdByGameIdAndTeamColor(game1Id, BLACK);
        Long game2WhitePlayer = playerDAO.findIdByGameIdAndTeamColor(game2Id, WHITE);
        Long game2BlackPlayer = playerDAO.findIdByGameIdAndTeamColor(game2Id, BLACK);

        assertThat(game1WhitePlayer).isNull();
        assertThat(game1BlackPlayer).isNull();
        assertThat(game2WhitePlayer).isNotNull();
        assertThat(game2BlackPlayer).isNotNull();
    }

    @DisplayName("모든 플레이어들 삭제")
    @Test
    void removeAll() throws SQLException {
        ChessGameEntity chessGame1 = chessGameDAO.save(new ChessGameEntity(TEST_TITLE));
        ChessGameEntity chessGame2 = chessGameDAO.save(new ChessGameEntity(TEST_TITLE + "2"));
        Long game1Id = chessGame1.getId();
        Long game2Id = chessGame2.getId();

        playerDAO.save(WHITE, game1Id);
        playerDAO.save(BLACK, game1Id);
        playerDAO.save(WHITE, game2Id);
        playerDAO.save(BLACK, game2Id);

        playerDAO.removeAll();

        Long game1WhitePlayer = playerDAO.findIdByGameIdAndTeamColor(game1Id, WHITE);
        Long game1BlackPlayer = playerDAO.findIdByGameIdAndTeamColor(game1Id, BLACK);
        Long game2WhitePlayer = playerDAO.findIdByGameIdAndTeamColor(game2Id, WHITE);
        Long game2BlackPlayer = playerDAO.findIdByGameIdAndTeamColor(game2Id, BLACK);

        assertThat(game1WhitePlayer).isNull();
        assertThat(game1BlackPlayer).isNull();
        assertThat(game2WhitePlayer).isNull();
        assertThat(game2BlackPlayer).isNull();
    }
}