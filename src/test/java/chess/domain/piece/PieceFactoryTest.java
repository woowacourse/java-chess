package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceFactoryTest {

    @Test
    @DisplayName("새로운 블랙 기물 목록 생성")
    void createNewBlackPieces() {
        List<Piece> blackPieces = PieceFactory.createNewBlackPieces();
        assertThat(blackPieces).hasSize(16);
    }
}
