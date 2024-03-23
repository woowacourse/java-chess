package chess.domain.square;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

@DisplayName("좌표")
class SquareTest {

    @DisplayName("스트링 값으로 들어온 좌표를 파일과 랭크로 변환한다.")
    @Test
    void convertUserInputToFileAndRank() {
        Square square = Square.from("b3");
        assertThat(square.file()).isEqualTo(File.b);
        assertThat(square.rank()).isEqualTo(Rank.THREE);
    }

    @DisplayName("출발지에서 목적지까지 직선으로 이동하는 경우의 경로를 생성한다.")
    @Test
    void createPathWhenStraight() {
        Square source = Square.from("e4");
        Square target = Square.from("a4");

        List<Square> actual = source.findPath(target);

        assertThat(actual).containsExactlyInAnyOrder(
                Square.from("b4"),
                Square.from("c4"),
                Square.from("d4"));
    }

    @DisplayName("출발지에서 목적지까지 대각선으로 이동하는 경우의 경로를 생성한다.")
    @Test
    void createPathWhenDiagonal() {
        Square source = Square.from("e4");
        Square target = Square.from("a8");

        List<Square> actual = source.findPath(target);

        assertThat(actual).containsExactlyInAnyOrder(
                Square.from("d5"),
                Square.from("c6"),
                Square.from("b7"));
    }

    @DisplayName("출발지에서 목적지까지 직선이나 대각선으로 이동하지 않는 경우 경로가 생성되지 않는다.")
    @Test
    void notCreatePathWhenNotStraightAndDiagonal() {
        Square source = Square.from("e4");
        Square target = Square.from("d2");

        List<Square> actual = source.findPath(target);
        assertThat(actual).isEmpty();
    }
}
