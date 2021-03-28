package chess.db.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.beforedb.domain.board.setting.BoardDefaultSetting;
import chess.db.dao.ChessGameDAO;
import chess.db.dao.PlayerDAO;
import chess.db.dao.PlayerPiecePositionDAO;
import chess.db.domain.game.ChessGameForDB;
import chess.db.entity.ChessGameEntity;
import chess.db.entity.PlayerEntity;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChessGameTest {
    private final ChessGameForDB chessGameForDB = new ChessGameForDB();
    private final ChessGameDAO chessGameDAO = new ChessGameDAO();
    private final PlayerDAO playerDAO = new PlayerDAO();
    private final PlayerPiecePositionDAO piecePositionDAO = new PlayerPiecePositionDAO();

    @AfterEach
    void tearDown() throws SQLException {
        piecePositionDAO.removeAll();
        playerDAO.removeAll();
        chessGameDAO.removeAll();
    }

    @DisplayName("종료를 요청하면, 현재 진행중인 ChessGame에 관련된 모든 정보 삭제")
    @Test
    void endGame() throws SQLException {
        chessGameForDB.createNew(new BoardDefaultSetting(), "game1");
        chessGameForDB.createNew(new BoardDefaultSetting(), "game2");

        List<ChessGameEntity> allChessGames = chessGameDAO.findAll();
        assertThat(allChessGames).hasSize(2);

        for (ChessGameEntity chessGame : allChessGames) {
            List<PlayerEntity> playersOfOneChessGame = playerDAO.findAllByChessGame(chessGame);
            assertThat(playersOfOneChessGame).hasSize(2);
            assertPiecesSizeOfPlayers(playersOfOneChessGame);
        }

        chessGameForDB.end();

        assertThat(chessGameDAO.findAll()).hasSize(1);
    }

    private void assertPiecesSizeOfPlayers(List<PlayerEntity> playersOfOneChessGame)
        throws SQLException {
        for (PlayerEntity playerEntity : playersOfOneChessGame) {
            assertThat(piecePositionDAO.findAllByPlayer(playerEntity)).hasSize(16);
        }
    }
}
