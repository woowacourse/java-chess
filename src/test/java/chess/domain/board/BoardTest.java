package chess.domain.board;

import chess.domain.board.piece.Owner;
import chess.domain.board.piece.Pawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest {
    @DisplayName("입력한 위치의 Square를 가져온다")
    @Test
    void of(){
        Board board = BoardInitializer.initiateBoard();

        Square square = board.of(Vertical.B, Horizontal.TWO);

        assertThat(square).isEqualTo(new Square(Vertical.B, Horizontal.TWO, Pawn.getInstanceOf(Owner.WHITE)));
    }
}
