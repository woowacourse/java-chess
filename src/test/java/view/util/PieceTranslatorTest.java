package view.util;

import static org.assertj.core.api.Assertions.assertThat;

import domain.piece.Color;
import domain.piece.King;
import domain.piece.Knight;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTranslatorTest {

    @DisplayName("해당하는 말이 검정 말일 경우 대문자 이름을 가져온다.")
    @Test
    void getBlackPieceName() {
        King king = new King(Color.BLACK);

        assertThat(PieceTranslator.getName(king)).isEqualTo("K");
    }

    @DisplayName("해당하는 말이 흰 말일 경우 소문자 이름을 가져온다.")
    @Test
    void getWhitePieceName() {
        Knight knight = new Knight(Color.WHITE);

        assertThat(PieceTranslator.getName(knight)).isEqualTo("n");
    }

    @DisplayName("해당하는 말이 없을 경우 빈 칸을 뜻하는 이름을 가져온다.")
    @Test
    void getBlankPieceName() {
        assertThat(PieceTranslator.getName(null)).isEqualTo(".");
    }
}
