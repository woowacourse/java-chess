package chess.view;

import chess.domain.piece.PieceType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

class PieceTypeViewTest {

    @DisplayName("모든 PieceType에 대응되는 PieceTypeView를 이름으로 찾을 수 있다.")
    @Test
    void mappingPieceTypeToPieceTypeView() {
        assertThatCode(() -> Arrays.stream(PieceType.values()).forEach(pieceType -> PieceTypeView.findViewName(pieceType.name())))
                .doesNotThrowAnyException();
    }
}
