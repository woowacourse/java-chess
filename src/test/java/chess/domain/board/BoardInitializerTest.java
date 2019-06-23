package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static chess.domain.piece.PieceType.*;
import static org.assertj.core.api.Assertions.assertThat;

class BoardInitializerTest {
    Map<Tile, Piece> board;

    @BeforeEach
    void setUp() {
        board = BoardInitializer.initialize();
    }

    @Test
    void 체스판_초기화_검사_1() {
        assertThat(board.get(Tile.of("a1"))).isEqualTo(ROOK.generate(PieceColor.WHITE));
    }

    @Test
    void 체스판_초기화_검사_2() {
        assertThat(board.get(Tile.of("a8"))).isEqualTo(ROOK.generate(PieceColor.BLACK));
    }

    @Test
    void 체스판_초기화_검사_3() {
        assertThat(board.get(Tile.of("e1"))).isEqualTo(KING.generate(PieceColor.WHITE));
    }

    @Test
    void 체스판_초기화_검사_4() {
        assertThat(board.get(Tile.of("d8"))).isEqualTo(QUEEN.generate(PieceColor.BLACK));
    }

    @Test
    void 체스판_초기화_검사_5() {
        assertThat(board.get(Tile.of("d5"))).isNull();
    }
}