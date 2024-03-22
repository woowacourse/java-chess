package chess.view;

import chess.domain.piece.PieceColor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

class PieceColorViewTest {

    @DisplayName("모든 PieceColor에 대응되는 PieceColorView를 이름으로 찾을 수 있다.")
    @Test
    void mappingPieceColorToPieceColorView() {
        assertThatCode(() -> Arrays.stream(PieceColor.values()).forEach(pieceColor -> PieceColorView.find(pieceColor.name())))
                .doesNotThrowAnyException();
    }
}
