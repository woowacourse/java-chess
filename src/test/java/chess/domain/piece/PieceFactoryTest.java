package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PieceFactoryTest {
    @DisplayName("초기 기물들을 만든다.")
    @Test
    void 초기_기물_생성() {
        PieceFactory pieceFactory = new PieceFactory();

        List<Piece> currentPieces = pieceFactory.initialPieces();
        int initialPiecesCount = currentPieces.size();

        assertThat(initialPiecesCount).isEqualTo(32);
    }
}
