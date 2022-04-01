package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PieceColorTest {

    @Test
    @DisplayName("검은색 기물이면 대문자를 출력하는지")
    void upperCaseOnBlack() {
        PieceColor color = PieceColor.BLACK;

        assertThat(color.correctSignature("a")).isEqualTo("A");
    }

    @Test
    @DisplayName("흰색 기물이면 소문자를 출력하는지")
    void lowerCaseOnWhite() {
        PieceColor color = PieceColor.WHITE;

        assertThat(color.correctSignature("a")).isEqualTo("a");
    }

    @ParameterizedTest
    @CsvSource(value = {"WHITE:BLACK", "BLACK:WHITE"}, delimiter = ':')
    @DisplayName("상대편 색을 가져오는지")
    void finEnemyColor(PieceColor color, PieceColor enemyColor) {
        assertThat(color.enemyColor()).isEqualTo(enemyColor);
    }

    @ParameterizedTest
    @CsvSource(value = {"WHITE:false", "BLACK:true"}, delimiter = ':')
    @DisplayName("검정색인지")
    void isBlack(PieceColor color, boolean isBlack) {
        assertThat(color.isBlack()).isEqualTo(isBlack);
    }


    @ParameterizedTest
    @CsvSource(value = {"WHITE:true", "BLACK:false"}, delimiter = ':')
    @DisplayName("흰색인지")
    void isWhite(PieceColor color, boolean isBlack) {
        assertThat(color.isWhite()).isEqualTo(isBlack);
    }
}
