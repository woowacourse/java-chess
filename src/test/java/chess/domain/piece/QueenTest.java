package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import chess.domain.Position;

class QueenTest {

    @ParameterizedTest
    @EnumSource(Color.class)
    @DisplayName("퀸 기물 생성")
    void createQueen(Color color) {
        Piece queen = new Queen(color, new Position('a', '1'));
        assertThat(queen).isInstanceOf(Queen.class);
    }
}
