package piece;

import chess.domain.piece.PieceColor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class PieceColorTest {

    @ParameterizedTest
    @DisplayName("하얀색 체스말의 이름을 소문자로 반환하는지 확인")
    @CsvSource(value = {"b,b", "k,k", "n,n", "p,p", "q,q", "r,r"})
    void ifWhitePieceThenReturnLowerCaseName(String input, String expected) {
        Assertions.assertThat(PieceColor.WHITE.getPieceName(input)).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("검은색 체스말의 이름을 대문자로 반환하는지 확인")
    @CsvSource(value = {"b,B", "k,K", "n,N", "p,P", "q,Q", "r,R"})
    void ifBlackPieceThenReturnUpperCaseName(String input, String expected) {
        Assertions.assertThat(PieceColor.BLACK.getPieceName(input)).isEqualTo(expected);
    }

    @Test
    @DisplayName("색이 없는 체스말의 이름을 그대로 반환하는지 확인")
    void ifNoneColorPieceThenReturnName() {
        Assertions.assertThat(PieceColor.NONE.getPieceName(".")).isEqualTo(".");
    }

    @ParameterizedTest
    @DisplayName("필드 변수로 갖고 있는 문자를 입력받으면 enum 클래스(PieceColor)를 정상 반환해야 함")
    @ValueSource(strings = {"White", "Black", "None"})
    void inputVarStringThenReturnPieceColorClass(String input) {
        Assertions.assertThat(PieceColor.of(input)).isInstanceOf(PieceColor.class);
    }
}
