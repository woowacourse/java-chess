package chess.dao;

import chess.dto.ChessGameRoomInfoDTO;
import java.sql.SQLException;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameDAOTest {

    @Test
    @DisplayName("체스 게임방 생성")
    void makeChessGameRoom() throws SQLException {
        ChessGameDAO chessGameDAO = new ChessGameDAO();
        ChessGame chessGame = ChessGame.initChessGame();
        chessGame.setName("zero");
        String gameId = chessGameDAO.addGame(chessGame);

        Assertions.assertThat(gameId).isNotNull();
    }

    @Test
    @DisplayName("체스 게임방 가져오기")
    void findChessGameRoom() throws SQLException {
        ChessGameDAO chessGameDAO = new ChessGameDAO();
        List<ChessGameRoomInfoDTO> activeGames = chessGameDAO.findActiveGames();

        Assertions.assertThat(activeGames.size()).isNotEqualTo(0);
    }
}
