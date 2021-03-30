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
        Piece pawn = new Pawn(Color.BLACK, Position.from("a3"));
        assertThat(pawn).isInstanceOf(Pawn.class);
    }

    @Test
    @DisplayName("하얀 말일 때 기본 이동")
    void whiteMove() {
        Position from = Position.from("a3");
        Position to = Position.from("a4");
        Piece pawn = new Pawn(Color.WHITE, from);
        pawn.moveToEmpty(to, new Pieces());
        assertTrue(pawn.hasPosition(to));
    }

    @Test
    @DisplayName("검정 말일 때 기본 이동")
    void blackMove() {
        Position from = Position.from("a3");
        Position to = Position.from("a2");
        Piece pawn = new Pawn(Color.BLACK, from);
        pawn.moveToEmpty(to, new Pieces());
        assertTrue(pawn.hasPosition(to));
    }

    @Test
    @DisplayName("하얀 말 처음 위치에서 이동")
    void whiteInitialMove() {
        Position from = Position.from("a2");
        Position to = Position.from("a4");
        Piece pawn = new Pawn(Color.WHITE, from);
        pawn.moveToEmpty(to, new Pieces());
        assertTrue(pawn.hasPosition(to));
    }

    @Test
    @DisplayName("검정 말 처음 위치에서 이동")
    void blackInitialMove() {
        Position from = Position.from("a7");
        Position to = Position.from("a5");
        Piece pawn = new Pawn(Color.BLACK, from);
        pawn.moveToEmpty(to, new Pieces());
        assertTrue(pawn.hasPosition(to));
    }

    @Test
    @DisplayName("하얀 말 처음 위치에서 이동 방해")
    void whiteInterruptedInitialMove() {
        Position from = Position.from("a2");
        Position to = Position.from("a4");
        Piece pawn = new Pawn(Color.WHITE, from);
        assertThatThrownBy(() -> pawn.moveToEmpty(to, new Pieces(new Pawn(Color.WHITE, Position.from("a3"))))).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("검정 말 처음 위치에서 이동 방해")
    void blackInterruptedInitialMove() {
        Position from = Position.from("a7");
        Position to = Position.from("a5");
        Piece pawn = new Pawn(Color.BLACK, from);
        assertThatThrownBy(() -> pawn.moveToEmpty(to, new Pieces(new Pawn(Color.WHITE, Position.from("a6"))))).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"b1", "d1"})
    @DisplayName("검정 말 잡기")
    void blackKill(String destination) {
        Position from = Position.from("c2");
        Position to = Position.from(destination);
        Piece pawn = new Pawn(Color.BLACK, from);
        pawn.moveForKill(to, new Pieces());
        assertTrue(pawn.hasPosition(to));
    }

    @ParameterizedTest
    @ValueSource(strings = {"g6"})
    @DisplayName("하얀 말 잡기")
    void whiteKill(String destination) {
        Position from = Position.from("h5");
        Position to = Position.from(destination);
        Piece pawn = new Pawn(Color.WHITE, from);
        pawn.moveForKill(to, new Pieces());
        assertTrue(pawn.hasPosition(to));
    }

    @Test
    @DisplayName("하얀 말 잡기 방해")
    void interruptedWhiteKill() {
        Position from = Position.from("h5");
        Position to = Position.from("h6");
        Piece pawn = new Pawn(Color.WHITE, from);

        assertThatThrownBy(() -> pawn.moveForKill(to, new Pieces())).isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    @DisplayName("검정 폰 기본 이동 가능 위치")
    void possiblePositions() {
        Position from = Position.from("a3");
        Pawn pawn = new Pawn(Color.BLACK, from);
        assertThat(pawn.movablePositions(from)).contains(Position.from("a2"));
    }


    @Test
    @DisplayName("검정 폰 시작점 이동 가능 위치")
    void possiblePositionsAtStart() {
        Position from = Position.from("a7");
        Pawn pawn = new Pawn(Color.BLACK, from);
        assertThat(pawn.movablePositions(from)).contains(
                Position.from("a5"),
                Position.from("a6")
        );
    }


    @Test
    @DisplayName("하얀 폰 기본 이동 가능 위치")
    void whitePossiblePositions() {
        Position from = Position.from("a3");
        Pawn pawn = new Pawn(Color.WHITE, from);
        assertThat(pawn.movablePositions(from)).contains(Position.from("a4"));
    }


    @Test
    @DisplayName("하얀 폰 시작점 이동 가능 위치")
    void whitePossiblePositionsAtStart() {
        Position from = Position.from("a2");
        Pawn pawn = new Pawn(Color.WHITE, from);
        assertThat(pawn.movablePositions(from)).contains(
                Position.from("a3"),
                Position.from("a4")
        );
    }

    @Test
    @DisplayName("검정 폰 kill 가능 위치")
    void blackKillablePositionsAtStart() {
        Position from = Position.from("b2");
        Pawn pawn = new Pawn(Color.BLACK, from);
        assertThat(pawn.killablePositions(from)).contains(
                Position.from("a1"),
                Position.from("c1")
        );
    }
    @Test
    @DisplayName("하얀 폰 kill 가능 위치")
    void whileKillablePositionsAtStart() {
        Position from = Position.from("b2");
        Pawn pawn = new Pawn(Color.WHITE, from);
        assertThat(pawn.killablePositions(from)).contains(
                Position.from("a3"),
                Position.from("c3")
        );
    }

}
