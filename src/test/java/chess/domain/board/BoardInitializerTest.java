package chess.domain.board;

import chess.domain.ChessPiece;
import chess.domain.PieceColor;
import chess.domain.PieceType;
import chess.domain.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class BoardInitializerTest {
    Map<Tile, ChessPiece> board;

    @BeforeEach
    void setUp() {
        board = BoardInitializer.initialize();
    }

    @Test
    void 체스판_초기화_검사_1() {
        assertThat(board.get(Tile.of("a1"))).isEqualTo(new ChessPiece(PieceColor.WHITE, PieceType.ROOK));
    }

    @Test
    void 체스판_초기화_검사_2() {
        assertThat(board.get(Tile.of("a8"))).isEqualTo(new ChessPiece(PieceColor.BLACK, PieceType.ROOK));
    }

    @Test
    void 체스판_초기화_검사_3() {
        assertThat(board.get(Tile.of("e1"))).isEqualTo(new ChessPiece(PieceColor.WHITE, PieceType.KING));
    }

    @Test
    void 체스판_초기화_검사_4() {
        assertThat(board.get(Tile.of("d8"))).isEqualTo(new ChessPiece(PieceColor.BLACK, PieceType.QUEEN));
    }

    @Test
    void 체스판_초기화_검사_5() {
        assertThat(board.get(Tile.of("d5"))).isNull();
    }
}