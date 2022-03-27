package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.postion.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardInitializerTest {

    @DisplayName("체스판 초기화 테스트")
    @Test
    void init() {
        Board board = new BoardInitializer().init();
        Map<Position, Piece> cells = board.getPieces();

        assertThat(cells.size()).isEqualTo(32);
    }
}
