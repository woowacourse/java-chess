package chess.domain.piece;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class PawnTest {
    @Test
    @DisplayName("폰이 잘 생성되는지 확인")
    void createPawn() {
        Piece pawn = new Pawn(Color.BLACK);
        assertThat(pawn).isInstanceOf(Pawn.class);
    }

    @Test
    @DisplayName("검정 폰 기본 이동 가능 위치")
    void possiblePositions() {
        Position from = Position.from("a3");
        Pawn pawn = new Pawn(Color.BLACK);
        assertThat(pawn.movablePositions(from)).contains(Arrays.asList(Position.from("a2")));
    }


    @Test
    @DisplayName("검정 폰 시작점 이동 가능 위치")
    void possiblePositionsAtStart() {
        Position from = Position.from("a7");
        Pawn pawn = new Pawn(Color.BLACK);
        assertThat(pawn.movablePositions(from)).contains(
                Arrays.asList(Position.from("a6"), Position.from("a5")));
    }


    @Test
    @DisplayName("하얀 폰 기본 이동 가능 위치")
    void whitePossiblePositions() {
        Position from = Position.from("a3");
        Pawn pawn = new Pawn(Color.WHITE);
        assertThat(pawn.movablePositions(from)).contains(Arrays.asList(Position.from("a4")));
    }


    @Test
    @DisplayName("하얀 폰 시작점 이동 가능 위치")
    void whitePossiblePositionsAtStart() {
        Position from = Position.from("a2");
        Pawn pawn = new Pawn(Color.WHITE);
        assertThat(pawn.movablePositions(from)).contains(
                Arrays.asList(Position.from("a3"), Position.from("a4")));
    }

    @Test
    @DisplayName("검정 폰 kill 가능 위치")
    void blackKillablePositionsAtStart() {
        Position from = Position.from("b2");
        Pawn pawn = new Pawn(Color.BLACK);
        assertThat(pawn.killablePositions(from)
                       .get(0)).contains(
                Position.from("a1"), Position.from("c1"));
    }

    @Test
    @DisplayName("하얀 폰 kill 가능 위치")
    void whileKillablePositionsAtStart() {
        Position from = Position.from("b2");
        Pawn pawn = new Pawn(Color.WHITE);
        assertThat(pawn.killablePositions(from)
                       .get(0)).contains(
                Position.from("a3"),
                Position.from("c3")
        );
    }
}
