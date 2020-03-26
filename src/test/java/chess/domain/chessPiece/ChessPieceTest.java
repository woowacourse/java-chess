package chess.domain.chessPiece;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ChessPieceTest {
    @Test
    void of_NameOfPieceType_ReturnInstance() {
        assertThat(ChessPiece.of("P")).isInstanceOf(ChessPiece.class);
    }

    @ParameterizedTest
    @NullSource
    void of_NullPieceType_ReturnInstance(String key) {
        assertThatThrownBy(() -> ChessPiece.of(key))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("체스 피스의 key가 null입니다.");
    }

    @Test
    void toString_PieceType_ReturnPieceTypeName() {
        assertThat(ChessPiece.of("P").toString()).isEqualTo("P");
    }
}
