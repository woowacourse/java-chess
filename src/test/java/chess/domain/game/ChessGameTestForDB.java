package chess.domain.game;

import static chess.domain.player.type.TeamColor.BLACK;
import static chess.domain.player.type.TeamColor.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.dao.ChessGameDAO;
import chess.dao.PlayerDAO;
import chess.dao.PlayerPiecePositionDAO;
import chess.dao.entity.PiecePositionFromDB;
import chess.domain.board.setting.BoardDefaultSetting;
import chess.utils.DBCleaner;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChessGameTestForDB {
    public static final int NUMBER_OF_PIECES_OF_ONE_PLAYER = 16;
    private final ChessGameForDB chessGameForDB = new ChessGameForDB();
    private final ChessGameDAO chessGameDAO = new ChessGameDAO();
    private final PlayerDAO playerDAO = new PlayerDAO();
    private final PlayerPiecePositionDAO piecePositionDAO = new PlayerPiecePositionDAO();

    @AfterEach
    void tearDown() throws SQLException {
        DBCleaner.removeAll();
    }

    @DisplayName("종료를 요청하면, 현재 진행중인 ChessGame에 관련된 모든 정보 삭제")
    @Test
    void endGame() throws SQLException {
        chessGameForDB.createNew(new BoardDefaultSetting(), "game1");
        chessGameForDB.createNew(new BoardDefaultSetting(), "game2");

        List<ChessGameEntity> allChessGames = chessGameDAO.findAll();
        assertThat(allChessGames).hasSize(2);

        for (ChessGameEntity chessGame : allChessGames) {
            Long whitePlayerId = playerDAO
                .findIdByGameIdAndTeamColor(chessGame.getId(), WHITE);
            Long blackPlayerId = playerDAO
                .findIdByGameIdAndTeamColor(chessGame.getId(), BLACK);

            assertThat(whitePlayerId).isNotNull();
            assertThat(blackPlayerId).isNotNull();

            assertPiecesSizeOfPlayers(whitePlayerId);
            assertPiecesSizeOfPlayers(blackPlayerId);
        }

        chessGameForDB.remove(allChessGames.get(0).getId());

        assertThat(chessGameDAO.findAll()).hasSize(1);
    }

    private void assertPiecesSizeOfPlayers(Long playerId) throws SQLException {
        List<PiecePositionFromDB> piecesPositions = piecePositionDAO.findAllByPlayerId(playerId);
        assertThat(piecesPositions).hasSize(NUMBER_OF_PIECES_OF_ONE_PLAYER);
    }
}
