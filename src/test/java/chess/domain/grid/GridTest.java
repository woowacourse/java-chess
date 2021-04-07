package chess.domain.grid;

import chess.domain.grid.gridStrategy.NormalGridStrategy;
import chess.domain.grid.gridStrategy.TestGridStrategy;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GridTest {
    @Test
    @DisplayName("해당 위치의 Piece를 잘 찾아내는 지 테스트")
    public void piece() {
        Grid grid = new Grid(new TestGridStrategy());
        Position position = new Position('b', '4');
        Piece piece = new King(Color.WHITE, 'b', '4');
        grid.lines().assign(position, piece);
        assertThat(grid.piece(position)).isEqualTo(piece);
    }

    @Test
    @DisplayName("점수를 잘 계산하는 지 테스트")
    public void score() {
        Grid grid = new Grid(new TestGridStrategy());
        Position position = new Position('b', '4');
        Piece piece = new Queen(Color.WHITE, 'b', '4');
        grid.lines().assign(position, piece);
        assertThat(grid.score(Color.WHITE)).isEqualTo(9);
    }

    @Test
    @DisplayName("Grid 내에 있는 모든 Piece들을 조회한 개수가 64개가 맞는 지 테스트")
    public void pieces() {
        Grid grid = new Grid(new NormalGridStrategy());
        assertThat(grid.pieces().size()).isEqualTo(64);
    }
}
