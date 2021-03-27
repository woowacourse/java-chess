package chess.domain.player;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    // I create these and then test creation....
    Player whitePlayer = Player.of(PieceColor.WHITE);
    Player blackPlayer = Player.of(PieceColor.BLACK);

    @DisplayName("Player 객체 생성 : 성공")
    @Test
    void create() {
        assertAll(
            () -> assertThatCode(() -> Player.of(PieceColor.WHITE)).doesNotThrowAnyException(),
            () -> assertThatCode(() -> Player.of(PieceColor.BLACK)).doesNotThrowAnyException()
        );
    }

    @DisplayName("플레이어의 색과 체스말의 색이 일치할 경우 isOwnerOf는 true를 반환한다.")
    @Test
    void isOwnerOf_returnTrue_success() {
        // Given
        List<Piece> blackPieces = PieceProvider.blackPieces();
        List<Piece> whitePieces = PieceProvider.whitePieces();

        // Then
        for (Piece whitePiece : whitePieces) {
            assertTrue(whitePlayer.isOwnerOf(whitePiece));
        }
        for (Piece blackPiece : blackPieces) {
            assertTrue(blackPlayer.isOwnerOf(blackPiece));
        }
    }

    @DisplayName("플레이어의 색과 체스말의 색이 일치하지 않을 경우 isOwnerOf는 false를 반환한다.")
    @Test
    void isOwnerOf_returnFail_success() {
        // Given
        List<Piece> blackPieces = PieceProvider.blackPieces();
        List<Piece> whitePieces = PieceProvider.whitePieces();

        // Then
        for (Piece whitePiece : whitePieces) {
            assertFalse(blackPlayer.isOwnerOf(whitePiece));
        }
        for (Piece blackPiece : blackPieces) {
            assertFalse(whitePlayer.isOwnerOf(blackPiece));
        }
    }


}