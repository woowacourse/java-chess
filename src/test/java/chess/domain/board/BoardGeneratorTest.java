package chess.domain.board;

import chess.domain.board.BoardGenerator;
import chess.domain.board.LineNumber;
import chess.domain.board.Point;
import chess.domain.piece.Piece;
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
