package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.BasicChessBoardGenerator;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackTurnTest {

    @DisplayName("말을 움직인다")
    @Test
    void test() {
        Board board = BasicChessBoardGenerator.generator();
        State blackTurn = new BlackTurn(board);
        Position source = Position.valueOf("a7");
        Position destination = Position.valueOf("a6");

        blackTurn.movePiece(source, destination);

        assertThat(board.findPieceBy(destination).get().isSameColor(Color.BLACK)).isTrue();
        assertThat(board.findPieceBy(destination).get().isSameType(Pawn.class)).isTrue();
    }

    @DisplayName("상대의 말을 움직이면 에러가 발생한다")
    @Test
    void testMoveError() {
        Board board = BasicChessBoardGenerator.generator();
        State blackTurn = new BlackTurn(board);

        Position source = Position.valueOf("a2");
        Position destination = Position.valueOf("a3");

        assertThatThrownBy(() -> blackTurn.movePiece(source, destination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("상대방");
    }

    @DisplayName("빈 곳을 움직이면 에러가 발생한다")
    @Test
    void testBlankMoveError() {
        Board board = BasicChessBoardGenerator.generator();
        State blackTurn = new BlackTurn(board);

        Position source = Position.valueOf("d5");
        Position destination = Position.valueOf("a6");

        assertThatThrownBy(() -> blackTurn.movePiece(source, destination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("기물이 존재하지 않습니다");
    }

    @DisplayName("화이트 왕이 죽으면 블랙이 이긴다")
    @Test
    void testBlackWin() {
        Map<Position, Piece> value = new HashMap<>();
        value.put(Position.valueOf("d5"), new King(Color.WHITE));
        value.put(Position.valueOf("d4"), new Queen(Color.BLACK));
        value.put(Position.valueOf("a4"), new King(Color.BLACK));
        Board board = new Board(value);

        State whiteTurn = Ready.start(board);
        State state = whiteTurn.movePiece(Position.valueOf("d5"), Position.valueOf("d6"));
        state = state.movePiece(Position.valueOf("d4"), Position.valueOf("d6"));

        assertThat(state).isInstanceOf(BlackWin.class);
    }
}
