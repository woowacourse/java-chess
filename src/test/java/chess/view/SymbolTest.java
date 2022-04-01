package chess.view;

import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class SymbolTest {

    @Test
    @DisplayName("Symbol 객체 반환 확인")
    void checkFindSymbol() {
        assertThat(Symbol.findSymbol(new Pawn(Color.WHITE))).isEqualTo(Symbol.PAWN);
    }

    @ParameterizedTest
    @CsvSource(value = {"KING:k", "PAWN:p", "QUEEN:q", "KNIGHT:k", "BISHOP:b", "ROOK:r"}, delimiter = ':')
    @DisplayName("기물에게 맞는 심볼 반환 확인")
    void checkStringSymbol(Symbol symbol, String stringSymbol) {
        assertThat(symbol.symbol()).isEqualTo(stringSymbol);
    }
}