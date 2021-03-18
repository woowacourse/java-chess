package chess.domain.board;

import chess.domain.board.position.Horizontal;
import chess.domain.board.position.Position;
import chess.domain.board.position.Vertical;
import chess.domain.piece.King;
import chess.domain.piece.Owner;
import chess.domain.piece.Piece;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class SquareTest {

    @DisplayName("Square 생성 테스트")
    @Test
    void create(){
        Square square = new Square(new Position(Vertical.A, Horizontal.FIVE), new King(Owner.BLACK));
        assertThat(square).isInstanceOf(Square.class);
    }

//    @DisplayName("Square 단순 이동 테스트")
//    @Test
//    void move(){
//        Board board = BoardInitializer.initiateBoard();
//
//        Square s1 = board.of(Vertical.A, Horizontal.ONE);
//        Piece pieceToMove = s1.getPiece();
//        s1.move(board.of(Vertical.C, Horizontal.THREE));
//
//        assertThat(board.of(Vertical.C, Horizontal.THREE))
//                .isEqualTo(new Square(new Position(Vertical.C, Horizontal.THREE), pieceToMove));
//    }

}