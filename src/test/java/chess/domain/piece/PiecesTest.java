package chess.domain.piece;

import chess.domain.board.Board;
import chess.exception.NoSuchPermittedChessPieceException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PiecesTest {

    @DisplayName("포지션에 있는 체스 말을 가져온다. 만일 자신의 말이 아니거나 말이 없다면 예외")
    @Test
    void findPieceByPosition() {
        Pieces pieces = new Pieces(Arrays.asList(
                Piece.createPawn(Color.BLACK, 0, 0)
        ));

        assertThatThrownBy(() -> pieces.movePiece(Color.WHITE, new Position(0, 0), new Position(0, 1), null))
                .isExactlyInstanceOf(NoSuchPermittedChessPieceException.class);

        assertThatThrownBy(() -> pieces.movePiece(Color.BLACK, new Position(0, 1), new Position(0, 1), null))
                .isExactlyInstanceOf(NoSuchPermittedChessPieceException.class);
    }

    @DisplayName("포지션의 상대편의 말이 있다면 상대 말을 없앤다")
    @Test
    void catchPiece() {
        Pieces pieces = new Pieces(Arrays.asList(
                Piece.createPawn(Color.WHITE, 0, 0),
                Piece.createPawn(Color.BLACK, 0, 0)
        ));

        pieces.catchPiece(Color.WHITE);
        assertThat(pieces.getPieces()).containsExactly(Piece.createPawn(Color.WHITE, 0, 0));
    }

    @DisplayName("블랙팀의 점수를 반환한다.")
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

        assertThat(pieces.getBlackScore(new Board(Collections.emptyList()))).isEqualTo(21.0);
    }

    @DisplayName("하얀팀의 점수를 반환한다.")
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

        assertThat(pieces.getWhiteScore(new Board(Collections.emptyList()))).isEqualTo(21.0);
    }

    @DisplayName("체스판에 블랙, 화이트 킹이 둘 다 존재하는지 확인한다.")
    @Test
    void isKingsExist() {
        Pieces pieces = new Pieces(Arrays.asList(
                Piece.createKing(Color.WHITE, 0, 0),
                Piece.createKing(Color.BLACK, 0, 0)
        ));

        assertThat(pieces.isKingsExist()).isTrue();

        pieces = new Pieces(Collections.singletonList(
                Piece.createKing(Color.WHITE, 0, 0)
        ));

        assertThat(pieces.isKingsExist()).isFalse();
    }
}