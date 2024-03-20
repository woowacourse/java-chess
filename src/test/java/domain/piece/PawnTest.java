package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import domain.board.File;
import domain.board.Position;
import domain.board.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnTest {

    @Test
    @DisplayName("흰색 폰은 1칸 전진할 수 있다.")
    void canMove_WhitePawn_ForwardOne() {
        Pawn pawn = new Pawn(Color.WHITE);
        Position position1 = new Position(new File(1), new Rank(3));
        Position position2 = new Position(new File(1), new Rank(4));
        assertThat(pawn.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("검정색 폰은 1칸 전진할 수 있다.")
    void canMove_BlackPawn_ForwardOne() {
        Pawn pawn = new Pawn(Color.BLACK);
        Position position1 = new Position(new File(1), new Rank(4));
        Position position2 = new Position(new File(1), new Rank(3));
        assertThat(pawn.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("흰색 폰은 후진할 수 없다.")
    void cannotMove_WhitePawn_Backward() {
        Pawn pawn = new Pawn(Color.WHITE);
        Position position1 = new Position(new File(1), new Rank(4));
        Position position2 = new Position(new File(1), new Rank(3));
        assertThat(pawn.canMove(position1, position2)).isFalse();
    }

    @Test
    @DisplayName("검정색 폰은 후진할 수 없다.")
    void cannotMove_BlackPawn_Backward() {
        Pawn pawn = new Pawn(Color.BLACK);
        Position position1 = new Position(new File(1), new Rank(3));
        Position position2 = new Position(new File(1), new Rank(4));
        assertThat(pawn.canMove(position1, position2)).isFalse();
    }

    @Test
    @DisplayName("초기 위치에 있는 흰색 폰은 2칸 전진할 수 있다.")
    void canMove_WhitePawn_ForwardTwo_InitPosition() {
        Pawn pawn = new Pawn(Color.WHITE);
        Position position1 = new Position(new File(1), new Rank(2));
        Position position2 = new Position(new File(1), new Rank(4));
        assertThat(pawn.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("초기 위치에 있는 검정색 폰은 2칸 전진할 수 있다.")
    void canMove_BlackPawn_ForwardTwo_InitPosition() {
        Pawn pawn = new Pawn(Color.BLACK);
        Position position1 = new Position(new File(1), new Rank(7));
        Position position2 = new Position(new File(1), new Rank(5));
        assertThat(pawn.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("흰색 폰은 앞 대각선으로 갈 수 있다.")
    void canMove_WhitePawn_DiagonalMove() {
        Pawn pawn = new Pawn(Color.WHITE);
        Position position1 = new Position(new File(3), new Rank(3));
        Position position2 = new Position(new File(2), new Rank(4));
        assertThat(pawn.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("검정색 폰은 앞 대각선으로 갈 수 있다.")
    void canMove_BlackPawn_DiagonalMove() {
        Pawn pawn = new Pawn(Color.BLACK);
        Position position1 = new Position(new File(4), new Rank(4));
        Position position2 = new Position(new File(5), new Rank(3));
        assertThat(pawn.canMove(position1, position2)).isTrue();
    }
}
