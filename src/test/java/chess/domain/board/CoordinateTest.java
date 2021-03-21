package chess.domain.board;

import chess.domain.piece.Direction;
import chess.domain.piece.TeamType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class CoordinateTest {

    private static Stream<Arguments> getCoordinateAndTeam() {
        return Stream.of(Arguments.of("d7", TeamType.BLACK, true),
                Arguments.of("d8", TeamType.BLACK, false),
                Arguments.of("d2", TeamType.WHITE, true),
                Arguments.of("d1", TeamType.WHITE, false));
    }

    @DisplayName("Coordinate는 File(행)과 Rank(열)를 필드로 가지는 좌표 VO다")
    @Test
    void compareCoordinateEquality() {
        Coordinate coordinate = new Coordinate(File.A, Rank.EIGHT);
        Coordinate targetCoordinate = new Coordinate(File.A, Rank.EIGHT);

        assertThat(coordinate).isEqualTo(targetCoordinate);
    }

    @DisplayName("Coordinate로 입력받는 문자열은 ${file}${rank} 두 자리의 문자열이어야 한다.")
    @Test
    void cannotMakeCoordinate() {
        assertThatCode(() -> Coordinate.from("ba31"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("위치에 해당하는 유효한 좌표 값은 두 자리의 문자열입니다.");
    }

    @DisplayName("현재 위치의 좌표에서 타겟 좌표까지의 방향을 찾아 반환한다.")
    @Test
    void evaluateDirection() {
        Coordinate current = Coordinate.from("c4");
        Coordinate target = Coordinate.from("a5");

        Direction direction = current.evaluateDirection(target);

        assertThat(direction).isEqualTo(Direction.LEFT_LEFT_UP);
    }

    @DisplayName("현재 위치의 좌표에서 주어진 방향을 토대로 움직인 뒤 새로운 좌표를 반환한다.")
    @Test
    void move() {
        Coordinate coordinate = Coordinate.from("c4");
        Direction direction = Direction.RIGHT_DOWN_DOWN;

        Coordinate nextCoordinate = coordinate.move(direction);

        assertThat(nextCoordinate).isEqualTo(Coordinate.from("d2"));
    }

    @DisplayName("file은 같고 rank(열)이 2 차이나면 true를 반환한다.")
    @Test
    void isTwoRankForwardFrom_True() {
        Coordinate current = Coordinate.from("c2");
        Coordinate target = Coordinate.from("c4");

        boolean isTwoRankForward = current.isTwoRankForwardFrom(target);

        assertThat(isTwoRankForward).isTrue();
    }

    @DisplayName("rank(열)이 2 차이나지 않으면 false를 반환한다.")
    @Test
    void isTwoRankForwardFrom_False() {
        Coordinate current = Coordinate.from("c2");
        Coordinate target = Coordinate.from("c5");

        boolean isTwoRankForward = current.isTwoRankForwardFrom(target);

        assertThat(isTwoRankForward).isFalse();
    }

    @DisplayName("현재 좌표가 각 팀의 Pawn 기물의 초기 위치에 해당하는 Rakn(열)인지 확인한다.")
    @ParameterizedTest
    @MethodSource("getCoordinateAndTeam")
    void isDefaultPawnRank(String coordinateInput, TeamType teamType, boolean expected) {
        Coordinate coordinate = Coordinate.from(coordinateInput);

        boolean isDefaultPawnRank = coordinate.isDefaultPawnRank(teamType);

        assertThat(isDefaultPawnRank).isEqualTo(expected);
    }
}
