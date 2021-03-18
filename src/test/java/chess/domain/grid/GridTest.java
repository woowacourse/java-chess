package chess.domain.grid;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GridTest {
    @Test
    @DisplayName("첫 체스판에서 Night(b1)가 c3으로 이동하는 지 테스트")
    public void move_Night_b1_To_c3() {
        Grid grid = new Grid();
        Position source = new Position('b', '1');
        Position target = new Position('c', '3');
        Piece sourcePiece = grid.findPiece(source);
        grid.move(source, target);
        Piece targetPiece = grid.findPiece(target);
        assertThat(sourcePiece).isEqualTo(targetPiece);
    }
}
