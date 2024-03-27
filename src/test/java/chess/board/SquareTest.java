package chess.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.piece.Color;
import chess.piece.InitPawn;
import chess.piece.King;
import chess.piece.MovedPawn;
import chess.piece.Rook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SquareTest {

    @Test
    @DisplayName("출발 칸에 기물이 없다면 예외를 발생한다.")
    void emptySourcePositionTest() {
        // given
        Square source = Square.empty();
        Square destination = new Square(new Rook(Color.WHITE));
        // when, then
        assertThatThrownBy(() -> source.movePieceTo(destination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("출발 칸에 기물이 없습니다.");
    }

    @Test
    @DisplayName("도착 칸에 자신의 기물이 있다면 예외를 발생한다.")
    void allyPieceOnDestinationTest() {
        // given
        Square source = new Square(new Rook(Color.WHITE));
        Square destination = new Square(new Rook(Color.WHITE));
        // when, then
        assertThatThrownBy(() -> source.movePieceTo(destination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("도착 칸에 자신의 기물이 있습니다.");
    }

    @Test
    @DisplayName("InitPawn이 이동하면 MovedPawn으로 교체한다.")
    void replaceInitPawnTest() {
        // given
        Square source = new Square(new InitPawn(Color.WHITE));
        Square destination = Square.empty();
        // when
        source.movePieceTo(destination);
        // then
        assertThat(destination.getPiece()).isInstanceOf(MovedPawn.class);
    }

    @Test
    @DisplayName("기물을 이동한다.")
    void movePieceTest() {
        // given
        Square source = new Square(new Rook(Color.WHITE));
        Square destination = Square.empty();
        // when
        source.movePieceTo(destination);
        // then
        assertAll(
                () -> assertThat(source.getPiece()).isNull(),
                () -> assertThat(destination.getPiece()).isInstanceOf(Rook.class)
        );
    }

    @Test
    @DisplayName("적 기물이 있는 칸으로 이동한다.")
    void attackTest() {
        // given
        Square source = new Square(new Rook(Color.WHITE));
        Square destination = new Square(new Rook(Color.BLACK));
        // when
        source.movePieceTo(destination);
        // then
        assertAll(
                () -> assertThat(source.getPiece()).isNull(),
                () -> assertThat(destination.getPiece()).isInstanceOf(Rook.class)
        );
    }

    @Test
    @DisplayName("기물의 존재 여부를 판단한다.")
    void hasPieceTest() {
        // given
        Square square = new Square(new InitPawn(Color.WHITE));
        // when
        boolean hasPiece = square.hasPiece();
        boolean hasNoPiece = square.hasNoPiece();
        // then
        assertAll(
                () -> assertThat(hasPiece).isTrue(),
                () -> assertThat(hasNoPiece).isFalse()
        );
    }

    @Test
    @DisplayName("킹 존재 여부를 판단한다.")
    void hasKingTest() {
        // given
        Square square = new Square(new King(Color.WHITE));
        Square empty = Square.empty();
        // when
        boolean hasKing = square.hasKing();
        boolean hasEmptyKing = empty.hasKing();
        // then
        assertAll(
                () -> assertThat(hasKing).isTrue(),
                () -> assertThat(hasEmptyKing).isFalse()
        );
    }

    @Test
    @DisplayName("폰 존재 여부를 판단한다.")
    void hasPawnTest() {
        // given
        Square square = new Square(new InitPawn(Color.WHITE));
        Square empty = Square.empty();
        // when
        boolean hasPawn = square.hasPawn();
        boolean hasNoPawn = square.hasNoPawn();
        boolean hasEmptyNoPawn = empty.hasNoPawn();
        // then
        assertAll(
                () -> assertThat(hasPawn).isTrue(),
                () -> assertThat(hasNoPawn).isFalse(),
                () -> assertThat(hasEmptyNoPawn).isTrue()
        );
    }

    @Test
    @DisplayName("두 칸이 서로 같은 색을 가지고 있는지 확인한다.")
    void squareColorTest() {
        // given
        Square whiteSquare = new Square(new InitPawn(Color.WHITE));
        Square empty = Square.empty();
        // when
        boolean actual = whiteSquare.hasPieceColored(Color.WHITE);
        boolean emptyActual = empty.hasPieceColored(Color.WHITE);
        // then
        assertAll(
                () -> assertThat(actual).isTrue(),
                () -> assertThat(emptyActual).isFalse()
        );
    }
}
