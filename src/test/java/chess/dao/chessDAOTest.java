package chess.dao;

import chess.domain.Turn;
import chess.domain.piece.Team;
import chess.domain.service.BoardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class chessDAOTest {
    private static ChessDAO chessDAO = ChessDAO.getInstance();

    @Test
    @DisplayName("말을 저장하고 불러오는 것을 테스트")
    void saveAndLoadPiecesTest() throws SQLException {
        BoardService boardService = new BoardService();
        chessDAO.saveGame(boardService.getStatus());
        assertThat(chessDAO.loadGame().getAlivePieces().size()).isEqualTo(32);
        assertThat(chessDAO.loadGame().getTurn()).isEqualTo(new Turn(Team.BLACK));
    }
}