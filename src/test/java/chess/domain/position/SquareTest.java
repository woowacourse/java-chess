package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.*;

@DisplayName("위치")
public class SquareTest {

    @DisplayName("위치를 생성한다.")
    @Test
    void createPosition() {
        // when & then
        assertThatCode(() -> Square.of(File.a, Rank.THREE)).doesNotThrowAnyException();
    }

    @DisplayName("위치는 인덱스만큼 수직 이동한 위치를 반환한다.")
    @Test
    void moveVerticalByIndex() {
        // given
        Square square = Square.of(File.a, Rank.TWO);
        int index = 3;

        // when
        Square actual = square.moveVertical(index);

        // then
        assertThat(actual).isEqualTo(Square.of(File.a, Rank.FIVE));
    }

    @DisplayName("폰의 위치가 첫 위치인지 확인한다.")
    @ParameterizedTest
    @EnumSource(mode = EnumSource.Mode.INCLUDE, names = {"TWO", "SEVEN"})
    void checkPawnStartSquare(Rank rank) {
        // given
        Square square = Square.of(File.a, rank);

        // when
        boolean actual = square.isPawnStartSquare();

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("위치는 인덱스만큼 수평 이동한 위치를 반환한다.")
    @Test
    void moveHorizontalByIndex() {
        // given
        Square square = Square.of(File.b, Rank.FOUR);
        int index = 2;

        // when
        Square actual = square.moveHorizontal(index);

        // then
        assertThat(actual).isEqualTo(Square.of(File.d, Rank.FOUR));
    }

    @DisplayName("위치는 인덱스만큼 대각 이동한 위치를 반환한다.")
    @Test
    void moveDiagonalByIndex() {
        // given
        Square square = Square.of(File.b, Rank.THREE);
        int verticalIndex = 1;
        int horizontalIndex = 2;

        // when
        Square actual = square.moveDiagonal(horizontalIndex, verticalIndex);

        // then
        assertThat(actual).isEqualTo(Square.of(File.d, Rank.FOUR));
    }
}
