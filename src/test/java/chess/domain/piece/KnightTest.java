package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Position;
import chess.domain.Team;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class KnightTest {

    private Piece blackTarget;
    private Piece emptyTarget;

    @BeforeEach
    void setUp() {
        blackTarget = new Pawn(Team.BLACK);
        emptyTarget = new Empty(Team.EMPTY);
    }

    @ParameterizedTest
    @CsvSource(value = {"1:2", "2:1", "4:5"}, delimiter = ':')
    @DisplayName("나이트는 L자로 이동할 수 있다.")
    void isMovable(int rank, int file) {
        Piece knight = new Knight(Team.WHITE);
        Position source = new Position(3, 3);
        Position target = new Position(rank, file);
        assertThat(knight.isMovable(source, target, emptyTarget)).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"2:2", "3:5", "1:2"}, delimiter = ':')
    @DisplayName("나이트는 L자를 제외한 곳으로 이동할 수 없다.")
    void isUnmovable(int rank, int file) {
        Piece knight = new Knight(Team.WHITE);
        Position source = new Position(1, 1);
        Position target = new Position(rank, file);
        assertThat(knight.isMovable(source, target, emptyTarget)).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"2:2", "3:5", "1:2"}, delimiter = ':')
    @DisplayName("나이트는 타겟 지점에 상대팀이 있으면 L자로 이동할 수 없다.")
    void isUnmovableToOppositeTeam(int rank, int file) {
        Piece knight = new Knight(Team.WHITE);
        Position source = new Position(1, 1);
        Position target = new Position(rank, file);
        assertThat(knight.isMovable(source, target, blackTarget)).isFalse();
    }

    @ParameterizedTest
    @MethodSource("findPathProvider")
    @DisplayName("타켓 위치로 갈 수 있는 모든 경로를 리스트에 담아서 반환한다.")
    void findPath(Position source, Position target, List<Position> expectedPath) {
        Piece knight = new Knight(Team.WHITE);
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
