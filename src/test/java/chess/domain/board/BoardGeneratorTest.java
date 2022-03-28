package chess.domain.board;

import static org.assertj.core.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.piece.Piece;

public class BoardGeneratorTest {

    @Test
    @DisplayName("초기 체스판을 만든다.")
    void createInitialPieces() {
        BoardGenerator constructor = new InitialBoardGenerator();
        Map<Point, Piece> pointPieces = constructor.generate();
        assertThat(pointPieces.size()).isEqualTo(LineNumber.MAX * LineNumber.MAX);
    }
}
