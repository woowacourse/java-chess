package chess.domain.piece;

import chess.domain.game.EmptyBoardMap;
import chess.domain.location.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class PawnTest {
    private final Map<Position, Piece> emptyBoard = EmptyBoardMap.create();

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
        assertThat(pawn.movablePositions(from, emptyBoard)).containsExactlyInAnyOrder(Position.from("a2"));
    }


    @Test
    @DisplayName("검정 폰 시작점 이동 가능 위치")
    void possiblePositionsAtStart() {
        Position from = Position.from("a7");
        Pawn pawn = new Pawn(Color.BLACK);
        assertThat(pawn.movablePositions(from, emptyBoard)).containsExactlyInAnyOrder(
                Position.from("a6"), Position.from("a5"));
    }


    @Test
    @DisplayName("하얀 폰 기본 이동 가능 위치")
    void whitePossiblePositions() {
        Position from = Position.from("a3");
        Pawn pawn = new Pawn(Color.WHITE);
        assertThat(pawn.movablePositions(from, emptyBoard)).containsExactlyInAnyOrder(Position.from("a4"));
    }


    @Test
    @DisplayName("하얀 폰 시작점 이동 가능 위치")
    void whitePossiblePositionsAtStart() {
        Position from = Position.from("a2");
        Pawn pawn = new Pawn(Color.WHITE);
        assertThat(pawn.movablePositions(from, emptyBoard)).containsExactlyInAnyOrder(
                Position.from("a3"), Position.from("a4"));
    }


    @Test
    void whiteOnPositionTwoKillInit() {
        Position from = Position.from("c2");
        Pawn pawn = new Pawn(Color.WHITE);
        emptyBoard.put(Position.from("b3"), new Pawn(Color.BLACK));
        emptyBoard.put(Position.from("d3"), new Pawn(Color.BLACK));

        assertThat(pawn.movablePositions(from, emptyBoard)).containsExactlyInAnyOrder(
                Position.from("b3"),
                Position.from("c3"),
                Position.from("c4"),
                Position.from("d3"));
    }


    @Test
    void whiteOnPositionOneKillInit() {
        Position from = Position.from("c2");
        Pawn pawn = new Pawn(Color.WHITE);
        emptyBoard.put(Position.from("b3"), new Pawn(Color.BLACK));

        assertThat(pawn.movablePositions(from, emptyBoard)).containsExactlyInAnyOrder(
                Position.from("b3"),
                Position.from("c3"),
                Position.from("c4"));
    }

    @Test
    void whiteOnPositionOneKill() {
        Position from = Position.from("d3");
        Pawn pawn = new Pawn(Color.WHITE);
        emptyBoard.put(Position.from("e4"), new Pawn(Color.BLACK));

        assertThat(pawn.movablePositions(from, emptyBoard)).containsExactlyInAnyOrder(
                Position.from("e4"),
                Position.from("d4"));
    }
}