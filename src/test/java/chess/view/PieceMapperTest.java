package chess.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("기물 출력명")
class PieceMapperTest {

    @Test
    @DisplayName("검은색 기물일 때 대문자로 변환한다.")
    void mapToUpperCaseWhenBlackPieceTest() {
        Character actual = PieceMapper.map("KNIGHT", "BLACK");
        assertThat(actual).isEqualTo('N');
    }

    @Test
    @DisplayName("흰색 기물일 때 소문자로 변환한다.")
    void mapToUpperCaseWhenWhitePieceTest() {
        Character actual = PieceMapper.map("KING", "WHITE");
        assertThat(actual).isEqualTo('k');
    }
}
