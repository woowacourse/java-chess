package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.game.Position;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class KnightTest {

    Team team;
    Piece knight;

    @BeforeEach
    void setUp() {
        team = Team.WHITE;
        knight = new Knight(team);
    }

    @Test
    @DisplayName("나이트는 target 지점에 같은 팀이 있으면 이동할 수 없다.")
    void isUnmovableToSameTeamInTarget() {
        Position source = new Position(1, 3);
        Position target = new Position(2, 5);
        assertThatThrownBy(() -> knight.isMovable(source, target, Team.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 타겟 위치에 같은 팀 기물이 있습니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"1:2", "2:1", "4:5"}, delimiter = ':')
    @DisplayName("나이트는 L자로 이동할 수 있다.")
    void isMovable(int rank, int file) {
        Position source = new Position(3, 3);
        Position target = new Position(rank, file);
        assertThat(knight.isMovable(source, target, team.reverse())).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"2:2", "3:5", "1:2"}, delimiter = ':')
    @DisplayName("나이트는 L자를 제외한 곳으로 이동할 수 없다.")
    void isUnmovable(int rank, int file) {
        Position source = new Position(1, 1);
        Position target = new Position(rank, file);
        assertThat(knight.isMovable(source, target, team.reverse())).isFalse();
    }

    @ParameterizedTest
    @MethodSource("findPathProvider")
    @DisplayName("타켓 위치로 갈 수 있는 모든 경로를 리스트에 담아서 반환한다.")
    void findPath(Position source, Position target, List<Position> expectedPath) {
        assertThat(knight.findPath(source, target)).containsAll(expectedPath);
    }

    static Stream<Arguments> findPathProvider() {
        return Stream.of(
                Arguments.of(
                        new Position(4, 4),
                        new Position(6, 3),
                        List.of(
                                new Position(6, 3)
                        )
                ),
                Arguments.of(
                        new Position(2, 1),
                        new Position(3, 3),
                        List.of(
                                new Position(3, 3)
                        )
                ),
                Arguments.of(
                        new Position(2, 3),
                        new Position(1, 1),
                        List.of(
                                new Position(1, 1)
                        )
                )
        );
    }
}
