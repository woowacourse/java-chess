package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import chess.domain.piece.Shape;
import chess.exception.ChessPieceCollisionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BoardTest {

//    @DisplayName("체스 말을 움직이는 기능을 테스트한다")
//    @Test
//    void move() {
//        Board board = new Board(Arrays.asList(new Piece(Color.BLACK, Shape.PAWN, new Position(0, 0)),
//                new Piece(Color.BLACK, Shape.PAWN, new Position(1, 0))));
//
//        assertThatThrownBy(() -> board.movePiece(new Position(0,0), new Position(1,0)))
//                .isExactlyInstanceOf(ChessPieceCollisionException.class);
//    }
}