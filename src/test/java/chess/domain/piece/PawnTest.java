package chess.domain.piece;

import chess.domain.board.Horizontal;
import chess.domain.board.Position;
import chess.domain.board.Vertical;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {
    @Test
    @DisplayName("블랙 폰인데 첫 이동일 경우")
    void blackPawnFirstMove() {
        Pawn pawn = new Pawn(Team.BLACK);
        List<Position> positions = pawn.searchMovablePositions(Position.of(Horizontal.A, Vertical.SEVEN));
        assertThat(positions).containsExactly(
                Position.of(Horizontal.A, Vertical.FIVE),
                Position.of(Horizontal.A, Vertical.SIX)
        );
    }

    @Test
    @DisplayName("블랙 폰인데 첫 이동이 아닐 경우")
    void blackPawnNotFirstMove() {
        Pawn pawn = new Pawn(Team.BLACK);
        List<Position> positions = pawn.searchMovablePositions(Position.of(Horizontal.A, Vertical.SIX));
        assertThat(positions).containsExactly(
                Position.of(Horizontal.A, Vertical.FIVE)
        );
    }

    @Test
    @DisplayName("화이트 폰인데 첫 이동일 경우")
    void whitePawnFirstMove() {
        Pawn pawn = new Pawn(Team.WHITE);
        List<Position> positions = pawn.searchMovablePositions(Position.of(Horizontal.A, Vertical.TWO));
        assertThat(positions).containsExactly(
                Position.of(Horizontal.A, Vertical.FOUR),
                Position.of(Horizontal.A, Vertical.THREE)
        );
    }

    @Test
    @DisplayName("화이트 폰인데 첫 이동이 아닐 경우")
    void whitePawnNotFirstMove() {
        Pawn pawn = new Pawn(Team.WHITE);
        List<Position> positions = pawn.searchMovablePositions(Position.of(Horizontal.A, Vertical.SEVEN));
        assertThat(positions).containsExactly(
                Position.of(Horizontal.A, Vertical.EIGHT)
        );
    }
}