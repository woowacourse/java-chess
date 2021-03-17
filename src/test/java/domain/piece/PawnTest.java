package domain.piece;

import domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PawnTest {
    @Test
    @DisplayName("폰이 잘 생성되는지 확인")
    void createPawn() {
        Piece pawn = new Pawn(Color.BLACK);
        assertThat(pawn).isInstanceOf(Pawn.class);
    }

    @Test
    @DisplayName("하얀 말일 때 기본 이동")
    void whiteMove() {
        Piece pawn = new Pawn(Color.WHITE);
        boolean canMove = pawn.canMove(Position.from("a3"), Position.from("a4"));
        assertTrue(canMove);
    }

    @Test
    @DisplayName("검정 말일 때 기본 이동")
    void blackMove() {
        Piece pawn = new Pawn(Color.BLACK);
        boolean canMove = pawn.canMove(Position.from("a3"), Position.from("a2"));
        assertTrue(canMove);
    }

    @Test
    @DisplayName("하얀 말 처음 위치에서 이동")
    void whiteInitialMove() {
        Piece pawn = new Pawn(Color.WHITE);
        boolean canMove = pawn.canMove(Position.from("a2"), Position.from("a4"));
        assertTrue(canMove);
    }

    @Test
    @DisplayName("검정 말 처음 위치에서 이동")
    void blackInitialMove() {
        Piece pawn = new Pawn(Color.BLACK);
        boolean canMove = pawn.canMove(Position.from("a7"), Position.from("a5"));
        assertTrue(canMove);
    }
}
