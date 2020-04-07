package chess.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class QueenTest {
    @DisplayName("체스말의 팀과 종류에 따라 심볼이 반환된다.")
    @ParameterizedTest
    @CsvSource(value = {"BLACK,Q", "WHITE,q"})
    void getSymbolTest(Team team, char expected) {
        Piece piece = new Piece(team, PieceType.QUEEN);
        assertThat(piece.getSymbol()).isEqualTo(expected);
    }
}
