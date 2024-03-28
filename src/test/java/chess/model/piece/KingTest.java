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

class KingTest {

    @Test
    @DisplayName("캐싱되어 항상 동일한 객체를 반환한다.")
    void from() {
        // given
        King blackKing = King.from(Side.BLACK);
        King otherBlackKing = King.from(Side.BLACK);

        // when
        boolean result = blackKing.equals(otherBlackKing);

        // then
        assertThat(result).isTrue();

    }

    @ParameterizedTest
    @MethodSource("provideTargetPositionAndResult")
    @DisplayName("King이 상하좌우, 대각선으로 한 칸씩 움직일 수 있다.")
    void findPath(Position target, List<Position> expected) {
        // given
        Position source = Position.of(File.C, Rank.TWO);
        King king = King.from(Side.WHITE);

        // when
        Path path = king.findPath(source, target, Blank.INSTANCE);

        // then
        assertThat(path.getPositions()).isEqualTo(expected);
    }

    private static Stream<Arguments> provideTargetPositionAndResult() {
        return Stream.of(
                Arguments.of(
                        Position.of(File.B, Rank.ONE),
                        List.of(Position.of(File.B, Rank.ONE))
                ),
                Arguments.of(
                        Position.of(File.C, Rank.ONE),
                        List.of(Position.of(File.C, Rank.ONE))
                ),
                Arguments.of(
                        Position.of(File.D, Rank.ONE),
                        List.of(Position.of(File.D, Rank.ONE))
                ),
                Arguments.of(
                        Position.of(File.B, Rank.TWO),
                        List.of(Position.of(File.B, Rank.TWO))
                ),
                Arguments.of(
                        Position.of(File.D, Rank.TWO),
                        List.of(Position.of(File.D, Rank.TWO))
                ),
                Arguments.of(
                        Position.of(File.B, Rank.THREE),
                        List.of(Position.of(File.B, Rank.THREE))
                ),
                Arguments.of(
                        Position.of(File.C, Rank.THREE),
                        List.of(Position.of(File.C, Rank.THREE))
                ),
                Arguments.of(
                        Position.of(File.D, Rank.THREE),
                        List.of(Position.of(File.D, Rank.THREE))
                )
        );
    }

    @Test
    @DisplayName("King 움직임으로 타겟 위치에 도달할 수 없다면 빈 리스트를 반환한다.")
    void findPathWhenCanNotReachTargetPiece() {
        // given
        Position source = Position.of(File.C, Rank.TWO);
        Position target = Position.of(File.H, Rank.THREE);
        King king = King.from(Side.BLACK);

        // when
        Path path = king.findPath(source, target, Blank.INSTANCE);

        // then
        assertThat(path.getPositions()).isEmpty();
    }
}
