package domain;

import domain.piece.Piece;
import domain.piece.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ChessBoardGeneratorTest {
    @DisplayName("64개의 칸이 생성되었는지 확인한다.")
    @Test
    void shouldReturn64WhenGetSizeOfChessBoard() {
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();
        Map<Position, Piece> chessBoard = chessBoardGenerator.generate();
        assertThat(chessBoard).hasSize(64);
    }
}