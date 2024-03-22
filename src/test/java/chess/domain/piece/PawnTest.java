package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnTest {

    @Test
    @DisplayName("흰색 폰은 1칸 전진할 수 있다.")
    void canMove_WhitePawn_ForwardOne() {
        Pawn pawn = new Pawn(Color.WHITE);
        Position position1 = Position.of(1, 3);
        Position position2 = Position.of(1, 4);
        assertThat(pawn.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("검정색 폰은 1칸 전진할 수 있다.")
    void canMove_BlackPawn_ForwardOne() {
        Pawn pawn = new Pawn(Color.BLACK);
        Position position1 = Position.of(1, 4);
        Position position2 = Position.of(1, 3);
        assertThat(pawn.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("흰색 폰은 후진할 수 없다.")
    void cannotMove_WhitePawn_Backward() {
        Pawn pawn = new Pawn(Color.WHITE);
        Position position1 = Position.of(1, 4);
        Position position2 = Position.of(1, 3);
        assertThat(pawn.canMove(position1, position2)).isFalse();
    }

    @Test
    @DisplayName("검정색 폰은 후진할 수 없다.")
    void cannotMove_BlackPawn_Backward() {
        Pawn pawn = new Pawn(Color.BLACK);
        Position position1 = Position.of(1, 3);
        Position position2 = Position.of(1, 4);
        assertThat(pawn.canMove(position1, position2)).isFalse();
    }

    @Test
    @DisplayName("초기 위치에 있는 흰색 폰은 2칸 전진할 수 있다.")
    void canMove_WhitePawn_ForwardTwo_InitPosition() {
        Pawn pawn = new Pawn(Color.WHITE);
        Position position1 = Position.of(1, 2);
        Position position2 = Position.of(1, 4);
        assertThat(pawn.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("초기 위치에 있는 검정색 폰은 2칸 전진할 수 있다.")
    void canMove_BlackPawn_ForwardTwo_InitPosition() {
        Pawn pawn = new Pawn(Color.BLACK);
        Position position1 = Position.of(1, 7);
        Position position2 = Position.of(1, 5);
        assertThat(pawn.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("흰색 폰은 앞 대각선으로 갈 수 있다.")
    void canMove_WhitePawn_DiagonalMove() {
        Pawn pawn = new Pawn(Color.WHITE);
        Position position1 = Position.of(3, 3);
        Position position2 = Position.of(2, 4);
        assertThat(pawn.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("검정색 폰은 앞 대각선으로 갈 수 있다.")
    void canMove_BlackPawn_DiagonalMove() {
        Pawn pawn = new Pawn(Color.BLACK);
        Position position1 = Position.of(4, 4);
        Position position2 = Position.of(5, 3);
        assertThat(pawn.canMove(position1, position2)).isTrue();
    }
}
