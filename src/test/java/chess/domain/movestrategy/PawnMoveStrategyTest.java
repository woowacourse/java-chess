package chess.domain.movestrategy;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.Rank;
import chess.domain.board.position.InitPosition;
import chess.domain.board.position.Position;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PawnMoveStrategyTest {

    private Map<Position, Piece> squares;

    @BeforeEach
    void setUp() {
        this.squares = InitPosition.initSquares();
    }

    @Test
    @DisplayName("화이트 폰 초기 이동 가능 경로 확인")
    void whitePawnStartMovable() {
        this.squares.replace(Position.of("c3"), Pawn.createBlack());
        this.squares.replace(Position.of("d2"), Pawn.createWhite());

        Board board = new Board(this.squares);

        MoveStrategy moveStrategy = Pawn.createWhite().moveStrategy();
        assertThat(moveStrategy.currentPositionMoveStrategy(board, Position.of("d2"))).containsExactlyInAnyOrder(
            Position.of("d3"), Position.of("d4"), Position.of("c3"));
    }

    @Test
    @DisplayName("화이트 폰 이동 가능 경로 확인")
    void whitePawnMovable() {
        this.squares.replace(Position.of("e4"), Pawn.createBlack());
        this.squares.replace(Position.of("d3"), Pawn.createWhite());

        Board board = new Board(this.squares);

        MoveStrategy moveStrategy = Pawn.createWhite().moveStrategy();
        assertThat(moveStrategy.currentPositionMoveStrategy(board, Position.of("d3"))).containsExactlyInAnyOrder(
            Position.of("d4"), Position.of("e4"));
    }

    @Test
    @DisplayName("블랙 폰 초기 이동 가능 경로 확인")
    void blackPawnStartMovable() {
        this.squares.replace(Position.of("c6"), Pawn.createWhite());
        this.squares.replace(Position.of("d7"), Pawn.createBlack());

        Board board = new Board(this.squares);

        MoveStrategy moveStrategy = Pawn.createBlack().moveStrategy();
        assertThat(moveStrategy.currentPositionMoveStrategy(board, Position.of("d7"))).containsExactlyInAnyOrder(
            Position.of("d6"), Position.of("d5"),
            Position.of("c6"));
    }

    @Test
    @DisplayName("블랙 폰 이동 가능 경로 확인")
    void blackPawnMovable() {
        this.squares.replace(Position.of("e5"), Pawn.createWhite());
        this.squares.replace(Position.of("d6"), Pawn.createBlack());

        Board board = new Board(this.squares);

        MoveStrategy moveStrategy = Pawn.createBlack().moveStrategy();
        assertThat(moveStrategy.currentPositionMoveStrategy(board, Position.of("d6"))).containsExactlyInAnyOrder(
            Position.of("d5"), Position.of("e5"));
    }

    @Test
    @DisplayName("앞이 막혔을 때 화이트 폰 초기 이동 가능 경로 확인")
    void whitePawnStartMovableWhenBlocked() {
        this.squares.replace(Position.of("d3"), Pawn.createBlack());
        this.squares.replace(Position.of("d2"), Pawn.createWhite());

        Board board = new Board(this.squares);

        MoveStrategy moveStrategy = Pawn.createWhite().moveStrategy();
        assertThat(moveStrategy.currentPositionMoveStrategy(board, Position.of("d2"))).containsExactlyInAnyOrder();
    }

    @Test
    @DisplayName("앞이 막혔을 때 블랙 폰 초기 이동 가능 경로 확인")
    void blackPawnStartMovableWhenBlocked() {
        this.squares.replace(Position.of("d6"), Pawn.createBlack());
        this.squares.replace(Position.of("d7"), Pawn.createBlack());

        Board board = new Board(this.squares);

        MoveStrategy moveStrategy = Pawn.createBlack().moveStrategy();
        assertThat(moveStrategy.currentPositionMoveStrategy(board, Position.of("d7"))).containsExactlyInAnyOrder();
    }
}
