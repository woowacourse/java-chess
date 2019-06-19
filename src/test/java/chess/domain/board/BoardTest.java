package chess.domain.board;

import chess.domain.ChessPiece;
import chess.domain.PieceColor;
import chess.domain.PieceType;
import chess.domain.Tile;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BoardTest {
    Map<Tile, ChessPiece> boardState = new HashMap<Tile, ChessPiece>() {
        {
            put(Tile.of("a1"), new ChessPiece(PieceColor.BLACK, PieceType.QUEEN));
        }
    };
    Board board = new Board(boardState);

    @Test
    void 보드_초기화_테스트1() {

        assertThat(board.at("a1"))
                .isEqualTo(Optional.of(new ChessPiece(PieceColor.BLACK, PieceType.QUEEN)));
    }

    @Test
    void 보드_초기화_테스트2() {

        assertThat(board.at("a2"))
                .isEqualTo(Optional.empty());
    }

    @Test
    void 보드_초기화_에러_확인1() {
        assertThrows(Exception.class, () ->
                board.at("1a")
        );
    }

    @Test
    void 보드_초기화_에러_확인2() {
        assertThrows(Exception.class, () ->
                board.at("i1")
        );
    }

    @Test
    void 보드_초기화_에러_확인3() {
        assertThrows(Exception.class, () ->
                board.at("a0")
        );
    }

    @Test
    void 보드_초기화_에러_확인4() {
        assertThrows(Exception.class, () ->
                board.at("a9")
        );
    }
}