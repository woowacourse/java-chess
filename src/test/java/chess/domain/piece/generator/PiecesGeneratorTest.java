package chess.domain.piece.generator;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PiecesGeneratorTest {

    @Test
    @DisplayName("32개의 체스말들을 생성한다")
    void generate() {
        final PiecesGenerator piecesGenerator = new NormalPiecesGenerator();
        final Map<Position, Piece> pieces = piecesGenerator.generate();

        assertThat(pieces).hasSize(32);
    }
}
