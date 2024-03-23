package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Piece;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardFactoryTest {

    @Test
    @DisplayName("기물들을 생성할 수 있다.")
    void create() {
        Map<Point, Piece> pieces = BoardFactory.createInitialChessBoard();

        assertThat(pieces).hasSize(64);
    }
}
