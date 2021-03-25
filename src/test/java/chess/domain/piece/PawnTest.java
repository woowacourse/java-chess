package chess.domain.piece;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PawnTest {
    @Test
    @DisplayName("폰이 잘 생성되는지 확인")
    void createPawn() {
        final Piece pawn = new Pawn(Color.BLACK, Position.from("a3"));
        assertThat(pawn).isInstanceOf(Pawn.class);
    }

    @Test
    @DisplayName("하얀 말일 때 기본 이동")
    void whiteMove() {
        final Position from = Position.from("a3");
        final Position to = Position.from("a4");
        final Piece pawn = new Pawn(Color.WHITE, from);

        pawn.moveToEmpty(to, new Pieces());
        assertTrue(pawn.hasPosition(to));
    }

    @Test
    @DisplayName("검정 말일 때 기본 이동")
    void blackMove() {
        final Position from = Position.from("a3");
        final Position to = Position.from("a2");
        final Piece pawn = new Pawn(Color.BLACK, from);

        pawn.moveToEmpty(to, new Pieces());
        assertTrue(pawn.hasPosition(to));
    }

    @Test
    @DisplayName("하얀 말 처음 위치에서 이동")
    void whiteInitialMove() {
        final Position from = Position.from("a2");
        final Position to = Position.from("a4");
        final Piece pawn = new Pawn(Color.WHITE, from);

        pawn.moveToEmpty(to, new Pieces());
        assertTrue(pawn.hasPosition(to));
    }

    @Test
    @DisplayName("검정 말 처음 위치에서 이동")
    void blackInitialMove() {
        final Position from = Position.from("a7");
        final Position to = Position.from("a5");
        final Piece pawn = new Pawn(Color.BLACK, from);

        pawn.moveToEmpty(to, new Pieces());
        assertTrue(pawn.hasPosition(to));
    }

    @Test
    @DisplayName("하얀 말 처음 위치에서 이동 방해")
    void whiteInterruptedInitialMove() {
        final Position from = Position.from("a2");
        final Position to = Position.from("a4");
        final Piece pawn = new Pawn(Color.WHITE, from);

        assertThatThrownBy(() -> pawn.moveToEmpty(to, new Pieces(new Pawn(Color.WHITE, Position.from("a3"))))).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("검정 말 처음 위치에서 이동 방해")
    void blackInterruptedInitialMove() {
        final Position from = Position.from("a7");
        final Position to = Position.from("a5");
        final Piece pawn = new Pawn(Color.BLACK, from);

        assertThatThrownBy(() -> pawn.moveToEmpty(to, new Pieces(new Pawn(Color.WHITE, Position.from("a6"))))).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"b1", "d1"})
    @DisplayName("검정 말 잡기")
    void blackKill(String destination) {
        final Position from = Position.from("c2");
        final Position to = Position.from(destination);
        final Piece pawn = new Pawn(Color.BLACK, from);

        pawn.moveForKill(to, new Pieces());
        assertTrue(pawn.hasPosition(to));
    }

    @ParameterizedTest
    @ValueSource(strings = {"g6"})
    @DisplayName("하얀 말 잡기")
    void whiteKill(String destination) {
        final Position from = Position.from("h5");
        final Position to = Position.from(destination);
        final Piece pawn = new Pawn(Color.WHITE, from);

        pawn.moveForKill(to, new Pieces());
        assertTrue(pawn.hasPosition(to));
    }

    @Test
    @DisplayName("하얀 말 잡기 방해")
    void interruptedWhiteKill() {
        final Position from = Position.from("h5");
        final Position to = Position.from("h6");
        final Piece pawn = new Pawn(Color.WHITE, from);

        assertThatThrownBy(() -> pawn.moveForKill(to, new Pieces())).isInstanceOf(IllegalArgumentException.class);
    }
}
