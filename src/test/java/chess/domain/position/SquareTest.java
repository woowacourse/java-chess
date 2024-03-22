package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("위치")
public class SquareTest {

    @DisplayName("위치를 생성한다.")
    @Test
    void createPosition() {
        // when & then
        assertThatCode(() -> Square.of(File.A, Rank.THREE)).doesNotThrowAnyException();
    }

    @DisplayName("위치는 인덱스만큼 수직 이동한 위치를 반환한다.")
    @Test
    void moveVerticalByIndex() {
        // given
        Square square = Square.of(File.A, Rank.TWO);
        int index = 3;

        // when
        Square actual = square.moveVertical(index);

        // then
        assertThat(actual).isEqualTo(Square.of(File.A, Rank.FIVE));
    }

    @DisplayName("위치가 주어진 랭크와 동일한 위치에 있는지 확인한다.")
    @Test
    void checkPawnStartSquare() {
        // given
        Square square = Square.of(File.A, Rank.TWO);

        // when
        boolean actual = square.isSameRank(Rank.TWO);

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("위치는 인덱스만큼 수평 이동한 위치를 반환한다.")
    @Test
    void moveHorizontalByIndex() {
        // given
        Square square = Square.of(File.B, Rank.FOUR);
        int index = 2;

        // when
        Square actual = square.moveHorizontal(index);

        // then
        assertThat(actual).isEqualTo(Square.of(File.D, Rank.FOUR));
    }

    @DisplayName("위치는 인덱스만큼 대각 이동한 위치를 반환한다.")
    @Test
    void moveDiagonalByIndex() {
        // given
        Square square = Square.of(File.B, Rank.THREE);
        int verticalIndex = 1;
        int horizontalIndex = 2;

        // when
        Square actual = square.moveDiagonal(horizontalIndex, verticalIndex);

        // then
        assertThat(actual).isEqualTo(Square.of(File.D, Rank.FOUR));
    }
}
