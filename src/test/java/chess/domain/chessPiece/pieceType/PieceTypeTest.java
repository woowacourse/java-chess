package chess.domain.chessPiece.pieceType;

import chess.domain.MovableStrategy.KingMovableStrategy;
import chess.domain.MovableStrategy.MovableStrategy;
import chess.domain.chessPiece.PieceColor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PieceTypeTest {
    @Test
    void PieceType_PieceColorAndMovableStrategy_GenerateInstance() {
        PieceType pieceType = new King(PieceColor.BLACK, new KingMovableStrategy());

        assertThat(pieceType).isInstanceOf(PieceType.class);
    }

    @ParameterizedTest
    @NullSource
    void PieceType_NullPieceColor_ExceptionThrown(PieceColor pieceColor) {
        assertThatThrownBy(() -> new King(pieceColor, new KingMovableStrategy()))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("피스 색상이 null입니다.");
    }

    @ParameterizedTest
    @NullSource
    void PieceType_NullMovableStrategy_ExceptionThrown(MovableStrategy movableStrategy) {
        assertThatThrownBy(() -> new King(PieceColor.BLACK, movableStrategy))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("피스 전략이 null입니다.");
    }
}