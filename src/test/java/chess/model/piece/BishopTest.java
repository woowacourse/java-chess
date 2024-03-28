package chess.model.piece;

import chess.model.position.Position;
import chess.model.position.File;
import chess.model.position.Path;
import chess.model.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {

    @Test
    @DisplayName("캐싱되어 항상 동일한 객체를 반환한다.")
    void from() {
        // given
        Bishop blackBishop = Bishop.from(Side.BLACK);
        Bishop otherBlackBishop = Bishop.from(Side.BLACK);

        // when
        boolean result = blackBishop.equals(otherBlackBishop);

        // then
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @MethodSource("provideTargetPositionAndResult")
    @DisplayName("Bishop은 대각선으로 원하는 만큼 움직일 수 있다.")
    void findPath(Position target, List<Position> expected) {
        // given
        Position source = Position.of(File.C, Rank.TWO);
        Bishop bishop = Bishop.from(Side.WHITE);

        // when
        Path path = bishop.findPath(source, target, Blank.INSTANCE);

        // then
        assertThat(path.getPositions()).isEqualTo(expected);
    }

    private static Stream<Arguments> provideTargetPositionAndResult() {
        return Stream.of(
                Arguments.of(
                        Position.of(File.D, Rank.THREE),
                        List.of(
                                Position.of(File.D, Rank.THREE)
                        )
                ),
                Arguments.of(
                        Position.of(File.D, Rank.ONE),
                        List.of(
                                Position.of(File.D, Rank.ONE)
                        )
                ),
                Arguments.of(
                        Position.of(File.B, Rank.THREE),
                        List.of(
                                Position.of(File.B, Rank.THREE)
                        )
                ),
                Arguments.of(
                        Position.of(File.B, Rank.ONE),
                        List.of(
                                Position.of(File.B, Rank.ONE)
                        )
                ),
                Arguments.of(
                        Position.of(File.A, Rank.FOUR),
                        List.of(
                                Position.of(File.B, Rank.THREE),
                                Position.of(File.A, Rank.FOUR)
                        )
                ),
                Arguments.of(
                        Position.of(File.F, Rank.FIVE),
                        List.of(
                                Position.of(File.D, Rank.THREE),
                                Position.of(File.E, Rank.FOUR),
                                Position.of(File.F, Rank.FIVE)
                        )
                )
        );
    }

    @Test
    @DisplayName("Bishop은 직교로 움직일 수 없다.")
    void findPathWhenCanNotReachTargetPiece() {
        // given
        Position source = Position.of(File.C, Rank.TWO);
        Position target = Position.of(File.D, Rank.TWO);
        Bishop bishop = Bishop.from(Side.BLACK);

        // when
        Path path = bishop.findPath(source, target, Blank.INSTANCE);

        // then
        assertThat(path.isEmpty()).isTrue();
    }
}
