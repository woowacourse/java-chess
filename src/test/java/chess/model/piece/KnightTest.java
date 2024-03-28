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

class KnightTest {

    @Test
    @DisplayName("캐싱되어 항상 동일한 객체를 반환한다.")
    void from() {
        // given
        Knight blackKnight = Knight.from(Side.BLACK);
        Knight otherBlackKnight = Knight.from(Side.BLACK);

        // when
        boolean result = blackKnight.equals(otherBlackKnight);

        // then
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @MethodSource("provideTargetPositionAndResult")
    @DisplayName("Knight는 L자로 움직인다.")
    void findPath(Position target, List<Position> expected) {
        // given
        Position source = Position.of(File.E, Rank.FOUR);
        Knight knight = Knight.from(Side.WHITE);

        // when
        Path path = knight.findPath(source, target, Blank.INSTANCE);

        // then
        assertThat(path.getPositions()).isEqualTo(expected);
    }

    private static Stream<Arguments> provideTargetPositionAndResult() {
        return Stream.of(
                Arguments.of(
                        Position.of(File.C, Rank.THREE),
                        List.of(Position.of(File.C, Rank.THREE))
                ),
                Arguments.of(
                        Position.of(File.C, Rank.FIVE),
                        List.of(Position.of(File.C, Rank.FIVE))
                ),
                Arguments.of(
                        Position.of(File.D, Rank.TWO),
                        List.of(Position.of(File.D, Rank.TWO))
                ),
                Arguments.of(
                        Position.of(File.D, Rank.SIX),
                        List.of(Position.of(File.D, Rank.SIX))
                ),
                Arguments.of(
                        Position.of(File.F, Rank.TWO),
                        List.of(Position.of(File.F, Rank.TWO))
                ),
                Arguments.of(
                        Position.of(File.F, Rank.SIX),
                        List.of(Position.of(File.F, Rank.SIX))
                ),
                Arguments.of(
                        Position.of(File.G, Rank.THREE),
                        List.of(Position.of(File.G, Rank.THREE))
                ),
                Arguments.of(
                        Position.of(File.G, Rank.FIVE),
                        List.of(Position.of(File.G, Rank.FIVE))
                )
        );
    }

    @Test
    @DisplayName("Knight 움직임으로 타겟 위치에 도달할 수 없다면 빈 리스트를 반환한다.")
    void findPathWhenCanNotReachTargetPiece() {
        // given
        Position source = Position.of(File.C, Rank.TWO);
        Position target = Position.of(File.D, Rank.TWO);
        Knight knight = Knight.from(Side.BLACK);

        // when
        Path path = knight.findPath(source, target, Blank.INSTANCE);

        // then
        assertThat(path.isEmpty()).isTrue();
    }
}
