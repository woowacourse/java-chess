package chess.domain.movestrategy;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.InitPieces;
import chess.domain.board.Rank;
import chess.domain.board.position.Position;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CommonMoveStrategyTest {

    private List<Rank> ranks;

    @BeforeEach
    void setUp() {
        this.ranks = InitPieces.initRanks();
    }

    @Test
    @DisplayName("주변이 아군일 때 룩 이동 가능 경로 확인")
    void rookMovableSurroundedByFriends() {

        replacePieceForTest(Position.of("c7"), Pawn.createWhite());
        replacePieceForTest(Position.of("d7"), Pawn.createWhite());
        replacePieceForTest(Position.of("e7"), Pawn.createWhite());
        replacePieceForTest(Position.of("f7"), Pawn.createWhite());
        replacePieceForTest(Position.of("g7"), Pawn.createWhite());

        replacePieceForTest(Position.of("c3"), Pawn.createWhite());
        replacePieceForTest(Position.of("d3"), Pawn.createWhite());
        replacePieceForTest(Position.of("e3"), Pawn.createWhite());
        replacePieceForTest(Position.of("f3"), Pawn.createWhite());
        replacePieceForTest(Position.of("g3"), Pawn.createWhite());

        replacePieceForTest(Position.of("c6"), Pawn.createWhite());
        replacePieceForTest(Position.of("c5"), Pawn.createWhite());
        replacePieceForTest(Position.of("c4"), Pawn.createWhite());

        replacePieceForTest(Position.of("g6"), Pawn.createWhite());
        replacePieceForTest(Position.of("g5"), Pawn.createWhite());
        replacePieceForTest(Position.of("g4"), Pawn.createWhite());

        replacePieceForTest(Position.of("e5"), Rook.createWhite());

        Board board = new Board(this.ranks);

        MoveStrategy moveStrategy = Rook.createWhite().moveStrategy();
        assertThat(moveStrategy.moveStrategy(board, Position.of("e5"))).containsExactlyInAnyOrder(
            Position.of("e6"), Position.of("e4"),
            Position.of("d5"), Position.of("f5"));
    }

    @Test
    @DisplayName("주변이 적일 때 룩 이동 가능 경로 확인")
    void rookMovableSurroundedByEnemies() {
        replacePieceForTest(Position.of("c7"), Pawn.createBlack());
        replacePieceForTest(Position.of("d7"), Pawn.createBlack());
        replacePieceForTest(Position.of("e7"), Pawn.createBlack());
        replacePieceForTest(Position.of("f7"), Pawn.createBlack());
        replacePieceForTest(Position.of("g7"), Pawn.createBlack());

        replacePieceForTest(Position.of("c3"), Pawn.createBlack());
        replacePieceForTest(Position.of("d3"), Pawn.createBlack());
        replacePieceForTest(Position.of("e3"), Pawn.createBlack());
        replacePieceForTest(Position.of("f3"), Pawn.createBlack());
        replacePieceForTest(Position.of("g3"), Pawn.createBlack());

        replacePieceForTest(Position.of("c6"), Pawn.createBlack());
        replacePieceForTest(Position.of("c5"), Pawn.createBlack());
        replacePieceForTest(Position.of("c4"), Pawn.createBlack());

        replacePieceForTest(Position.of("g6"), Pawn.createBlack());
        replacePieceForTest(Position.of("g5"), Pawn.createBlack());
        replacePieceForTest(Position.of("g4"), Pawn.createBlack());

        replacePieceForTest(Position.of("e5"), Rook.createWhite());

        Board board = new Board(this.ranks);

        MoveStrategy moveStrategy = Rook.createWhite().moveStrategy();
        assertThat(moveStrategy.moveStrategy(board, Position.of("e5"))).containsExactlyInAnyOrder(
            Position.of("e6"), Position.of("e7"),
            Position.of("e4"), Position.of("e3"),
            Position.of("d5"), Position.of("c5"),
            Position.of("f5"), Position.of("g5"));
    }

    @Test
    @DisplayName("주변이 아군일 때 비숍 이동 가능 경로 확인")
    void bishopMovableSurroundedByFriends() {
        replacePieceForTest(Position.of("c7"), Pawn.createWhite());
        replacePieceForTest(Position.of("d7"), Pawn.createWhite());
        replacePieceForTest(Position.of("e7"), Pawn.createWhite());
        replacePieceForTest(Position.of("f7"), Pawn.createWhite());
        replacePieceForTest(Position.of("g7"), Pawn.createWhite());

        replacePieceForTest(Position.of("c3"), Pawn.createWhite());
        replacePieceForTest(Position.of("d3"), Pawn.createWhite());
        replacePieceForTest(Position.of("e3"), Pawn.createWhite());
        replacePieceForTest(Position.of("f3"), Pawn.createWhite());
        replacePieceForTest(Position.of("g3"), Pawn.createWhite());

        replacePieceForTest(Position.of("c6"), Pawn.createWhite());
        replacePieceForTest(Position.of("c5"), Pawn.createWhite());
        replacePieceForTest(Position.of("c4"), Pawn.createWhite());

        replacePieceForTest(Position.of("g6"), Pawn.createWhite());
        replacePieceForTest(Position.of("g5"), Pawn.createWhite());
        replacePieceForTest(Position.of("g4"), Pawn.createWhite());

        replacePieceForTest(Position.of("e5"), Bishop.createWhite());

        Board board = new Board(this.ranks);

        MoveStrategy moveStrategy = Bishop.createWhite().moveStrategy();
        assertThat(moveStrategy.moveStrategy(board, Position.of("e5"))).containsExactlyInAnyOrder(
            Position.of("d6"), Position.of("f6"),
            Position.of("d4"), Position.of("f4"));
    }

    @Test
    @DisplayName("주변이 적일 때 비숍 이동 가능 경로 확인")
    void bishopMovableSurroundedByEnemies() {
        replacePieceForTest(Position.of("c7"), Pawn.createBlack());
        replacePieceForTest(Position.of("d7"), Pawn.createBlack());
        replacePieceForTest(Position.of("e7"), Pawn.createBlack());
        replacePieceForTest(Position.of("f7"), Pawn.createBlack());
        replacePieceForTest(Position.of("g7"), Pawn.createBlack());

        replacePieceForTest(Position.of("c3"), Pawn.createBlack());
        replacePieceForTest(Position.of("d3"), Pawn.createBlack());
        replacePieceForTest(Position.of("e3"), Pawn.createBlack());
        replacePieceForTest(Position.of("f3"), Pawn.createBlack());
        replacePieceForTest(Position.of("g3"), Pawn.createBlack());

        replacePieceForTest(Position.of("c6"), Pawn.createBlack());
        replacePieceForTest(Position.of("c5"), Pawn.createBlack());
        replacePieceForTest(Position.of("c4"), Pawn.createBlack());

        replacePieceForTest(Position.of("g6"), Pawn.createBlack());
        replacePieceForTest(Position.of("g5"), Pawn.createBlack());
        replacePieceForTest(Position.of("g4"), Pawn.createBlack());

        replacePieceForTest(Position.of("e5"), Bishop.createWhite());

        Board board = new Board(this.ranks);

        MoveStrategy moveStrategy = Bishop.createWhite().moveStrategy();
        assertThat(moveStrategy.moveStrategy(board, Position.of("e5"))).containsExactlyInAnyOrder(
            Position.of("d6"), Position.of("c7"),
            Position.of("f6"), Position.of("g7"),
            Position.of("d4"), Position.of("c3"),
            Position.of("f4"), Position.of("g3"));
    }

    @Test
    @DisplayName("주변이 아군일 때 퀸 이동 가능 경로 확인")
    void queenMovableSurroundedByFriends() {
        replacePieceForTest(Position.of("c7"), Pawn.createWhite());
        replacePieceForTest(Position.of("d7"), Pawn.createWhite());
        replacePieceForTest(Position.of("e7"), Pawn.createWhite());
        replacePieceForTest(Position.of("f7"), Pawn.createWhite());
        replacePieceForTest(Position.of("g7"), Pawn.createWhite());

        replacePieceForTest(Position.of("c3"), Pawn.createWhite());
        replacePieceForTest(Position.of("d3"), Pawn.createWhite());
        replacePieceForTest(Position.of("e3"), Pawn.createWhite());
        replacePieceForTest(Position.of("f3"), Pawn.createWhite());
        replacePieceForTest(Position.of("g3"), Pawn.createWhite());

        replacePieceForTest(Position.of("c6"), Pawn.createWhite());
        replacePieceForTest(Position.of("c5"), Pawn.createWhite());
        replacePieceForTest(Position.of("c4"), Pawn.createWhite());

        replacePieceForTest(Position.of("g6"), Pawn.createWhite());
        replacePieceForTest(Position.of("g5"), Pawn.createWhite());
        replacePieceForTest(Position.of("g4"), Pawn.createWhite());

        replacePieceForTest(Position.of("e5"), Queen.createWhite());

        Board board = new Board(this.ranks);

        MoveStrategy moveStrategy = Queen.createWhite().moveStrategy();
        assertThat(moveStrategy.moveStrategy(board, Position.of("e5"))).containsExactlyInAnyOrder(
            Position.of("e6"), Position.of("e4"),
            Position.of("d5"), Position.of("f5"),
            Position.of("d6"), Position.of("f6"),
            Position.of("d4"), Position.of("f4"));
    }

    @Test
    @DisplayName("주변이 적일 때 퀸 이동 가능 경로 확인")
    void queenMovableSurroundedByEnemies() {
        replacePieceForTest(Position.of("c7"), Pawn.createBlack());
        replacePieceForTest(Position.of("d7"), Pawn.createBlack());
        replacePieceForTest(Position.of("e7"), Pawn.createBlack());
        replacePieceForTest(Position.of("f7"), Pawn.createBlack());
        replacePieceForTest(Position.of("g7"), Pawn.createBlack());

        replacePieceForTest(Position.of("c3"), Pawn.createBlack());
        replacePieceForTest(Position.of("d3"), Pawn.createBlack());
        replacePieceForTest(Position.of("e3"), Pawn.createBlack());
        replacePieceForTest(Position.of("f3"), Pawn.createBlack());
        replacePieceForTest(Position.of("g3"), Pawn.createBlack());

        replacePieceForTest(Position.of("c6"), Pawn.createBlack());
        replacePieceForTest(Position.of("c5"), Pawn.createBlack());
        replacePieceForTest(Position.of("c4"), Pawn.createBlack());

        replacePieceForTest(Position.of("g6"), Pawn.createBlack());
        replacePieceForTest(Position.of("g5"), Pawn.createBlack());
        replacePieceForTest(Position.of("g4"), Pawn.createBlack());

        replacePieceForTest(Position.of("e5"), Queen.createWhite());

        Board board = new Board(this.ranks);

        MoveStrategy moveStrategy = Queen.createWhite().moveStrategy();
        assertThat(moveStrategy.moveStrategy(board, Position.of("e5"))).containsExactlyInAnyOrder(
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
        replacePieceForTest(Position.of("d7"), Pawn.createWhite());
        replacePieceForTest(Position.of("e7"), Pawn.createWhite());
        replacePieceForTest(Position.of("f7"), Pawn.createWhite());

        replacePieceForTest(Position.of("d3"), Pawn.createWhite());
        replacePieceForTest(Position.of("e3"), Pawn.createWhite());
        replacePieceForTest(Position.of("f3"), Pawn.createWhite());

        replacePieceForTest(Position.of("d6"), Pawn.createWhite());
        replacePieceForTest(Position.of("d5"), Pawn.createWhite());
        replacePieceForTest(Position.of("d4"), Pawn.createWhite());

        replacePieceForTest(Position.of("f6"), Pawn.createWhite());
        replacePieceForTest(Position.of("f5"), Pawn.createWhite());
        replacePieceForTest(Position.of("f4"), Pawn.createWhite());

        replacePieceForTest(Position.of("e5"), King.createWhite());

        Board board = new Board(this.ranks);

        MoveStrategy moveStrategy = King.createWhite().moveStrategy();
        assertThat(moveStrategy.moveStrategy(board, Position.of("e5"))).containsExactlyInAnyOrder(
            Position.of("e6"), Position.of("e4"));
    }

    @Test
    @DisplayName("주변이 적일 때 킹 이동 가능 경로 확인")
    void kingMovableSurroundedByEnemies() {
        replacePieceForTest(Position.of("d7"), Pawn.createBlack());
        replacePieceForTest(Position.of("e7"), Pawn.createBlack());
        replacePieceForTest(Position.of("f7"), Pawn.createBlack());

        replacePieceForTest(Position.of("d3"), Pawn.createBlack());
        replacePieceForTest(Position.of("e3"), Pawn.createBlack());
        replacePieceForTest(Position.of("f3"), Pawn.createBlack());

        replacePieceForTest(Position.of("d6"), Pawn.createBlack());
        replacePieceForTest(Position.of("d5"), Pawn.createBlack());
        replacePieceForTest(Position.of("d4"), Pawn.createBlack());

        replacePieceForTest(Position.of("f6"), Pawn.createBlack());
        replacePieceForTest(Position.of("f5"), Pawn.createBlack());
        replacePieceForTest(Position.of("f4"), Pawn.createBlack());

        replacePieceForTest(Position.of("e5"), King.createWhite());

        Board board = new Board(this.ranks);

        MoveStrategy moveStrategy = King.createWhite().moveStrategy();
        assertThat(moveStrategy.moveStrategy(board, Position.of("e5"))).containsExactlyInAnyOrder(
            Position.of("e6"), Position.of("e4"),
            Position.of("d5"), Position.of("f5"),
            Position.of("d6"), Position.of("f6"),
            Position.of("d4"), Position.of("f4"));
    }

    private void replacePieceForTest(final Position position, final Piece piece) {
        this.ranks.stream()
            .filter(map -> map.hasPosition(position))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 좌표입니다."))
            .replacePiece(position, piece);
    }
}
