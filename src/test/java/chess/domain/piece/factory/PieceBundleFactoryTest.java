package chess.domain.piece.factory;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceBundleFactory;
import chess.domain.team.BlackTeam;
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