package chess.domain.piece;

import chess.domain.piece.NormalPiecesGenerator;
import chess.domain.piece.Piece;
import chess.domain.piece.PiecesGenerator;
import chess.domain.position.Position;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PiecesGeneratorTest {

    @Test
    @DisplayName("32개의 체스말들을 생성한다")
    void generate() {
        PiecesGenerator piecesGenerator = new NormalPiecesGenerator();
        Map<Position, Piece> pieces = piecesGenerator.generate();
        Assertions.assertThat(pieces).hasSize(32);
    }
}
