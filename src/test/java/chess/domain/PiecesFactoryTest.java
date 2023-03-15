package chess.domain;


import static chess.domain.piece.Color.BLACK;
import static org.junit.jupiter.api.Assertions.*;

import chess.domain.piece.PiecesFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PiecesFactoryTest {

    @Test
    @DisplayName("Pieces를 생성한다.")
    void createPieces() {
        assertDoesNotThrow(() -> PiecesFactory.create(BLACK));
    }
}
