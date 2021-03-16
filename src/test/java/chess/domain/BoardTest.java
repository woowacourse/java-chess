package chess.domain;

import chess.domain.piece.Pawn;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {
    @Test
    @DisplayName("폰 초기화 기능 확인")
    void initiate() {
        final Board board = new Board();
        board.initializePawn();
        assertThat(board.unwrap().get(new Position("a", "7")))
                .isInstanceOf(Pawn.class);
    }
}