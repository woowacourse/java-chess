package chess.domain.chessPiece.pieceType;

import chess.domain.RuleStrategy.KingRuleStrategy;
import chess.domain.chessPiece.pieceState.InitialState;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KingTest {
    @Test
    void King_PieceColor_GenerateInstance() {
        assertThat(new King(PieceColor.BLACK, new InitialState(new KingRuleStrategy()))).isInstanceOf(King.class);
    }

    @Test
    void getName_ReturnName() {
        assertThat(new King(PieceColor.BLACK, new InitialState(new KingRuleStrategy())).getName()).isEqualTo("K");
        assertThat(new King(PieceColor.WHITE, new InitialState(new KingRuleStrategy())).getName()).isEqualTo("k");
    }
}