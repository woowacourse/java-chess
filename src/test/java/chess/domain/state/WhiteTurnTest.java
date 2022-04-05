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

class WhiteTurnTest {

    @DisplayName("말을 움직인다")
    @Test
    void test() {
        Board board = BasicChessBoardGenerator.generator();
        State whiteTurn = Ready.start(board);

        Position source = Position.of("a2");
        Position destination = Position.of("a3");

        whiteTurn.movePiece(source, destination);

        assertThat(board.findPieceBy(destination).get().isSameColor(Color.WHITE)).isTrue();
        assertThat(board.findPieceBy(destination).get().isSameType(Pawn.class)).isTrue();
    }

    @DisplayName("상대의 말을 움직이면 에러가 발생한다")
    @Test
    void testMoveError() {
        Board board = BasicChessBoardGenerator.generator();
        State whiteTurn = Ready.start(board);

        Position source = Position.of("a7");
        Position destination = Position.of("a6");

        assertThatThrownBy(() -> whiteTurn.movePiece(source, destination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("상대방");
    }

    @DisplayName("빈 곳을 움직이면 에러가 발생한다")
    @Test
    void testBlankMoveError() {
        Board board = BasicChessBoardGenerator.generator();
        State whiteTurn = Ready.start(board);

        Position source = Position.of("d5");
        Position destination = Position.of("a6");

        assertThatThrownBy(() -> whiteTurn.movePiece(source, destination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("기물이 존재하지 않습니다");
    }

    @DisplayName("블랙 왕이 죽으면 화이트가 이긴다")
    @Test
    void testWhiteWin() {
        Map<Position, Piece> value = new HashMap<>();
        value.put(Position.of("d5"), new King(Color.BLACK));
        value.put(Position.of("d4"), new Queen(Color.WHITE));
        value.put(Position.of("a4"), new King(Color.WHITE));
        Board board = new Board(value);

        State whiteTurn = Ready.start(board);
        State state = whiteTurn.movePiece(Position.of("d4"), Position.of("d5"));

        assertThat(state).isInstanceOf(WhiteWin.class);
    }
}
