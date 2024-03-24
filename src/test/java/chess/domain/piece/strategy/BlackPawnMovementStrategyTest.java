package chess.domain.piece.strategy;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BlackPawnMovementStrategyTest {

    @DisplayName("블랙 폰은 앞으로 한 칸 움직일 수 있다.")
    @Test
    void canBlackPawnMoveOneStep() {
        // given
        Piece pawn = new Piece(PieceType.BLACK_PAWN);
        Position source = Position.A6;
        Position target = Position.A5;

        // when
        boolean result = pawn.isInMovableRange(source, target);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("블랙폰은 처음(Rank7) 움직일 때, 앞으로 한 칸  또는 두 칸 움직일 수 있다.")
    @Test
    void canBlackPawnMoveTwoStep() {
        // given
        Piece pawn = new Piece(PieceType.BLACK_PAWN);
        Position source = Position.A7;
        Position target = Position.A5;

        // when
        boolean result = pawn.isInMovableRange(source, target);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("블랙폰은 처음(Rank7) 움직이는 것이 아닐 때, 앞으로 두 칸 움직일 수 없다.")
    @Test
    void cannotBlackPawnMoveTwoStep() {
        // given
        Piece pawn = new Piece(PieceType.BLACK_PAWN);
        Position source = Position.A6;
        Position target = Position.A4;

        // when
        boolean result = pawn.isInMovableRange(source, target);

        // then
        assertThat(result).isFalse();
    }

    @DisplayName("블랙폰은 앞 측 대각으로 한 칸 움직일 수 있다.")
    @Test
    void canBlackPawnMoveDiagonalOneStep() {
        // given
        Piece pawn = new Piece(PieceType.BLACK_PAWN);
        Position source = Position.B7;
        Position target = Position.A6;

        // when
        boolean result = pawn.isInMovableRange(source, target);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("블랙폰은 앞 측 대각으로는 두 칸 움직일 수 없다.")
    @Test
    void cannotBlackPawnMoveDiagonalTwoStep() {
        // given
        Piece pawn = new Piece(PieceType.BLACK_PAWN);
        Position source = Position.A7;
        Position target = Position.C5;

        // when
        boolean result = pawn.isInMovableRange(source, target);

        // then
        assertThat(result).isFalse();
    }
}
