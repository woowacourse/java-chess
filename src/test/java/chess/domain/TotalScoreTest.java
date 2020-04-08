package chess.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;

public class TotalScoreTest {

    private static Stream<Arguments> scoreCalculateSource() {
        return Stream.of(
            Arguments.of(Arrays.asList(new King(new Position("d1"), Team.WHITE),
                new Pawn(new Position("e4"), Team.WHITE),
                new Pawn(new Position("f4"), Team.WHITE)), 2),
            Arguments.of(Arrays.asList(new King(new Position("d1"), Team.WHITE),
                new Pawn(new Position("e4"), Team.WHITE),
                new Pawn(new Position("e6"), Team.WHITE)), 1)
        );
    }

    @ParameterizedTest
    @DisplayName("점수가 제대로 계산되는지 확인")
    @MethodSource("scoreCalculateSource")
    void name(List<Piece> pieces, int expected) {
        TotalScore whiteTotalScore = new TotalScore(pieces);
        assertThat(whiteTotalScore.getTotalScore()).isEqualTo(expected);
    }
}
