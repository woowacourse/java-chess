package chess.board;

import chess.piece.Piece;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardGeneratorTest {

    @Test
    @DisplayName("")
    void createInitialPieces() {
        BoardGenerator constructor = new BoardGenerator();
        Map<Point, Piece> pointPieces = constructor.generate();
        assertThat(pointPieces.size()).isEqualTo(LineNumber.MAX * LineNumber.MAX);
    }
}
