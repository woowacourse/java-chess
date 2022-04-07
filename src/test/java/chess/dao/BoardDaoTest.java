package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;

import chess.domain.board.BoardInitializer;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.dto.PieceDto;
import java.sql.SQLException;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardDaoTest {

    @DisplayName("DB에 보드를 저장한다.")
    @Test
    void saveTo() {
        BoardDao boardDao = new BoardDao();
        Map<Position, Piece> squares = BoardInitializer.get().getSquares();

        assertThatNoException().isThrownBy(() -> boardDao.saveTo("chess_test", squares));
    }

    @DisplayName("DB에 초기 보드를 저장한 후 load하면 a1 위치에 흰색 룩이 있다.")
    @Test
    void load_a1_white_rook() {
        BoardDao boardDao = new BoardDao();
        Map<Position, Piece> squares = BoardInitializer.get().getSquares();
        Map<String, PieceDto> board = null;

        try {
            boardDao.saveTo("chess_test", squares);
            board = boardDao.loadFrom("chess_test");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        PieceDto pieceAtA1 = board.get("a1");
        assertThat(pieceAtA1.getCamp() + pieceAtA1.getType()).isEqualTo("whiterook");
    }
}
