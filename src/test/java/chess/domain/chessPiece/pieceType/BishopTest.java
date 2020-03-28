package chess.domain.chessPiece.pieceType;

import chess.domain.RuleStrategy.nonLeapableStrategy.BishopRuleStrategy;
import chess.domain.chessPiece.pieceState.InitialState;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {
    @Test
    void Bishop_PieceColor_GenerateInstance() {
        assertThat(new Bishop(PieceColor.BLACK, new InitialState(new BishopRuleStrategy()))).isInstanceOf(Bishop.class);
    }

    @Test
    void getName_ReturnName() {
        assertThat(new Bishop(PieceColor.BLACK, new InitialState(new BishopRuleStrategy())).getName()).isEqualTo("B");
        assertThat(new Bishop(PieceColor.WHITE, new InitialState(new BishopRuleStrategy())).getName()).isEqualTo("b");
    }
}