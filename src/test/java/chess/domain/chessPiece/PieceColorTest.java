package chess.domain.chessPiece;

import chess.domain.chessPiece.pieceType.King;
import chess.domain.chessPiece.PieceColor;
import chess.domain.chessPiece.pieceType.PieceType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class PieceColorTest {
    @ParameterizedTest
    @MethodSource("providePieceTypeAndExpected")
    void convertName_PieceTypeName_ConvertByPieceColor(PieceColor pieceColor, String expected) {
        PieceType king = King.getInstance();

        assertThat(pieceColor.convertName(king.getName())).isEqualTo(expected);
    }

    private static Stream<Arguments> providePieceTypeAndExpected() {
        return Stream.of(
                Arguments.of(PieceColor.WHITE, "k"),
                Arguments.of(PieceColor.BLACK, "K"));
    }
}

