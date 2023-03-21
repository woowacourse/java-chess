package chess;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static chess.fixture.PositionFixture.A1;
import static chess.fixture.PositionFixture.A2;
import static chess.fixture.PositionFixture.A4;
import static chess.fixture.PositionFixture.A7;
import static chess.fixture.PositionFixture.A8;
import static chess.fixture.PositionFixture.B6;
import static chess.fixture.PositionFixture.B8;
import static chess.fixture.PositionFixture.C7;
import static chess.fixture.PositionFixture.D4;
import static chess.fixture.PositionFixture.D7;
import static chess.fixture.PositionFixture.E1;
import static chess.fixture.PositionFixture.E6;
import static chess.fixture.PositionFixture.F1;
import static chess.fixture.PositionFixture.F2;
import static chess.fixture.PositionFixture.F3;
import static chess.fixture.PositionFixture.F4;
import static chess.fixture.PositionFixture.G2;
import static chess.fixture.PositionFixture.G4;
import static chess.fixture.PositionFixture.H3;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BoardTest {

    @DisplayName("체스판은 말을 성공적으로 이동시킬 수 있다.")
    @Nested
    class moveTest {
        @DisplayName("빈 출발점을 선택하는 경우 예외 처리한다.")
        @Test
        void test_moveEmptyFrom() {
            Board board = new BoardFactory().createInitialBoard();

            assertThatThrownBy(() -> board.move(D4, A1))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("출발점에 말이 없습니다.");
        }

        @DisplayName("체스판은 말을 이동할 수 있다.")
        @Test
        void test_movePawn() {
            Board board = new BoardFactory().createInitialBoard();

            Position from = A2;
            Position to = A4;

            assertTrue(board.board().containsKey(from));
            assertFalse(board.board().containsKey(to));

            board.move(from, to);

            assertFalse(board.board().containsKey(from));
            assertTrue(board.board().containsKey(to));
        }
    }

    @Test
    void test_calculateScore() {
        Map<Position, Piece> boardData = new HashMap<>() {
            {
                put(A7, Pawn.from(BLACK));
                put(A8, King.from(BLACK));
                put(B6, Pawn.from(BLACK));
                put(B8, Rook.from(BLACK));
                put(C7, Pawn.from(BLACK));
                put(D7, Bishop.from(BLACK));
                put(E1, Rook.from(WHITE));
                put(E6, Queen.from(BLACK));
                put(F1, King.from(WHITE));
                put(F2, Pawn.from(WHITE));
                put(F3, Pawn.from(WHITE));
                put(F4, Knight.from(WHITE));
                put(G2, Pawn.from(WHITE));
                put(G4, Queen.from(WHITE));
                put(H3, Pawn.from(WHITE));
            }
        };

        Board board = new Board(boardData);

        assertThat(board.calculateScore().get(BLACK)).isEqualTo(20);
        assertThat(board.calculateScore().get(WHITE)).isEqualTo(19.5);
    }
}
