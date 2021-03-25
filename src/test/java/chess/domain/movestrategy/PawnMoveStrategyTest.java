package chess.domain.movestrategy;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.InitPieces;
import chess.domain.board.Rank;
import chess.domain.board.position.Position;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PawnMoveStrategyTest {

    private List<Rank> ranks;

    @BeforeEach
    void setUp() {
        this.ranks = InitPieces.initRanks();
    }

    @Test
    @DisplayName("화이트 폰 초기 이동 가능 경로 확인")
    void whitePawnStartMovable() {
        replacePieceForTest(Position.of("c3"), Pawn.createBlack());
        replacePieceForTest(Position.of("d2"), Pawn.createWhite());
        Board board = new Board(this.ranks);
        MoveStrategy moveStrategy = Pawn.createWhite().moveStrategy();
        assertThat(moveStrategy.moveStrategy(board, Position.of("d2"))).containsExactlyInAnyOrder(
            Position.of("d3"), Position.of("d4"), Position.of("c3"));
    }

    @Test
    @DisplayName("화이트 폰 이동 가능 경로 확인")
    void whitePawnMovable() {
        replacePieceForTest(Position.of("e4"), Pawn.createBlack());
        replacePieceForTest(Position.of("d3"), Pawn.createWhite());
        Board board = new Board(this.ranks);
        MoveStrategy moveStrategy = Pawn.createWhite().moveStrategy();
        assertThat(moveStrategy.moveStrategy(board, Position.of("d3"))).containsExactlyInAnyOrder(
            Position.of("d4"), Position.of("e4"));
    }

    @Test
    @DisplayName("블랙 폰 초기 이동 가능 경로 확인")
    void blackPawnStartMovable() {
        replacePieceForTest(Position.of("c6"), Pawn.createWhite());
        replacePieceForTest(Position.of("d7"), Pawn.createBlack());
        Board board = new Board(this.ranks);
        MoveStrategy moveStrategy = Pawn.createBlack().moveStrategy();
        assertThat(moveStrategy.moveStrategy(board, Position.of("d7"))).containsExactlyInAnyOrder(
            Position.of("d6"), Position.of("d5"),
            Position.of("c6"));
    }

    @Test
    @DisplayName("블랙 폰 이동 가능 경로 확인")
    void blackPawnMovable() {
        replacePieceForTest(Position.of("e5"), Pawn.createWhite());
        replacePieceForTest(Position.of("d6"), Pawn.createBlack());
        Board board = new Board(this.ranks);
        MoveStrategy moveStrategy = Pawn.createBlack().moveStrategy();
        assertThat(moveStrategy.moveStrategy(board, Position.of("d6"))).containsExactlyInAnyOrder(
            Position.of("d5"), Position.of("e5"));
    }

    @Test
    @DisplayName("앞이 막혔을 때 화이트 폰 초기 이동 가능 경로 확인")
    void whitePawnStartMovableWhenBlocked() {
        replacePieceForTest(Position.of("d3"), Pawn.createBlack());
        replacePieceForTest(Position.of("d2"), Pawn.createWhite());
        Board board = new Board(this.ranks);
        MoveStrategy moveStrategy = Pawn.createWhite().moveStrategy();
        assertThat(moveStrategy.moveStrategy(board, Position.of("d2"))).containsExactlyInAnyOrder();
    }

    @Test
    @DisplayName("앞이 막혔을 때 블랙 폰 초기 이동 가능 경로 확인")
    void blackPawnStartMovableWhenBlocked() {
        replacePieceForTest(Position.of("d6"), Pawn.createBlack());
        replacePieceForTest(Position.of("d7"), Pawn.createBlack());
        Board board = new Board(this.ranks);
        MoveStrategy moveStrategy = Pawn.createBlack().moveStrategy();
        assertThat(moveStrategy.moveStrategy(board, Position.of("d7"))).containsExactlyInAnyOrder();
    }

    private void replacePieceForTest(final Position position, final Piece piece) {
        this.ranks.stream()
            .filter(map -> map.hasPosition(position))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 좌표입니다."))
            .replacePiece(position, piece);
    }
}
