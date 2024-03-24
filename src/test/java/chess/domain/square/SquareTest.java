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
        Square square = Square.of(File.b, Rank.THREE);
        assertThat(square.file()).isEqualTo(File.b);
        assertThat(square.rank()).isEqualTo(Rank.THREE);
    }

    @DisplayName("출발지에서 목적지까지 직선으로 이동하는 경우의 경로를 생성한다.")
    @Test
    void createPathWhenStraight() {
        Square source = Square.of(File.e, Rank.FOUR);
        Square target = Square.of(File.a, Rank.FOUR);

        List<Square> actual = source.findPath(target);

        assertThat(actual).containsExactlyInAnyOrder(
                Square.of(File.b, Rank.FOUR),
                Square.of(File.c, Rank.FOUR),
                Square.of(File.d, Rank.FOUR));
    }

    @DisplayName("출발지에서 목적지까지 대각선으로 이동하는 경우의 경로를 생성한다.")
    @Test
    void createPathWhenDiagonal() {
        Square source = Square.of(File.e, Rank.FOUR);
        Square target = Square.of(File.a, Rank.EIGHT);

        List<Square> actual = source.findPath(target);

        assertThat(actual).containsExactlyInAnyOrder(
                Square.of(File.d, Rank.FIVE),
                Square.of(File.c, Rank.SIX),
                Square.of(File.b, Rank.SEVEN));
    }

    @DisplayName("출발지에서 목적지까지 직선이나 대각선으로 이동하지 않는 경우 경로가 생성되지 않는다.")
    @Test
    void notCreatePathWhenNotStraightAndDiagonal() {
        Square source = Square.of(File.e, Rank.TWO);
        Square target = Square.of(File.d, Rank.FIVE);

        List<Square> actual = source.findPath(target);
        assertThat(actual).isEmpty();
    }
}
