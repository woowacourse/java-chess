package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.position.move.InvalidMove;
import chess.domain.position.move.PawnCatchMove;
import chess.domain.position.move.PawnForwardMove;
import chess.domain.position.move.PieceMove;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PawnTest {

    @Test
    @DisplayName("폰은 한 칸 전진할 수 있다.")
    void isMovable1() {
        Piece pawn = new Pawn(Camp.WHITE);

        Position from = Position.of(File.A, Rank.ONE);
        Position to = Position.of(File.A, Rank.TWO);

        PieceMove result = pawn.getMovement(from, to);

        assertThat(result).isInstanceOf(PawnForwardMove.class);
    }

    @Test
    @DisplayName("처음 움직이는 폰은 두 칸까지 움직일 수 있다.")
    void isMovable2() {
        Piece pawn = new Pawn(Camp.WHITE);

        Position from = Position.of(File.A, Rank.ONE);
        Position to = Position.of(File.A, Rank.THREE);

        PieceMove result = pawn.getMovement(from, to);

        assertThat(result).isInstanceOf(PawnForwardMove.class);
    }

    @Test
    @DisplayName("폰은 대각선 한 칸 앞으로 움직일 수 있다.")
    void isMovableDiagonal() {
        Piece pawn = new Pawn(Camp.WHITE);

        Position from = Position.of(File.A, Rank.ONE);
        Position to = Position.of(File.B, Rank.TWO);

        PieceMove result = pawn.getMovement(from, to);

        assertThat(result).isInstanceOf(PawnCatchMove.class);
    }

    @Nested
    @DisplayName("폰은 진영에 따라 전진 방향이 다르다.")
    class PawnMoveForwardTest {

        @Test
        @DisplayName("하얀색 폰은 위 방향이 전진이다.")
        void whitePawnMoveForwardTest() {
            Piece pawn = new Pawn(Camp.WHITE);

            Position from = Position.of(File.B, Rank.ONE);
            Position to = Position.of(File.A, Rank.ONE);

            PieceMove result = pawn.getMovement(from, to);

            assertThat(result).isInstanceOf(InvalidMove.class);
        }

        @Test
        @DisplayName("검은색 폰은 아래 방향이 전진이다.")
        void blackPawnMoveForwardTest() {
            Piece pawn = new Pawn(Camp.BLACK);

            Position from = Position.of(File.A, Rank.ONE);
            Position to = Position.of(File.B, Rank.ONE);

            PieceMove result = pawn.getMovement(from, to);

            assertThat(result).isInstanceOf(InvalidMove.class);
        }
    }
}
