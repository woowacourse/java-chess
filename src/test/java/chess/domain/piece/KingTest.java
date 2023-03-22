package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.position.move.BlockingMove;
import chess.domain.position.move.InvalidMove;
import chess.domain.position.move.PieceMove;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KingTest {

    @Test
    @DisplayName("킹은 모든 방향으로 움직일 수 있다.")
    void movableTest() {
        Piece king = new King(Camp.WHITE);

        Position from = Position.of(File.A, Rank.ONE);
        Position to = Position.of(File.A, Rank.TWO);

        PieceMove result = king.getMovement(from, to);

        assertThat(result).isInstanceOf(BlockingMove.class);
    }

    @Test
    @DisplayName("킹은 한 칸만 움직일 수 있다.")
    void movableSizeTest() {
        Piece king = new King(Camp.WHITE);

        Position from = Position.of(File.A, Rank.ONE);
        Position to = Position.of(File.C, Rank.THREE);

        PieceMove result = king.getMovement(from, to);

        assertThat(result).isInstanceOf(InvalidMove.class);
    }
}
