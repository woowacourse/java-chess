package chess.domain.piece;

import chess.domain.board.BoardFactory;
import chess.domain.point.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class BoardFactoryTest {

    @Test
    @DisplayName("기물들을 생성할 수 있다.")
    void create() {
        Map<Point, Piece> pieces = BoardFactory.createInitialChessBoard();

        assertThat(pieces).hasSize(64);
    }
}
