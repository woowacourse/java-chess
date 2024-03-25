package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.chessboard.ChessboardFactory;
import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Position;

class PawnTest {

    @DisplayName("폰이 이동한다.")
    @Test
    void move() {
        Piece sut = new Pawn(Color.WHITE, Position.from("d3"));
        Position actual = sut.move(ChessboardFactory.empty(), Position.from("d4")).position();
        assertThat(actual).isEqualTo(Position.from("d4"));
    }

    @DisplayName("이동할 수 없는 위치를 입력받으면 예외를 발생한다.")
    @Test
    void moveException() {
        Piece sut = new Pawn(Color.WHITE, Position.from("d5"));
        assertThatCode(() -> sut.move(ChessboardFactory.empty(), Position.from("e3")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동할 수 없는 위치입니다: E3");
    }
}
