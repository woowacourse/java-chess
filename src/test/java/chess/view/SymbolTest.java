package chess.view;

import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SymbolTest {

    @Test
    @DisplayName("Symbol 객체 반환 확인")
    void checkFindSymbol() {
        assertThat(Symbol.findSymbol(new Pawn(Color.WHITE))).isEqualTo(Symbol.PAWN);
    }

    @Test
    @DisplayName("기물에게 맞는 심볼 반환 확인")
    void checkStringSymbol() {
        assertThat(Symbol.KING.symbol(new King(Color.WHITE))).isEqualTo("k");
    }
}
