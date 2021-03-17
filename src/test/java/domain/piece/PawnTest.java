package domain.piece;

import domain.position.Position;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PawnTest {
    @Test
    @DisplayName("폰이 잘 생성되는지 확인")
    void createPawn() {
        Piece pawn = new Pawn(Color.BLACK, Position.from("a3"));
        assertThat(pawn).isInstanceOf(Pawn.class);
    }

    @Test
    @DisplayName("하얀 말일 때 기본 이동")
    void whiteMove() {
        Position from = Position.from("a3");
        Position to = Position.from("a4");
        Piece pawn = new Pawn(Color.WHITE, from);
        pawn.move(to, Lists.emptyList());
        assertTrue(pawn.hasPosition(to));
    }

    @Test
    @DisplayName("검정 말일 때 기본 이동")
    void blackMove() {
        Position from = Position.from("a3");
        Position to = Position.from("a2");
        Piece pawn = new Pawn(Color.BLACK, from);
        pawn.move(to, Lists.emptyList());
        assertTrue(pawn.hasPosition(to));
    }

    @Test
    @DisplayName("하얀 말 처음 위치에서 이동")
    void whiteInitialMove() {
        Position from = Position.from("a2");
        Position to = Position.from("a4");
        Piece pawn = new Pawn(Color.WHITE, from);
        pawn.move(to, Lists.emptyList());
        assertTrue(pawn.hasPosition(to));
    }

    @Test
    @DisplayName("검정 말 처음 위치에서 이동")
    void blackInitialMove() {
        Position from = Position.from("a7");
        Position to = Position.from("a5");
        Piece pawn = new Pawn(Color.BLACK, from);
        pawn.move(to, Lists.emptyList());
        assertTrue(pawn.hasPosition(to));
    }
}
