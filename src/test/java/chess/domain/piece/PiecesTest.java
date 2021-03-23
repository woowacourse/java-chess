package chess.domain.piece;

import chess.exception.NoSuchPermittedChessPieceException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PiecesTest {

    @DisplayName("특정 위치로 피스를 찾는 기능")
    @Test
    void findPieceByPosition() {
        Pieces pieces = new Pieces(Arrays.asList(
                Piece.createPawn(Color.BLACK, 0, 0)
        ));

        assertThat(pieces.findControllablePieceByPosition(Color.BLACK, new Position(0, 0)))
                .isEqualTo(Piece.createPawn(Color.BLACK, 0, 0));

        assertThatThrownBy(() -> pieces.findControllablePieceByPosition(Color.WHITE, new Position(0, 0)))
                .isExactlyInstanceOf(NoSuchPermittedChessPieceException.class);

        assertThatThrownBy(() -> pieces.findControllablePieceByPosition(Color.BLACK, new Position(0, 1)))
                .isExactlyInstanceOf(NoSuchPermittedChessPieceException.class);
    }

    @Test
    void catchPiece() {
        Pieces pieces = new Pieces(Arrays.asList(
                Piece.createPawn(Color.WHITE, 0, 0),
                Piece.createPawn(Color.BLACK, 0, 0)
        ));

        pieces.catchPiece(Color.WHITE);
        assertThat(pieces.getPieces()).containsExactly(Piece.createPawn(Color.WHITE, 0, 0));
    }

    @DisplayName("검은색의 현재 총점을 확인하는 기능")
    @Test
    void getBlackScore() {
        Pieces pieces = new Pieces(Arrays.asList(
                Piece.createKing(Color.BLACK, 0, 1),
                Piece.createRook(Color.BLACK, 0, 2),
                Piece.createPawn(Color.BLACK, 1, 0),
                Piece.createPawn(Color.BLACK, 1, 2),
                Piece.createBishop(Color.BLACK, 1, 3),
                Piece.createPawn(Color.BLACK, 2, 1),
                Piece.createQueen(Color.BLACK, 2, 4)
        ));

        assertThat(pieces.getBlackScore()).isEqualTo(20.0);
    }

    @DisplayName("하얀색의 현재 총점을 확인하는 기능")
    @Test
    void getWhiteScore() {
        Pieces pieces = new Pieces(Arrays.asList(
                Piece.createRook(Color.WHITE, 7, 4),
                Piece.createKing(Color.WHITE, 7, 5),
                Piece.createPawn(Color.WHITE, 6, 5),
                Piece.createPawn(Color.WHITE, 6, 6),
                Piece.createPawn(Color.WHITE, 5, 5),
                Piece.createPawn(Color.WHITE, 5, 7),
                Piece.createKnight(Color.WHITE, 4, 5),
                Piece.createQueen(Color.WHITE, 4, 6)
        ));

        assertThat(pieces.getWhiteScore()).isEqualTo(19.5);
    }

    @DisplayName("모든 왕이 존재하는지 확인하는 기능")
    @Test
    void isKingsExist() {
        Pieces pieces = new Pieces(Arrays.asList(
                Piece.createKing(Color.WHITE, 0, 0),
                Piece.createKing(Color.BLACK, 0, 0)
        ));

        assertThat(pieces.isCaughtKing()).isFalse();

        pieces = new Pieces(Collections.singletonList(
                Piece.createKing(Color.WHITE, 0, 0)
        ));

        assertThat(pieces.isCaughtKing()).isTrue();
    }

}
