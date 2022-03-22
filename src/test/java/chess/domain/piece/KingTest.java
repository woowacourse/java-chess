package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import chess.domain.Position;

class KingTest {

    @ParameterizedTest
    @EnumSource(Color.class)
    @DisplayName("킹 기물 생성")
    void createKing(Color color) {
        Piece king = new King(color, new Position('a', '1'));
        assertThat(king).isInstanceOf(King.class);
    }
}
