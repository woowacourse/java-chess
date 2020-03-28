package chess.domain.chessPiece.pieceType;

import chess.domain.RuleStrategy.KingRuleStrategy;
import chess.domain.chessPiece.ChessPiece;
import chess.domain.chessPiece.pieceState.InitialState;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ChessPieceTest {
    @Test
    void PieceType_PieceColorAndMovableStrategy_GenerateInstance() {
        ChessPiece chessPiece = new King(PieceColor.BLACK, new InitialState(new KingRuleStrategy()));

        assertThat(chessPiece).isInstanceOf(ChessPiece.class);
    }

    @ParameterizedTest
    @NullSource
    void PieceType_NullPieceColor_ExceptionThrown(PieceColor pieceColor) {
        assertThatThrownBy(() -> new King(pieceColor, new InitialState(new KingRuleStrategy())))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("피스 색상이 null입니다.");
    }

    @ParameterizedTest
    @NullSource
    void PieceType_NullState_ExceptionThrown(InitialState initialState) {
        assertThatThrownBy(() -> new King(PieceColor.BLACK, initialState))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("피스 상태가 null입니다.");
    }
}