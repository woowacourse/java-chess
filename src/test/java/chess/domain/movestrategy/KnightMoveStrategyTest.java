package chess.domain.movestrategy;

import chess.domain.board.Board;
import chess.domain.board.InitPieces;
import chess.domain.board.Rank;
import chess.domain.board.position.Position;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class KnightMoveStrategyTest {
    private List<Rank> ranks;

    @BeforeEach
    void setUp() {
        this.ranks = InitPieces.initRanks();
    }

    @Test
    @DisplayName("나이트 이동 가능 경로 확인")
    void movable() {
        replacePieceForTest(Position.of("h5"), Pawn.createWhite());
        replacePieceForTest(Position.of("g8"), Pawn.createWhite());
        replacePieceForTest(Position.of("d5"), Pawn.createWhite());
        replacePieceForTest(Position.of("d7"), Pawn.createWhite());
        replacePieceForTest(Position.of("f6"), Knight.createWhite());

        Board board = new Board(this.ranks);
        MoveStrategy moveStrategy = Knight.createWhite().moveStrategy();

        assertThat(moveStrategy.moveStrategy(board, Position.of("f6"))).containsExactlyInAnyOrder(
                Position.of("h7"), Position.of("e8"),
                Position.of("g4"), Position.of("e4"));
    }

    private void replacePieceForTest(final Position position, final Piece piece) {
        this.ranks.stream()
            .filter(map -> map.hasPosition(position))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 좌표입니다."))
            .replacePiece(position, piece);
    }
}
