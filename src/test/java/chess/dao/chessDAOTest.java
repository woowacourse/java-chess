package chess.dao;

import chess.domain.Board;
import chess.domain.Turn;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class chessDAOTest {
    private static ChessDAO chessDAO = ChessDAO.getInstance();

    @Test
    @DisplayName("데이터베이스에 연결하는 것을 테스트")
    public void connection() {
        Connection con = chessDAO.getConnection();
        assertNotNull(con);
    }

    @Test
    @DisplayName("말을 저장하고 불러오는 것을 테스트")
    void saveAndLoadPiecesTest() throws SQLException {
        List<Piece> pieces = new Board().getPieces().getAlivePieces();
        chessDAO.savePieces(pieces);
        assertThat(chessDAO.loadPieces().getAlivePieces().size()).isEqualTo(32);
    }

    @Test
    @DisplayName("턴 상태를 저장하고 불러오는 것을 테스트")
    void saveAndLoadTurnTest() throws SQLException {
        Turn turn = new Turn(Team.BLACK);
        chessDAO.saveTurn(turn);
        assertThat(chessDAO.loadTurn().getTeam()).isEqualTo(Team.BLACK);
    }
}