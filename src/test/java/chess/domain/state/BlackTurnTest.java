package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.domain.board.Color;
import chess.domain.board.King;
import chess.domain.board.Pawn;
import chess.domain.board.Piece;
import chess.domain.board.Queen;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackTurnTest {

    @DisplayName("말을 움직인다")
    @Test
    void test() {
        Board board = Board.getInitializedInstance();
        State blackTurn = new BlackTurn(board);
        Position src = Position.of("a7");
        Position dest = Position.of("a6");

        blackTurn.movePiece(src, dest);

        assertThat(board.exist(dest, Pawn.class, Color.BLACK)).isTrue();
    }

    @DisplayName("상대의 말을 움직이면 에러가 발생한다")
    @Test
    void testMoveError() {
        Board board = Board.getInitializedInstance();
        State blackTurn = new BlackTurn(board);

        Position src = Position.of("a2");
        Position dest = Position.of("a3");

        assertThatThrownBy(() -> blackTurn.movePiece(src, dest))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("상대방");
    }

    @DisplayName("빈 곳을 움직이면 에러가 발생한다")
    @Test
    void testBlankMoveError() {
        Board board = Board.getInitializedInstance();
        State blackTurn = new BlackTurn(board);

        Position src = Position.of("d5");
        Position dest = Position.of("a6");

        assertThatThrownBy(() -> blackTurn.movePiece(src, dest))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("기물이 존재하지 않습니다");
    }

    @DisplayName("화이트 왕이 죽으면 블랙이 이긴다")
    @Test
    void testBlackWin() {
        Map<Position, Piece> value = new HashMap<>();
        value.put(Position.of("d5"), new King(Color.WHITE));
        value.put(Position.of("d4"), new Queen(Color.BLACK));
        value.put(Position.of("a4"), new King(Color.BLACK));
        Board board = new Board(value);

        State whiteTurn = State.start(board);
        State state = whiteTurn.movePiece(Position.of("d5"), Position.of("d6"));
        state = state.movePiece(Position.of("d4"), Position.of("d6"));

        assertThat(state).isInstanceOf(BlackWin.class);
    }
}
