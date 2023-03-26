package chess;

import chess.domain.Color;
import chess.domain.piece.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ChessPieceTest {

    @Test
    @DisplayName("각 기물들이 올바르게 생성된다.")
    void shouldSuccessGeneratePieces() {
        assertAll(
                () -> assertDoesNotThrow(() -> new King(Color.BLACK)),
                () -> assertDoesNotThrow(() -> new Queen(Color.BLACK)),
                () -> assertDoesNotThrow(() -> new Knight(Color.BLACK)),
                () -> assertDoesNotThrow(() -> new Bishop(Color.BLACK)),
                () -> assertDoesNotThrow(() -> new Rook(Color.BLACK)),
                () -> assertDoesNotThrow(() -> new Pawn(Color.BLACK)),
                () -> assertDoesNotThrow(() -> new King(Color.WHITE)),
                () -> assertDoesNotThrow(() -> new Queen(Color.WHITE)),
                () -> assertDoesNotThrow(() -> new Knight(Color.WHITE)),
                () -> assertDoesNotThrow(() -> new Bishop(Color.WHITE)),
                () -> assertDoesNotThrow(() -> new Rook(Color.WHITE)),
                () -> assertDoesNotThrow(() -> new Pawn(Color.WHITE)),
                () -> assertDoesNotThrow(Empty::new)
        );
    }
}
