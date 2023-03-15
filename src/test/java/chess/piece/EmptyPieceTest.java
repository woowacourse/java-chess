package chess.piece;

import chess.board.File;
import chess.board.Position;
import chess.board.Rank;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class EmptyPieceTest {

    @Test
    void 빈_말에_대해_메소드를_수행하면_예외() {
        //given
        EmptyPiece emptyPiece = new EmptyPiece();
        Position to = new Position(File.B, Rank.THREE);
        Position from = new Position(File.A, Rank.ONE);

        //when & then
        Assertions.assertThatThrownBy(() -> emptyPiece.isMovable(from, to))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("빈 말은 움직일 수 없습니다.");
    }

}
