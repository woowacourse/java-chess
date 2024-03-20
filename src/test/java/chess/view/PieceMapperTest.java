package chess.view;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.PieceColor;
import chess.domain.PieceName;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceMapperTest {

    @Test
    @DisplayName("흑색 기물일 때 대문자로 변환한다.")
    void mapToUpperCaseWhenBlackPiece() {
        Character actual = PieceMapper.map(PieceName.KNIGHT, PieceColor.BLACK);
        assertThat(actual).isEqualTo('N');
    }
}
