package chess.domain;


import chess.domain.piece.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest {
    
    @Test
    @DisplayName("board 초기화 테스트")
    void testBoardInit() {
        //given
        Board board = new Board();

        //when
        Piece rook = board.piece(Position.of(0, 0));
        Piece knight = board.piece(Position.of(1, 0));
        Piece bishop = board.piece(Position.of(2, 0));
        Piece queen = board.piece(Position.of(3, 0));
        Piece king = board.piece(Position.of(4, 0));

        //then
        assertThat(rook).isInstanceOf(Rook.class);
        assertThat(knight).isInstanceOf(Knight.class);
        assertThat(bishop).isInstanceOf(Bishop.class);
        assertThat(queen).isInstanceOf(Queen.class);
        assertThat(king).isInstanceOf(King.class);
    }


}
