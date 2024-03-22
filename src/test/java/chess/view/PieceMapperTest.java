package chess.view;

import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("기물 출력명")
class PieceMapperTest {

    @Test
    @DisplayName("흑색 기물일 때 대문자로 변환한다.")
    void mapToUpperCaseWhenBlackPiece() {
        Character actual = PieceMapper.map(PieceType.KNIGHT, PieceColor.BLACK);
        assertThat(actual).isEqualTo('N');
    }
}
