package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PieceNamePatternTest {

    @DisplayName("기물의 색이 White이면 소문자 네이밍 패턴을 적용한다.")
    @Test
    void whiteColorNaming() {
        // given
        String pieceName = "p";
        PieceNamePattern whiteNamePattern = PieceNamePattern.WHITE;

        // when
        String whitePieceName = whiteNamePattern.apply(pieceName);

        //then
        assertThat(whitePieceName).isEqualTo("p");
    }

    @DisplayName("기물의 색이 Black이면 소문자 네이밍 패턴을 적용한다.")
    @Test
    void blackColorNaming() {
        // given
        String pieceName = "p";
        PieceNamePattern whiteNamePattern = PieceNamePattern.BLACK;

        // when
        String whitePieceName = whiteNamePattern.apply(pieceName);

        //then
        assertThat(whitePieceName).isEqualTo("P");
    }
}