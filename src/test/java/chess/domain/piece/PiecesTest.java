package chess.domain.piece;

import chess.exception.NoSuchPermittedChessPieceException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PiecesTest {

    @DisplayName("해당 위치의 체스말을 잘 찾는지 확인")
    @Test
    void findPieceByPosition() {
        Pieces pieces = new Pieces(Arrays.asList(
                Piece.createPawn(Color.BLACK, 0, 0)
        ));

        assertThat(pieces.findPieceByPosition(Color.BLACK, new Position(0, 0)))
                .isEqualTo(Piece.createPawn(Color.BLACK, 0, 0));

        assertThatThrownBy(() -> pieces.findPieceByPosition(Color.WHITE, new Position(0, 0)))
                .isExactlyInstanceOf(NoSuchPermittedChessPieceException.class);

        assertThatThrownBy(() -> pieces.findPieceByPosition(Color.BLACK, new Position(0, 1)))
                .isExactlyInstanceOf(NoSuchPermittedChessPieceException.class);
    }

    @DisplayName("한 위치에 2개의 체스말이 있는 경우 반대편 색의 말을 잡는지 확인")
    @Test
    void catchPiece() {
        Pieces pieces = new Pieces(Arrays.asList(
                Piece.createPawn(Color.WHITE, 0, 0),
                Piece.createPawn(Color.BLACK, 0, 0)
        ));

        pieces.catchPiece(Color.WHITE);
        assertThat(pieces.getPieces()).containsOnly(Piece.createPawn(Color.WHITE, 0, 0));
    }

    @DisplayName("검은 팀의 총점 확인")
    @Test
    void getBlackScore() {
        Pieces pieces = new Pieces(Arrays.asList(
                Piece.createPawn(Color.BLACK, 0, 1),
                Piece.createPawn(Color.BLACK, 1, 1),
                Piece.createPawn(Color.BLACK, 0, 2),
                Piece.createQueen(Color.BLACK, 2, 1),
                Piece.createRook(Color.BLACK, 3, 1),
                Piece.createKing(Color.BLACK, 4, 1),
                Piece.createKnight(Color.BLACK, 5, 1),
                Piece.createBishop(Color.BLACK, 6, 1)
        ));

        assertThat(pieces.getBlackScore()).isEqualTo(21.0);
    }

    @DisplayName("하얀 팀의 총점 확인")
    @Test
    void getWhiteScore() {
        Pieces pieces = new Pieces(Arrays.asList(
                Piece.createPawn(Color.WHITE, 0, 1),
                Piece.createPawn(Color.WHITE, 1, 1),
                Piece.createPawn(Color.WHITE, 0, 2),
                Piece.createQueen(Color.WHITE, 2, 1),
                Piece.createRook(Color.WHITE, 3, 1),
                Piece.createKing(Color.WHITE, 4, 1),
                Piece.createKnight(Color.WHITE, 5, 1),
                Piece.createBishop(Color.WHITE, 6, 1)
        ));

        assertThat(pieces.getWhiteScore()).isEqualTo(21.0);
    }

    @DisplayName("King 이 보드에 존재하는지 확인")
    @Test
    void isKingExist() {
        Pieces pieces = new Pieces(Arrays.asList(
                Piece.createKing(Color.WHITE, 0, 0),
                Piece.createKing(Color.BLACK, 0, 0)
        ));

        assertThat(pieces.isKingCatch()).isFalse();

        pieces = new Pieces(Collections.singletonList(
                Piece.createKing(Color.WHITE, 0, 0)
        ));

        assertThat(pieces.isKingCatch()).isTrue();
    }
}