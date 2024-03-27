package view.translator;

import static org.assertj.core.api.Assertions.assertThat;

import domain.piece.Blank;
import domain.piece.pawn.BlackPawn;
import domain.piece.pawn.Pawn;
import domain.piece.pawn.WhitePawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTranslatorTest {

    @DisplayName("해당하는 말이 검정 말일 경우 대문자 이름을 가져온다.")
    @Test
    void getBlackPieceName() {
        Pawn pawn = new BlackPawn();

        assertThat(PieceTranslator.getName(pawn)).isEqualTo("P");
    }

    @DisplayName("해당하는 말이 흰 말일 경우 소문자 이름을 가져온다.")
    @Test
    void getWhitePieceName() {
        Pawn pawn = new WhitePawn();

        assertThat(PieceTranslator.getName(pawn)).isEqualTo("p");
    }

    @DisplayName("해당하는 말이 없을 경우 빈 칸을 뜻하는 이름을 가져온다.")
    @Test
    void getBlankPieceName() {

        assertThat(PieceTranslator.getName(new Blank())).isEqualTo(".");
    }
}
