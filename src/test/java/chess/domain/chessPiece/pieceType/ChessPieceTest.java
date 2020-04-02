package chess.domain.chessPiece.pieceType;

import chess.domain.chessPiece.ChessPiece;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ChessPieceTest {
    @Test
    void PieceType_PieceColorAndMovableStrategy_GenerateInstance() {
        ChessPiece chessPiece = new King(PieceColor.BLACK);

        assertThat(chessPiece).isInstanceOf(ChessPiece.class);
    }

    @ParameterizedTest
    @NullSource
    void PieceType_NullPieceColor_ExceptionThrown(PieceColor pieceColor) {
        assertThatThrownBy(() -> new King(pieceColor))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("피스 색상이 null입니다.");
    }

}