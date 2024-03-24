package chess.model.piece;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.model.position.Movement;
import chess.model.position.Position;
import org.junit.jupiter.api.Test;

class PieceTest {
    @Test
    void 목적지_기물이_자신과_동일한_색상이면_예외가_발생한다() {
        Movement movement = new Movement(Position.of(1, 1), Position.of(1, 2));
        Piece piece = PieceFactory.of(Color.WHITE, Type.ROOK);
        Piece target = PieceFactory.of(Color.WHITE, Type.PAWN);
        assertThatThrownBy(() -> piece.canMove(movement, target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 목적지_기물이_자신과_다른_색상이면_예외가_발생하지_않는다() {
        Movement movement = new Movement(Position.of(1, 1), Position.of(1, 2));
        Piece piece = PieceFactory.of(Color.WHITE, Type.ROOK);
        Piece target = PieceFactory.of(Color.BLACK, Type.PAWN);
        assertThatCode(() -> piece.canMove(movement, target))
                .doesNotThrowAnyException();
    }
}
