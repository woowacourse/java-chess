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

class RookTest {

    @Test
    @DisplayName("캐싱되어 항상 동일한 객체를 반환한다.")
    void from() {
        // given
        Rook blackRook = Rook.from(Side.BLACK);
        Rook otherBlackRook = Rook.from(Side.BLACK);

        // when
        boolean result = blackRook.equals(otherBlackRook);

        // then
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @MethodSource("provideTargetPositionAndResult")
    @DisplayName("Rook은 상하좌우로 원하는 만큼 움직일 수 있다.")
    void findPath(Position target, List<Position> expected) {
        // given
        Position source = Position.of(File.C, Rank.TWO);
        Rook rook = Rook.from(Side.WHITE);

        // when
        Path path = rook.findPath(source, target, Blank.INSTANCE);

        // then
        assertThat(path.getPositions()).isEqualTo(expected);
    }

    private static Stream<Arguments> provideTargetPositionAndResult() {
        return Stream.of(
                Arguments.of(
                        Position.of(File.C, Rank.THREE),
                        List.of(
                                Position.of(File.C, Rank.THREE)
                        )
                ),
                Arguments.of(
                        Position.of(File.C, Rank.ONE),
                        List.of(
                                Position.of(File.C, Rank.ONE)
                        )
                ),
                Arguments.of(
                        Position.of(File.B, Rank.TWO),
                        List.of(
                                Position.of(File.B, Rank.TWO)
                        )
                ),
                Arguments.of(
                        Position.of(File.D, Rank.TWO),
                        List.of(
                                Position.of(File.D, Rank.TWO)
                        )
                ),
                Arguments.of(
                        Position.of(File.A, Rank.TWO),
                        List.of(
                                Position.of(File.B, Rank.TWO),
                                Position.of(File.A, Rank.TWO)
                        )
                ),
                Arguments.of(
                        Position.of(File.C, Rank.EIGHT),
                        List.of(
                                Position.of(File.C, Rank.THREE),
                                Position.of(File.C, Rank.FOUR),
                                Position.of(File.C, Rank.FIVE),
                                Position.of(File.C, Rank.SIX),
                                Position.of(File.C, Rank.SEVEN),
                                Position.of(File.C, Rank.EIGHT)
                        )
                )
        );
    }

    @Test
    @DisplayName("Rook 움직임으로 타겟 위치에 도달할 수 없다면 빈 리스트를 반환한다.")
    void findPathWhenCanNotReachTargetPiece() {
        // given
        Position source = Position.of(File.C, Rank.TWO);
        Position target = Position.of(File.H, Rank.THREE);
        Rook rook = Rook.from(Side.BLACK);

        // when
        Path path = rook.findPath(source, target, Blank.INSTANCE);

        // then
        assertThat(path.isEmpty()).isTrue();
    }
}
