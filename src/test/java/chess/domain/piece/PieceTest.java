package chess.domain.piece;

import chess.domain.board.Board;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PieceTest {

    @DisplayName("체스말 생성 테스트")
    @Test
    void createPiece() {
        Piece piece = new Piece(Color.WHITE, Shape.ROOK, new Position(0, 0), null);
        Piece kingPiece = new Piece(Color.WHITE, Shape.KING, new Position(0, 0), null);

        assertThat(piece.isSameColor(Color.WHITE)).isTrue();
        assertThat(piece.isSameColor(Color.BLACK)).isFalse();
        assertThat(piece.isKing()).isFalse();
        assertThat(kingPiece.isKing()).isTrue();
    }

    @DisplayName("색상에 따라 노테이션을 반환하는 테스트")
    @Test
    void getNotation() {
        Piece whitePiece = new Piece(Color.WHITE, Shape.ROOK, new Position(0, 0), null);
        Piece blackPiece = new Piece(Color.BLACK, Shape.ROOK, new Position(0, 0), null);

        assertThat(whitePiece.getNotation()).isEqualTo("r");
        assertThat(blackPiece.getNotation()).isEqualTo("R");
    }

    @DisplayName("체스말이 움직이는 기능을 테스트한다")
    @Test
    void testMove() {
        //given
        Piece piece = Piece.createPawn(Color.BLACK, 1, 0);
        Board board = new Board(Arrays.asList(
                piece,
                Piece.createPawn(Color.WHITE, 2, 1)));
        Position target = new Position(2, 1);

        //when
        piece.move(target, board);

        //then
        assertThat(piece.getPosition()).isEqualTo(new Position(2, 1));
    }

    @DisplayName("체스말이 움직이는 기능을 테스트한다")
    @Test
    void testMoveIfNotMove() {
        //given
        Piece piece = Piece.createPawn(Color.BLACK, 1, 0);
        Board board = new Board(Arrays.asList(
                piece,
                Piece.createPawn(Color.WHITE, 2, 2)));
        Position target = new Position(2, 2);

        //when //then
        assertThatThrownBy(() -> piece.move(target, board))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

}
