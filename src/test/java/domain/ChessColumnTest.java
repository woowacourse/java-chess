package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ChessColumnTest {

    @Test
    @DisplayName("ChessColumn의 차이를 계산한다.")
    void calculateDifference() {
        ChessColumn e = ChessColumn.E;
        ChessColumn d = ChessColumn.D;

        Assertions.assertThat(e.minus(d)).isEqualTo(1);
        Assertions.assertThat(d.minus(e)).isEqualTo(-1);
    }

    @Test
    @DisplayName("숫자로 ChessColumn을 가져온다.")
    void findChessColumnByNumber() {
        ChessColumn chessColumn = ChessColumn.find(1);

        Assertions.assertThat(chessColumn).isEqualTo(ChessColumn.A);
    }

    @Test
    @DisplayName("문자로 ChessColumn을 가져온다.")
    void findChessColumnByCharacter() {
        ChessColumn chessColumn = ChessColumn.find('h');

        Assertions.assertThat(chessColumn).isEqualTo(ChessColumn.H);
    }

    @ParameterizedTest
    @DisplayName("잘못된 숫자로 ChessColumn을 가져올시 예외 발생가 발생한다.")
    @ValueSource(ints = {0, 9})
    void invalidChessColumnByNumber(int column) {
        Assertions.assertThatThrownBy(() -> ChessColumn.find(column))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 위치입니다.");
    }

    @Test
    @DisplayName("잘못된 문자로 ChessColumn을 가져올시 예외 발생가 발생한다.")
    void invalidChessColumnByCharacter() {
        Assertions.assertThatThrownBy(() -> ChessColumn.find('i'))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 위치입니다.");
    }

}
