package chess.dao;

import static chess.domain.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.pawn.Pawn;
import chess.testutil.H2Connection;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessBoardDaoImplTest {

    @BeforeEach
    void setUp() {
        H2Connection.setUpTable();
    }

    @Test
    @DisplayName("piece를 저장하고 저장 확인")
    void test() {
        // given
        ChessBoardDao chessBoardDao = new ChessBoardDaoImpl(H2Connection.getConnection());
        Position position = Position.of('a', '1');
        ChessBoard chessBoard = new ChessBoard(Map.of(position, new Piece(WHITE, new Pawn(WHITE))));

        // when
        chessBoardDao.savePieces(chessBoard.getPieces());
        Piece result = chessBoardDao.findAllPieces().get(position);

        // then
        assertAll(
                () -> assertThat(result.color()).isEqualTo(WHITE),
                () -> assertThat(result.name()).isEqualTo("pawn")
        );
    }
}
