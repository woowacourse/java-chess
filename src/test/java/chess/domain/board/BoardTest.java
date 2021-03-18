package chess.domain.board;

import chess.domain.board.position.Horizontal;
import chess.domain.board.position.Position;
import chess.domain.board.position.Vertical;
import chess.domain.piece.Owner;
import chess.domain.piece.Pawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest {
    @DisplayName("입력한 위치의 Square를 가져온다")
    @Test
    void of(){
        Board board = BoardInitializer.initiateBoard();

        Square square = board.of(Vertical.B, Horizontal.TWO);
        Position position = new Position(Vertical.B, Horizontal.TWO);
        assertThat(square).isEqualTo(new Square(position, Pawn.getInstanceOf(Owner.WHITE)));
    }
}
