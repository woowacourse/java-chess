package chess.domain.piece.type;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {

    @DisplayName("블랙 폰은 앞으로 한 칸 움직일 수 있다.")
    @Test
    void canBlackPawnMoveOneStep() {
        // given
        Piece pawn = new Pawn(PieceColor.BLACK);
        Position source = Position.of("a6");
        Position target = Position.of("a5");

        // when
        boolean result = pawn.isInMovableRange(source, target);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("화이트폰은 앞으로 한 칸 움직일 수 있다.")
    @Test
    void canWhitePawnMoveOneStep() {
        // given
        Piece pawn = new Pawn(PieceColor.WHITE);
        Position source = Position.of("a2");
        Position target = Position.of("a3");

        // when
        boolean result = pawn.isInMovableRange(source, target);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("블랙폰은 처음(Rank7) 움직일 때, 앞으로 한 칸  또는 두 칸 움직일 수 있다.")
    @Test
    void canBlackPawnMoveTwoStep() {
        // given
        Piece pawn = new Pawn(PieceColor.BLACK);
        Position source = Position.of("a7");
        Position target = Position.of("a5");

        // when
        boolean result = pawn.isInMovableRange(source, target);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("화이트폰은 처음(Rank2) 움직일 때, 앞으로 한 칸  또는 두 칸 움직일 수 있다.")
    @Test
    void canWhitePawnMoveTwoStep() {
        // given
        Piece pawn = new Pawn(PieceColor.WHITE);
        Position source = Position.of("a2");
        Position target = Position.of("a4");

        // when
        boolean result = pawn.isInMovableRange(source, target);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("블랙폰은 처음(Rank7) 움직이는 것이 아닐 때, 앞으로 두 칸 움직일 수 없다.")
    @Test
    void cannotBlackPawnMoveTwoStep() {
        // given
        Piece pawn = new Pawn(PieceColor.BLACK);
        Position source = Position.of("a6");
        Position target = Position.of("a4");

        // when
        boolean result = pawn.isInMovableRange(source, target);

        // then
        assertThat(result).isFalse();
    }

    @DisplayName("화이트폰은 처음(Rank2) 움직이는 것이 아닐 때, 앞으로 두 칸 움직일 수 없다.")
    @Test
    void cannotWhitePawnMoveTwoStep() {
        // given
        Piece pawn = new Pawn(PieceColor.WHITE);
        Position source = Position.of("a3");
        Position target = Position.of("a5");

        // when
        boolean result = pawn.isInMovableRange(source, target);

        // then
        assertThat(result).isFalse();
    }

    @DisplayName("화이트폰은 앞 측 대각으로 한 칸 움직일 수 있다.")
    @Test
    void canWhitePawnMoveDiagonalOneStep() {
        // given
        Piece pawn = new Pawn(PieceColor.WHITE);
        Position source = Position.of("a3");
        Position target = Position.of("b4");

        // when
        boolean result = pawn.isInMovableRange(source, target);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("블랙폰은 앞 측 대각으로 한 칸 움직일 수 있다.")
    @Test
    void canBlackPawnMoveDiagonalOneStep() {
        // given
        Piece pawn = new Pawn(PieceColor.BLACK);
        Position source = Position.of("b7");
        Position target = Position.of("a6");

        // when
        boolean result = pawn.isInMovableRange(source, target);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("화이트폰은 앞 측 대각으로는 두 칸 움직일 수 없다.")
    @Test
    void cannotWhitePawnMoveDiagonalTwoStep() {
        // given
        Piece pawn = new Pawn(PieceColor.WHITE);
        Position source = Position.of("a2");
        Position target = Position.of("c4");

        // when
        boolean result = pawn.isInMovableRange(source, target);

        // then
        assertThat(result).isFalse();
    }

    @DisplayName("블랙폰은 앞 측 대각으로는 두 칸 움직일 수 없다.")
    @Test
    void cannotBlackPawnMoveDiagonalTwoStep() {
        // given
        Piece pawn = new Pawn(PieceColor.BLACK);
        Position source = Position.of("a7");
        Position target = Position.of("c5");

        // when
        boolean result = pawn.isInMovableRange(source, target);

        // then
        assertThat(result).isFalse();
    }
}
