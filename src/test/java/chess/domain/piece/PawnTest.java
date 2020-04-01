package chess.domain.piece;

import chess.domain.position.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PawnTest {
    @DisplayName("흰색 폰이 이동 가능한 곳인지 검사")
    @Test
    void whitePawnMovableTest() {
        Piece whitePawn = new Pawn(PieceType.PAWN, Team.WHITE);
        Position initialSource = Position.of("a2");
        Position directOne = Position.of("a3");
        Position directTwo = Position.of("a4");
        Position nonMovableInitial = Position.of("a5");

        Position nonInitialSource = Position.of("b3");
        Position diagonalTarget = Position.of("c4");
        Position directTarget = Position.of("b4");
        Position nonMovableTarget = Position.of("b5");

        Assertions.assertThat(whitePawn.movable(initialSource, directOne)).isTrue();
        Assertions.assertThat(whitePawn.movable(initialSource, directTwo)).isTrue();
        Assertions.assertThat(whitePawn.movable(initialSource, nonMovableInitial)).isFalse();

        Assertions.assertThat(whitePawn.movable(nonInitialSource, diagonalTarget)).isTrue();
        Assertions.assertThat(whitePawn.movable(nonInitialSource, directTarget)).isTrue();
        Assertions.assertThat(whitePawn.movable(nonInitialSource, nonMovableTarget)).isFalse();
    }

    @DisplayName("검은색 폰이 이동 가능한 곳인지 검사")
    @Test
    void blackPawnMovableTest() {
        Piece blackPawn = new Pawn(PieceType.PAWN, Team.BLACK);
        Position initialSource = Position.of("a7");
        Position directOne = Position.of("a6");
        Position directTwo = Position.of("a5");
        Position nonMovableInitial = Position.of("a4");

        Position nonInitialSource = Position.of("b6");
        Position diagonalTarget = Position.of("c5");
        Position directTarget = Position.of("b5");
        Position nonMovableTarget = Position.of("b4");

        Assertions.assertThat(blackPawn.movable(initialSource, directOne)).isTrue();
        Assertions.assertThat(blackPawn.movable(initialSource, directTwo)).isTrue();
        Assertions.assertThat(blackPawn.movable(initialSource, nonMovableInitial)).isFalse();

        Assertions.assertThat(blackPawn.movable(nonInitialSource, diagonalTarget)).isTrue();
        Assertions.assertThat(blackPawn.movable(nonInitialSource, directTarget)).isTrue();
        Assertions.assertThat(blackPawn.movable(nonInitialSource, nonMovableTarget)).isFalse();
    }
}
