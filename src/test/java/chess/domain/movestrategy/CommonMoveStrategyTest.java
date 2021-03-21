package chess.domain.movestrategy;

import chess.domain.board.Board;
import chess.domain.board.BoardGenerator;
import chess.domain.board.position.Position;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class CommonMoveStrategyTest {
    private BoardGenerator boardGenerator;

    @BeforeEach
    void setUp() {
        boardGenerator = new BoardGenerator();
    }

    @Test
    @DisplayName("룩 이동 가능 경로 확인")
    void movable() {
        boardGenerator.put(Position.of("h5"), Pawn.createWhite());
        boardGenerator.put(Position.of("g8"), Pawn.createWhite());
        boardGenerator.put(Position.of("d5"), Pawn.createWhite());
        boardGenerator.put(Position.of("d7"), Pawn.createWhite());
        boardGenerator.put(Position.of("f6"), Knight.createWhite());
        Board board = new Board(boardGenerator.create());
        MoveStrategy moveStrategy = Knight.createWhite().moveStrategy();
        assertThat(moveStrategy.movable(board, Position.of("f6"))).containsAll(Arrays.asList(
                Position.of("h7"), Position.of("e8"),
                Position.of("g4"), Position.of("e4")));
    }
}
