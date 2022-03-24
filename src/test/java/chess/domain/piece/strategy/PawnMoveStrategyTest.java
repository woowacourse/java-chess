package chess.domain.piece.strategy;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import chess.domain.piece.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PawnMoveStrategyTest {

    @ParameterizedTest
    @CsvSource(value = {
            "A,TWO,A,THREE",
            "A,TWO,A,FOUR",
            "A,TWO,B,THREE",
            "B,TWO,A,THREE",
            "B,THREE,B,FOUR",
            "A,THREE,B,FOUR",
            "B,THREE,A,FOUR",
    })
    @DisplayName("폰이 갈 수 있는 위치 중 하나여야 한다.")
    void movePossibilityOfTrue(File fileA, Rank rankA, File fileB, Rank rankB) {
        assertDoesNotThrow(() -> new PawnMoveStrategy()
                .canMove(Color.WHITE,
                        new Position(fileA, rankA),
                        new Position(fileB, rankB)
                )
        );
    }
}
