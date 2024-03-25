package chess.domain.board;

import chess.domain.piece.ColorType;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.File;
import chess.domain.position.Rank;
import chess.domain.position.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("체스판")
public class BoardTest {

    @DisplayName("체스판을 생성한다.")
    @Test
    void createBoard() {
        // when & then
        assertThatCode(Board::new).doesNotThrowAnyException();
    }

    @DisplayName("체스판은 목적지에 같은 색의 말이 있으면 예외를 반환한다.")
    @Test
    void checkPieceSameColor() {
        // given
        Board board = new Board();
        Square source = Square.of(File.A, Rank.EIGHT);
        Square destination = Square.of(File.A, Rank.SEVEN);

        // when & then
        assertThatThrownBy(() -> board.move(source, destination)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("체스판은 출발지에서 목적지로 말을 옮긴다.")
    @Test
    void move() {
        // given
        Board board = new Board();
        Square source = Square.of(File.A, Rank.TWO);
        Square destination = Square.of(File.A, Rank.THREE);

        // when & then
        assertThatCode(() -> board.move(source, destination)).doesNotThrowAnyException();
    }

    @DisplayName("체스판은 목적지로 가는 경로에 다른 말이 있으면 예외가 발생한다.")
    @Test
    void checkPathBlocked() {
        // given
        Board board = new Board();
        Square source = Square.of(File.A, Rank.EIGHT);
        Square destination = Square.of(File.A, Rank.FIVE);

        // when & then
        assertThatThrownBy(() -> board.move(source, destination)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("체스판은 목적지로 가는 경로에 다른 말이 없으면 예외가 발생하지 않는다.")
    @Test
    void checkPathNotBlocked() {
        // given
        Board board = new Board();
        Square source = Square.of(File.A, Rank.TWO);
        Square destination = Square.of(File.A, Rank.FOUR);

        // when & then
        assertThatCode(() -> board.move(source, destination)).doesNotThrowAnyException();
    }

    @DisplayName("체스판은 말의 규칙에 따라 갈 수 없는 도착지가 들어오면 예외가 발생한다.")
    @Test
    void checkCannotMove() {
        // given
        Board board = new Board();
        Square source = Square.of(File.B, Rank.EIGHT);
        Square destination = Square.of(File.C, Rank.THREE);

        // when & then
        assertThatThrownBy(() -> board.move(source, destination)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("체스판은 말의 규칙에 따라 갈 수 있는 도착지가 들어오면 예외가 발생하지 않는다.")
    @Test
    void checkCanMove() {
        // given
        Board board = new Board();
        Square source = Square.of(File.B, Rank.ONE);
        Square destination = Square.of(File.C, Rank.THREE);

        // when & then
        assertThatCode(() -> board.move(source, destination)).doesNotThrowAnyException();
    }

    @DisplayName("체스판은 턴에 해당하지 않는 말을 옮기려고 할 때 예외가 발생한다.")
    @Test
    void checkTurn() {
        // given
        Board board = new Board();
        Square source = Square.of(File.B, Rank.SEVEN);
        Square destination = Square.of(File.C, Rank.SIX);

        // when & then
        assertThatThrownBy(() -> board.move(source, destination)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("체스판은 말을 이동할 때 도착지에 다른 색의 말이 있으면 말을 잡는다.")
    @Test
    void catchOpponent() {
        // given
        Board board = new Board();
        board.move(Square.of(File.G, Rank.TWO), Square.of(File.G, Rank.FOUR));
        board.move(Square.of(File.H, Rank.SEVEN), Square.of(File.H, Rank.FIVE));

        // when
        board.move(Square.of(File.G, Rank.FOUR), Square.of(File.H, Rank.FIVE));

        Piece actual = board.findPieceBySquare(Square.of(File.H, Rank.FIVE));

        // then
        assertThat(actual.matches(PieceType.PAWN)
                && actual.isSameColor(new Piece(PieceType.PAWN, ColorType.WHITE)))
                .isTrue();
    }

    @DisplayName("폰이 직선이동으로 말을 잡으려고 하면 예외가 발생한다.")
    @Test
    void pawnStraightCatch() {
        // given
        Board board = new Board();
        board.move(Square.of(File.G, Rank.TWO), Square.of(File.G, Rank.FOUR));
        board.move(Square.of(File.G, Rank.SEVEN), Square.of(File.G, Rank.FIVE));

        // when & then
        assertThatThrownBy(() -> board.move(Square.of(File.G, Rank.FOUR), Square.of(File.G, Rank.FIVE)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
