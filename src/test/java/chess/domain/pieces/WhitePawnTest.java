package chess.domain.pieces;

import chess.domain.Direction;
import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import chess.domain.pieces.component.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WhitePawnTest {
    //public void checkStep(Position currentPosition, Direction direction, List<Piece> pieces) {
    @Test
    @DisplayName("checkStep()은 step이 3 이상이면 예외를 발생시킨다.")
    void test_over_step() {
        // given
        WhitePawn whitePawn = new WhitePawn(Type.PAWN);
        Position position = new Position(Rank.ONE, File.B);
        List<Piece> pieces = List.of(new BlackPawn(Type.PAWN), new BlackPawn(Type.PAWN), new BlackPawn(Type.PAWN));
        // when

        assertThatThrownBy(() -> whitePawn.checkStep(position, Direction.UP, pieces));
        // then
    }

    @Test
    @DisplayName("checkStep()은 처음 위치가 아닌데 두칸을 움직일 경우 예외를 발생시킨다.")
    void test_first_move_false() {
        // given
        WhitePawn whitePawn = new WhitePawn(Type.PAWN);
        Position position = new Position(Rank.TREE, File.B);
        List<Piece> pieces = List.of(new BlackPawn(Type.PAWN), new BlackPawn(Type.PAWN));
        // when

        assertThatThrownBy(() -> whitePawn.checkStep(position, Direction.UP, pieces));
        // then
    }
}
