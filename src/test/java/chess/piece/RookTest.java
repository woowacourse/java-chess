package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {

    @Test
    @DisplayName("상하좌우로 칸 수 제한 없이 움직일 수 있다.")
    void should_move_up_down_left_right_unlimited() {
        Piece piece = new Rook(Color.WHITE);
        Position from = new Position(File.a, Rank.ONE);
        Position to = new Position(File.a, Rank.THREE);

        assertThat(piece.canMove(from, to)).isTrue();
    }
}
