package chess.domain.movestrategy;

import chess.domain.board.Board;
import chess.domain.board.BoardGenerator;
import chess.domain.board.position.Position;
import chess.domain.piece.Pawn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PawnMoveStrategyTest {
    private BoardGenerator boardGenerator;

    @BeforeEach
    void setUp() {
        boardGenerator = new BoardGenerator();
    }

    @Test
    @DisplayName("화이트 폰 초기 이동 가능 경로 확인")
    void whitePawnStartMovable() {
        boardGenerator.put(Position.of("c3"), Pawn.createBlack());
        boardGenerator.put(Position.of("d2"), Pawn.createWhite());
        Board board = new Board(boardGenerator.create());
        MoveStrategy moveStrategy = Pawn.createWhite().moveStrategy();
        assertThat(moveStrategy.movable(board, Position.of("d2"))).containsExactlyInAnyOrder(
                Position.of("d3"), Position.of("d4"), Position.of("c3"));
    }

    @Test
    @DisplayName("화이트 폰 이동 가능 경로 확인")
    void whitePawnMovable() {
        boardGenerator.put(Position.of("e4"), Pawn.createBlack());
        boardGenerator.put(Position.of("d3"), Pawn.createWhite());
        Board board = new Board(boardGenerator.create());
        MoveStrategy moveStrategy = Pawn.createWhite().moveStrategy();
        assertThat(moveStrategy.movable(board, Position.of("d3"))).containsExactlyInAnyOrder(
                Position.of("d4"), Position.of("e4"));
    }

    @Test
    @DisplayName("블랙 폰 초기 이동 가능 경로 확인")
    void blackPawnStartMovable() {
        boardGenerator.put(Position.of("c6"), Pawn.createWhite());
        boardGenerator.put(Position.of("d7"), Pawn.createBlack());
        Board board = new Board(boardGenerator.create());
        MoveStrategy moveStrategy = Pawn.createBlack().moveStrategy();
        assertThat(moveStrategy.movable(board, Position.of("d7"))).containsExactlyInAnyOrder(
                Position.of("d6"), Position.of("d5"),
                Position.of("c6"));
    }

    @Test
    @DisplayName("블랙 폰 이동 가능 경로 확인")
    void blackPawnMovable() {
        boardGenerator.put(Position.of("e5"), Pawn.createWhite());
        boardGenerator.put(Position.of("d6"), Pawn.createBlack());
        Board board = new Board(boardGenerator.create());
        MoveStrategy moveStrategy = Pawn.createBlack().moveStrategy();
        assertThat(moveStrategy.movable(board, Position.of("d6"))).containsExactlyInAnyOrder(
                Position.of("d5"), Position.of("e5"));
    }
}
