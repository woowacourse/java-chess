package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.position.move.PawnCatchMove;
import chess.domain.position.move.PawnForwardMove;
import chess.domain.position.move.PieceMove;
import org.junit.jupiter.api.DisplayName;
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
}
