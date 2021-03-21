package chess.domain.movestrategy;

import chess.domain.board.Board;
import chess.domain.board.BoardGenerator;
import chess.domain.board.position.Position;
import chess.domain.piece.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CommonMoveStrategyTest {
    private BoardGenerator boardGenerator;

    @BeforeEach
    void setUp() {
        boardGenerator = new BoardGenerator();
    }

    @Test
    @DisplayName("주변이 아군일 때 룩 이동 가능 경로 확인")
    void rookMovableSurroundedByFriends() {
        boardGenerator.put(Position.of("c7"), Pawn.createWhite());
        boardGenerator.put(Position.of("d7"), Pawn.createWhite());
        boardGenerator.put(Position.of("e7"), Pawn.createWhite());
        boardGenerator.put(Position.of("f7"), Pawn.createWhite());
        boardGenerator.put(Position.of("g7"), Pawn.createWhite());

        boardGenerator.put(Position.of("c3"), Pawn.createWhite());
        boardGenerator.put(Position.of("d3"), Pawn.createWhite());
        boardGenerator.put(Position.of("e3"), Pawn.createWhite());
        boardGenerator.put(Position.of("f3"), Pawn.createWhite());
        boardGenerator.put(Position.of("g3"), Pawn.createWhite());

        boardGenerator.put(Position.of("c6"), Pawn.createWhite());
        boardGenerator.put(Position.of("c5"), Pawn.createWhite());
        boardGenerator.put(Position.of("c4"), Pawn.createWhite());

        boardGenerator.put(Position.of("g6"), Pawn.createWhite());
        boardGenerator.put(Position.of("g5"), Pawn.createWhite());
        boardGenerator.put(Position.of("g4"), Pawn.createWhite());

        boardGenerator.put(Position.of("e5"), Rook.createWhite());

        boardGenerator.print();

        Board board = new Board(boardGenerator.create());

        MoveStrategy moveStrategy = Rook.createWhite().moveStrategy();
        assertThat(moveStrategy.movable(board, Position.of("e5"))).containsExactlyInAnyOrder(
                Position.of("e6"), Position.of("e4"),
                Position.of("d5"), Position.of("f5"));
    }

    @Test
    @DisplayName("주변이 적일 때 룩 이동 가능 경로 확인")
    void rookMovableSurroundedByEnemies() {
        boardGenerator.put(Position.of("c7"), Pawn.createBlack());
        boardGenerator.put(Position.of("d7"), Pawn.createBlack());
        boardGenerator.put(Position.of("e7"), Pawn.createBlack());
        boardGenerator.put(Position.of("f7"), Pawn.createBlack());
        boardGenerator.put(Position.of("g7"), Pawn.createBlack());

        boardGenerator.put(Position.of("c3"), Pawn.createBlack());
        boardGenerator.put(Position.of("d3"), Pawn.createBlack());
        boardGenerator.put(Position.of("e3"), Pawn.createBlack());
        boardGenerator.put(Position.of("f3"), Pawn.createBlack());
        boardGenerator.put(Position.of("g3"), Pawn.createBlack());

        boardGenerator.put(Position.of("c6"), Pawn.createBlack());
        boardGenerator.put(Position.of("c5"), Pawn.createBlack());
        boardGenerator.put(Position.of("c4"), Pawn.createBlack());

        boardGenerator.put(Position.of("g6"), Pawn.createBlack());
        boardGenerator.put(Position.of("g5"), Pawn.createBlack());
        boardGenerator.put(Position.of("g4"), Pawn.createBlack());

        boardGenerator.put(Position.of("e5"), Rook.createWhite());

        boardGenerator.print();

        Board board = new Board(boardGenerator.create());

        MoveStrategy moveStrategy = Rook.createWhite().moveStrategy();
        assertThat(moveStrategy.movable(board, Position.of("e5"))).containsExactlyInAnyOrder(
                Position.of("e6"), Position.of("e7"),
                Position.of("e4"), Position.of("e3"),
                Position.of("d5"), Position.of("c5"),
                Position.of("f5"), Position.of("g5"));
    }

    @Test
    @DisplayName("주변이 아군일 때 비숍 이동 가능 경로 확인")
    void bishopMovableSurroundedByFriends() {
        boardGenerator.put(Position.of("c7"), Pawn.createWhite());
        boardGenerator.put(Position.of("d7"), Pawn.createWhite());
        boardGenerator.put(Position.of("e7"), Pawn.createWhite());
        boardGenerator.put(Position.of("f7"), Pawn.createWhite());
        boardGenerator.put(Position.of("g7"), Pawn.createWhite());

        boardGenerator.put(Position.of("c3"), Pawn.createWhite());
        boardGenerator.put(Position.of("d3"), Pawn.createWhite());
        boardGenerator.put(Position.of("e3"), Pawn.createWhite());
        boardGenerator.put(Position.of("f3"), Pawn.createWhite());
        boardGenerator.put(Position.of("g3"), Pawn.createWhite());

        boardGenerator.put(Position.of("c6"), Pawn.createWhite());
        boardGenerator.put(Position.of("c5"), Pawn.createWhite());
        boardGenerator.put(Position.of("c4"), Pawn.createWhite());

        boardGenerator.put(Position.of("g6"), Pawn.createWhite());
        boardGenerator.put(Position.of("g5"), Pawn.createWhite());
        boardGenerator.put(Position.of("g4"), Pawn.createWhite());

        boardGenerator.put(Position.of("e5"), Bishop.createWhite());

        boardGenerator.print();

        Board board = new Board(boardGenerator.create());

        MoveStrategy moveStrategy = Bishop.createWhite().moveStrategy();
        assertThat(moveStrategy.movable(board, Position.of("e5"))).containsExactlyInAnyOrder(
                Position.of("d6"), Position.of("f6"),
                Position.of("d4"), Position.of("f4"));
    }

    @Test
    @DisplayName("주변이 적일 때 비숍 이동 가능 경로 확인")
    void bishopMovableSurroundedByEnemies() {
        boardGenerator.put(Position.of("c7"), Pawn.createBlack());
        boardGenerator.put(Position.of("d7"), Pawn.createBlack());
        boardGenerator.put(Position.of("e7"), Pawn.createBlack());
        boardGenerator.put(Position.of("f7"), Pawn.createBlack());
        boardGenerator.put(Position.of("g7"), Pawn.createBlack());

        boardGenerator.put(Position.of("c3"), Pawn.createBlack());
        boardGenerator.put(Position.of("d3"), Pawn.createBlack());
        boardGenerator.put(Position.of("e3"), Pawn.createBlack());
        boardGenerator.put(Position.of("f3"), Pawn.createBlack());
        boardGenerator.put(Position.of("g3"), Pawn.createBlack());

        boardGenerator.put(Position.of("c6"), Pawn.createBlack());
        boardGenerator.put(Position.of("c5"), Pawn.createBlack());
        boardGenerator.put(Position.of("c4"), Pawn.createBlack());

        boardGenerator.put(Position.of("g6"), Pawn.createBlack());
        boardGenerator.put(Position.of("g5"), Pawn.createBlack());
        boardGenerator.put(Position.of("g4"), Pawn.createBlack());

        boardGenerator.put(Position.of("e5"), Bishop.createWhite());

        boardGenerator.print();

        Board board = new Board(boardGenerator.create());

        MoveStrategy moveStrategy = Bishop.createWhite().moveStrategy();
        assertThat(moveStrategy.movable(board, Position.of("e5"))).containsExactlyInAnyOrder(
                Position.of("d6"), Position.of("c7"),
                Position.of("f6"), Position.of("g7"),
                Position.of("d4"), Position.of("c3"),
                Position.of("f4"), Position.of("g3"));
    }

    @Test
    @DisplayName("주변이 아군일 때 퀸 이동 가능 경로 확인")
    void queenMovableSurroundedByFriends() {
        boardGenerator.put(Position.of("c7"), Pawn.createWhite());
        boardGenerator.put(Position.of("d7"), Pawn.createWhite());
        boardGenerator.put(Position.of("e7"), Pawn.createWhite());
        boardGenerator.put(Position.of("f7"), Pawn.createWhite());
        boardGenerator.put(Position.of("g7"), Pawn.createWhite());

        boardGenerator.put(Position.of("c3"), Pawn.createWhite());
        boardGenerator.put(Position.of("d3"), Pawn.createWhite());
        boardGenerator.put(Position.of("e3"), Pawn.createWhite());
        boardGenerator.put(Position.of("f3"), Pawn.createWhite());
        boardGenerator.put(Position.of("g3"), Pawn.createWhite());

        boardGenerator.put(Position.of("c6"), Pawn.createWhite());
        boardGenerator.put(Position.of("c5"), Pawn.createWhite());
        boardGenerator.put(Position.of("c4"), Pawn.createWhite());

        boardGenerator.put(Position.of("g6"), Pawn.createWhite());
        boardGenerator.put(Position.of("g5"), Pawn.createWhite());
        boardGenerator.put(Position.of("g4"), Pawn.createWhite());

        boardGenerator.put(Position.of("e5"), Queen.createWhite());

        boardGenerator.print();

        Board board = new Board(boardGenerator.create());

        MoveStrategy moveStrategy = Queen.createWhite().moveStrategy();
        assertThat(moveStrategy.movable(board, Position.of("e5"))).containsExactlyInAnyOrder(
                Position.of("e6"), Position.of("e4"),
                Position.of("d5"), Position.of("f5"),
                Position.of("d6"), Position.of("f6"),
                Position.of("d4"), Position.of("f4"));
    }

    @Test
    @DisplayName("주변이 적일 때 퀸 이동 가능 경로 확인")
    void queenMovableSurroundedByEnemies() {
        boardGenerator.put(Position.of("c7"), Pawn.createBlack());
        boardGenerator.put(Position.of("d7"), Pawn.createBlack());
        boardGenerator.put(Position.of("e7"), Pawn.createBlack());
        boardGenerator.put(Position.of("f7"), Pawn.createBlack());
        boardGenerator.put(Position.of("g7"), Pawn.createBlack());

        boardGenerator.put(Position.of("c3"), Pawn.createBlack());
        boardGenerator.put(Position.of("d3"), Pawn.createBlack());
        boardGenerator.put(Position.of("e3"), Pawn.createBlack());
        boardGenerator.put(Position.of("f3"), Pawn.createBlack());
        boardGenerator.put(Position.of("g3"), Pawn.createBlack());

        boardGenerator.put(Position.of("c6"), Pawn.createBlack());
        boardGenerator.put(Position.of("c5"), Pawn.createBlack());
        boardGenerator.put(Position.of("c4"), Pawn.createBlack());

        boardGenerator.put(Position.of("g6"), Pawn.createBlack());
        boardGenerator.put(Position.of("g5"), Pawn.createBlack());
        boardGenerator.put(Position.of("g4"), Pawn.createBlack());

        boardGenerator.put(Position.of("e5"), Queen.createWhite());

        boardGenerator.print();

        Board board = new Board(boardGenerator.create());

        MoveStrategy moveStrategy = Queen.createWhite().moveStrategy();
        assertThat(moveStrategy.movable(board, Position.of("e5"))).containsExactlyInAnyOrder(
                Position.of("e6"), Position.of("e7"),
                Position.of("e4"), Position.of("e3"),
                Position.of("d5"), Position.of("c5"),
                Position.of("f5"), Position.of("g5"),
                Position.of("d6"), Position.of("c7"),
                Position.of("f6"), Position.of("g7"),
                Position.of("d4"), Position.of("c3"),
                Position.of("f4"), Position.of("g3"));
    }

    @Test
    @DisplayName("주변이 아군일 때 킹 이동 가능 경로 확인")
    void kingMovableSurroundedByFriends() {
        boardGenerator.put(Position.of("d7"), Pawn.createWhite());
        boardGenerator.put(Position.of("e7"), Pawn.createWhite());
        boardGenerator.put(Position.of("f7"), Pawn.createWhite());

        boardGenerator.put(Position.of("d3"), Pawn.createWhite());
        boardGenerator.put(Position.of("e3"), Pawn.createWhite());
        boardGenerator.put(Position.of("f3"), Pawn.createWhite());

        boardGenerator.put(Position.of("d6"), Pawn.createWhite());
        boardGenerator.put(Position.of("d5"), Pawn.createWhite());
        boardGenerator.put(Position.of("d4"), Pawn.createWhite());

        boardGenerator.put(Position.of("f6"), Pawn.createWhite());
        boardGenerator.put(Position.of("f5"), Pawn.createWhite());
        boardGenerator.put(Position.of("f4"), Pawn.createWhite());

        boardGenerator.put(Position.of("e5"), King.createWhite());

        boardGenerator.print();

        Board board = new Board(boardGenerator.create());

        MoveStrategy moveStrategy = King.createWhite().moveStrategy();
        assertThat(moveStrategy.movable(board, Position.of("e5"))).containsExactlyInAnyOrder(
                Position.of("e6"), Position.of("e4"));
    }

    @Test
    @DisplayName("주변이 적일 때 킹 이동 가능 경로 확인")
    void kingMovableSurroundedByEnemies() {
        boardGenerator.put(Position.of("d7"), Pawn.createBlack());
        boardGenerator.put(Position.of("e7"), Pawn.createBlack());
        boardGenerator.put(Position.of("f7"), Pawn.createBlack());

        boardGenerator.put(Position.of("d3"), Pawn.createBlack());
        boardGenerator.put(Position.of("e3"), Pawn.createBlack());
        boardGenerator.put(Position.of("f3"), Pawn.createBlack());

        boardGenerator.put(Position.of("d6"), Pawn.createBlack());
        boardGenerator.put(Position.of("d5"), Pawn.createBlack());
        boardGenerator.put(Position.of("d4"), Pawn.createBlack());

        boardGenerator.put(Position.of("f6"), Pawn.createBlack());
        boardGenerator.put(Position.of("f5"), Pawn.createBlack());
        boardGenerator.put(Position.of("f4"), Pawn.createBlack());

        boardGenerator.put(Position.of("e5"), King.createWhite());

        boardGenerator.print();

        Board board = new Board(boardGenerator.create());

        MoveStrategy moveStrategy = King.createWhite().moveStrategy();
        assertThat(moveStrategy.movable(board, Position.of("e5"))).containsExactlyInAnyOrder(
                Position.of("e6"), Position.of("e4"),
                Position.of("d5"), Position.of("f5"),
                Position.of("d6"), Position.of("f6"),
                Position.of("d4"), Position.of("f4"));
    }
}
