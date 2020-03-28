package chess.domain.chessPiece.pieceType;

import chess.domain.RuleStrategy.nonLeapableStrategy.RookRuleStrategy;
import chess.domain.chessPiece.pieceState.InitialState;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RookTest {
    @Test
    void Rook_PieceColor_GenerateInstance() {
        assertThat(new Rook(PieceColor.BLACK, new InitialState(new RookRuleStrategy()))).isInstanceOf(Rook.class);
    }

    @Test
    void getName_ReturnName() {
        assertThat(new Rook(PieceColor.BLACK, new InitialState(new RookRuleStrategy())).getName()).isEqualTo("R");
        assertThat(new Rook(PieceColor.WHITE, new InitialState(new RookRuleStrategy())).getName()).isEqualTo("r");
    }
}