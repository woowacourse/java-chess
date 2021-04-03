package chess.dao;

import static chess.domain.player.type.TeamColor.BLACK;
import static chess.domain.player.type.TeamColor.WHITE;
import static chess.utils.TestFixture.TEST_TITLE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.dao.entity.ChessGameEntity;
import chess.dao.game.ChessGameDAO;
import chess.dao.game.ChessGameRepository;
import chess.dao.player.PlayerDAO;
import chess.dao.player.PlayerRepository;
import chess.domain.player.type.TeamColor;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerDAOTest {
    private final PlayerRepository playerRepository = new PlayerDAO();
    private final ChessGameRepository chessGameRepository = new ChessGameDAO();

    @AfterEach
    void tearDown() throws SQLException {
        playerRepository.removeAll();
        chessGameRepository.removeAll();
    }

    @DisplayName("저장 및 조회")
    @Test
    void saveAndFindById() throws SQLException {
        ChessGameEntity chessGame = chessGameRepository.save(new ChessGameEntity(TEST_TITLE));
        Long gameId = chessGame.getId();

        playerRepository.save(TeamColor.values(), gameId);

        Long whitePlayerId = playerRepository.findIdByGameIdAndTeamColor(gameId, WHITE);

        assertThat(whitePlayerId).isNotNull();
    }

    @DisplayName("특정 체스 게임의 모든 플레이어들 삭제")
    @Test
    void removeAllByChessGame() throws SQLException {
        ChessGameEntity chessGame1 = chessGameRepository.save(new ChessGameEntity(TEST_TITLE));
        ChessGameEntity chessGame2 = chessGameRepository.save(new ChessGameEntity(TEST_TITLE + "2"));
        Long game1Id = chessGame1.getId();
        Long game2Id = chessGame2.getId();

        playerRepository.save(TeamColor.values(), game1Id);
        playerRepository.save(TeamColor.values(), game2Id);

        playerRepository.removeAllByChessGame(game1Id);

        Long game1WhitePlayer = playerRepository.findIdByGameIdAndTeamColor(game1Id, WHITE);
        Long game1BlackPlayer = playerRepository.findIdByGameIdAndTeamColor(game1Id, BLACK);
        Long game2WhitePlayer = playerRepository.findIdByGameIdAndTeamColor(game2Id, WHITE);
        Long game2BlackPlayer = playerRepository.findIdByGameIdAndTeamColor(game2Id, BLACK);

        assertThat(game1WhitePlayer).isNull();
        assertThat(game1BlackPlayer).isNull();
        assertThat(game2WhitePlayer).isNotNull();
        assertThat(game2BlackPlayer).isNotNull();
    }

    @DisplayName("모든 플레이어들 삭제")
    @Test
    void removeAll() throws SQLException {
        ChessGameEntity chessGame1 = chessGameRepository.save(new ChessGameEntity(TEST_TITLE));
        ChessGameEntity chessGame2 = chessGameRepository.save(new ChessGameEntity(TEST_TITLE + "2"));
        Long game1Id = chessGame1.getId();
        Long game2Id = chessGame2.getId();

        playerRepository.save(TeamColor.values(), game1Id);
        playerRepository.save(TeamColor.values(), game2Id);

        playerRepository.removeAll();

        Long game1WhitePlayer = playerRepository.findIdByGameIdAndTeamColor(game1Id, WHITE);
        Long game1BlackPlayer = playerRepository.findIdByGameIdAndTeamColor(game1Id, BLACK);
        Long game2WhitePlayer = playerRepository.findIdByGameIdAndTeamColor(game2Id, WHITE);
        Long game2BlackPlayer = playerRepository.findIdByGameIdAndTeamColor(game2Id, BLACK);

        assertThat(game1WhitePlayer).isNull();
        assertThat(game1BlackPlayer).isNull();
        assertThat(game2WhitePlayer).isNull();
        assertThat(game2BlackPlayer).isNull();
    }
}