package chess.domain.chessPiece.factory;

import chess.domain.chessPiece.piece.Piece;
import chess.domain.chessPiece.team.BlackTeam;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PieceBundleFactoryTest {
    @Test
    @DisplayName("체스 말들이 생성된다")
    void createPiecesTest() {
        List<Piece> pieces = PieceBundleFactory.createPieceSet(new BlackTeam());
        assertThat(pieces.size()).isEqualTo(16);
    }
}