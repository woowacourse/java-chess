package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.piece.move.BlockingMove;
import chess.domain.piece.move.InvalidMove;
import chess.domain.piece.move.PieceMove;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class KingTest {

    @Nested
    @DisplayName("킹은 모든 방향으로 움직일 수 있다.")
    class KingMoveDirectionTest {

        @Test
        @DisplayName("대각선 방향")
        void movableDiagonalTest() {
            Piece king = new King(Camp.WHITE);

            Position from = Position.of(File.A, Rank.ONE);
            Position to = Position.of(File.B, Rank.TWO);

            PieceMove result = king.getMovement(from, to);

            assertThat(result).isInstanceOf(BlockingMove.class);
        }

        @Test
        @DisplayName("직진 방향")
        void movableForwardTest() {
            Piece king = new King(Camp.WHITE);

            Position from = Position.of(File.A, Rank.ONE);
            Position to = Position.of(File.A, Rank.TWO);

            PieceMove result = king.getMovement(from, to);

            assertThat(result).isInstanceOf(BlockingMove.class);
        }

        @Test
        @DisplayName("후진 방향")
        void movableBackwardTest() {
            Piece king = new King(Camp.WHITE);

            Position from = Position.of(File.A, Rank.TWO);
            Position to = Position.of(File.A, Rank.ONE);

            PieceMove result = king.getMovement(from, to);

            assertThat(result).isInstanceOf(BlockingMove.class);
        }
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
