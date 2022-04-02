package chess.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.movable.Pawn;
import chess.domain.piece.movable.multiple.Bishop;
import chess.domain.piece.movable.multiple.Queen;
import chess.domain.piece.movable.multiple.Rook;
import chess.domain.piece.movable.single.Knight;

class ScoreCalculatorTest {

    private ScoreCalculator scoreCalculator;

    @BeforeEach
    void setUp() {
        scoreCalculator = new ScoreCalculator();
    }

    @DisplayName("기물 점수의 합계를 계산할 수 있어야 한다.")
    @ParameterizedTest
    @MethodSource("provideForCalculateScore")
    void calculateScore(final Map<Position, Piece> pieces, final double expected) {
        final double actual = scoreCalculator.calculateScore(pieces);
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideForCalculateScore() {
        return Stream.of(
                Arguments.of(
                        Map.of(
                                Position.from("a1"), Pawn.getWhitePawn(),
                                Position.from("a2"), Pawn.getWhitePawn(),
                                Position.from("a3"), Pawn.getWhitePawn(),
                                Position.from("a4"), Pawn.getWhitePawn()
                        ), 2
                ),
                Arguments.of(
                        Map.of(
                                Position.from("a1"), Pawn.getWhitePawn(),
                                Position.from("a2"), Queen.getInstance(),
                                Position.from("a3"), Knight.getInstance(),
                                Position.from("a4"), Rook.getInstance(),
                                Position.from("a5"), Bishop.getInstance()
                        ), 20.5
                ),
                Arguments.of(
                        Map.of(
                                Position.from("a1"), Pawn.getWhitePawn(),
                                Position.from("b2"), Queen.getInstance(),
                                Position.from("c3"), Knight.getInstance(),
                                Position.from("d4"), Rook.getInstance(),
                                Position.from("e5"), Bishop.getInstance()
                        ), 20.5
                )
        );
    }

}