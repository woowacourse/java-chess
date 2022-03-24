package chess.domain.board;

import chess.domain.piece.Piece;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardGeneratorTest {

    @Test
    @DisplayName("초기 체스판을 만든다.")
    void createInitialPieces() {
        BoardGenerator constructor = new InitialBoardGenerator();
        Map<Point, Piece> pointPieces = constructor.generate();
        assertThat(pointPieces.size()).isEqualTo(LineNumber.MAX * LineNumber.MAX);
    }
}
