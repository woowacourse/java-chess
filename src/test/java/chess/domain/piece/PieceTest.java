package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.piece.white.WhiteQueen;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class PieceTest {

    @DisplayName("체스말 생성 테스트")
    @Test
    void createPiece() {
        /*ChessPiece piece =  new ChessPiece(Shape.ROOK, new Position(0, 0), null);
        ChessPiece kingPiece = new ChessPiece(Shape.KING, new Position(0, 0), null);

        assertThat(piece.isSameColor(Color.WHITE)).isTrue();
        assertThat(piece.isSameColor(Color.BLACK)).isFalse();
        assertThat(piece.isKing()).isFalse();
        assertThat(kingPiece.isKing()).isTrue();*/
    }

    @DisplayName("색상에 따라 노테이션을 반환하는 테스트")
    @Test
    void getNotation() {
        /*ChessPiece whitePiece = new ChessPiece(Shape.ROOK, new Position(0, 0), null);
        ChessPiece blackPiece = new ChessPiece( Shape.ROOK, new Position(0, 0), null);

        assertThat(whitePiece.getNotation()).isEqualTo("r");
        assertThat(blackPiece.getNotation()).isEqualTo("R");*/
    }

    @DisplayName("말의 움직임이 이루어지는지 확인")
    @Test
    void move() {
        ChessPiece piece = WhiteQueen.createWithCoordinate(0, 0);

        Board board = new Board(Collections.singletonList(
                piece
        ));

        piece.move(new Position(0, 1), board);

        assertThat(piece.isSamePosition(new Position(0, 1))).isTrue();
    }
}