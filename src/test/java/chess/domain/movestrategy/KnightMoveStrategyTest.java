package chess.domain.movestrategy;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.position.InitPosition;
import chess.domain.board.position.Position;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KnightMoveStrategyTest {

    private Map<Position, Piece> squares;

    @BeforeEach
    void setUp() {
        this.squares = InitPosition.initSquares();
    }

    @Test
    @DisplayName("나이트 이동 가능 경로 확인")
    void movable() {
        this.squares.replace(Position.of("h5"), Pawn.createWhite());
        this.squares.replace(Position.of("g8"), Pawn.createWhite());
        this.squares.replace(Position.of("d5"), Pawn.createWhite());
        this.squares.replace(Position.of("d7"), Pawn.createWhite());
        this.squares.replace(Position.of("f6"), Knight.createWhite());

        Board board = new Board(this.squares);
        MoveStrategy moveStrategy = Knight.createWhite().moveStrategy();

        assertThat(moveStrategy.currentPositionMoveStrategy(board, Position.of("f6")))
            .containsExactlyInAnyOrder(
                Position.of("h7"), Position.of("e8"),
                Position.of("g4"), Position.of("e4"));
    }
}
