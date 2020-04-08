package chess.model.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class BoardSquareTest {

    @Test
    @DisplayName("칸 캐싱했는지 확인")
    void checkSquareCache() {
        Assertions.assertThat(BoardSquare.of("a1")).isEqualTo(BoardSquare.of("a1"));
    }

    @DisplayName("Null이 of에 들어갔을 때 예외 발생")
    @Test
    void validNotNull() {
        assertThatThrownBy(() -> BoardSquare.of(null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("Null");
        assertThatThrownBy(() -> BoardSquare.of(null, null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("Null");
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = {"a9", "f0", "jkl", "j3"})
    @DisplayName("잘못된 값이 of에 들어갔을 때 예외 발생")
    void validLocation(String location) {
        assertThatThrownBy(() -> BoardSquare.of(location))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("더한 값이 캐싱된 값이면 캐싱 값 리턴, 아니면 본인 리턴")
    @Test
    void canAddTest() {
        BoardSquare boardSquare = BoardSquare.of("a2");
        assertThat(boardSquare.getIncreased(1, 1)).isEqualTo(BoardSquare.of("b3"));
    }

    @DisplayName("같은 File의 Square인지 확인")
    @Test
    void isSameFile() {
        BoardSquare boardSquareA2 = BoardSquare.of("a2");
        BoardSquare boardSquareA3 = BoardSquare.of("a3");
        BoardSquare boardSquareB2 = BoardSquare.of("b2");
        assertThat(boardSquareA2.isSameFile(boardSquareA3)).isTrue();
        assertThat(boardSquareA2.isSameFile(boardSquareB2)).isFalse();
    }

    @DisplayName("File의 차이를 가져옴")
    @Test
    void getFileSubtract() {
        BoardSquare boardSquareA3 = BoardSquare.of("a3");
        BoardSquare boardSquareB2 = BoardSquare.of("b2");
        BoardSquare boardSquareC1 = BoardSquare.of("c1");
        assertThat(boardSquareB2.getFileCompare(boardSquareA3)).isEqualTo(1);
        assertThat(boardSquareB2.getFileCompare(boardSquareB2)).isEqualTo(0);
        assertThat(boardSquareB2.getFileCompare(boardSquareC1)).isEqualTo(-1);
    }

    @DisplayName("Rank의 차이를 가져옴")
    @Test
    void getRankSubtract() {
        BoardSquare boardSquareA3 = BoardSquare.of("a3");
        BoardSquare boardSquareB2 = BoardSquare.of("b2");
        BoardSquare boardSquareC1 = BoardSquare.of("c1");
        assertThat(boardSquareB2.getRankCompare(boardSquareA3)).isEqualTo(-1);
        assertThat(boardSquareB2.getRankCompare(boardSquareB2)).isEqualTo(0);
        assertThat(boardSquareB2.getRankCompare(boardSquareC1)).isEqualTo(1);
    }

    @DisplayName("한 칸 점프한 칸인지 비교")
    @Test
    void isJumpOneRank() {
        BoardSquare boardSquareA3 = BoardSquare.of("a3");
        BoardSquare boardSquareA1 = BoardSquare.of("a1");
        BoardSquare boardSquareB3 = BoardSquare.of("b3");
        assertThat(boardSquareA3.isJumpOneRank(boardSquareA3)).isFalse();
        assertThat(boardSquareA3.isJumpOneRank(boardSquareA1)).isTrue();
        assertThat(boardSquareA3.isJumpOneRank(boardSquareB3)).isFalse();
    }

    @DisplayName("한 칸 점프한 칸 가져오기")
    @Test
    void getBetweenWhenJumpOneRank() {
        BoardSquare boardSquareA3 = BoardSquare.of("a3");
        BoardSquare boardSquareA1 = BoardSquare.of("a1");
        assertThatThrownBy(() -> boardSquareA3.getBetweenWhenJumpOneRank(boardSquareA3))
            .isInstanceOf(IllegalArgumentException.class);
        assertThat(boardSquareA3.getBetweenWhenJumpOneRank(boardSquareA1))
            .isEqualTo(BoardSquare.of("a2"));
    }
}
