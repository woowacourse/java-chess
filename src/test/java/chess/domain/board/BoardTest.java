package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceGenerator;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BoardTest {
    Map<Tile, Piece> boardState = new HashMap<Tile, Piece>() {{
        put(Tile.of("a1"), PieceGenerator.QUEEN.generate(PieceColor.BLACK));
    }};
    Board customBoard = new Board(boardState);
    Board gameBoard;

    @Test
    void 보드_초기화_테스트1() {
        assertThat(customBoard.at("a1"))
                .isEqualTo(PieceGenerator.QUEEN.generate(PieceColor.BLACK));
    }

    @Test
    void 보드_초기화_테스트2() {
        assertThrows(RuntimeException.class, () ->
                customBoard.at("a2"));
    }

    @Test
    void 보드_초기화_에러_확인1() {
        assertThrows(Exception.class, () ->
                customBoard.at("1a")
        );
    }

    @Test
    void 보드_초기화_에러_확인2() {
        assertThrows(Exception.class, () ->
                customBoard.at("i1")
        );
    }

    @Test
    void 보드_초기화_에러_확인3() {
        assertThrows(Exception.class, () ->
                customBoard.at("a0")
        );
    }

    @Test
    void 보드_초기화_에러_확인4() {
        assertThrows(Exception.class, () ->
                customBoard.at("a9")
        );
    }

    @Test
    void 말_이동_가능_케이스() {
        Piece queen = PieceGenerator.QUEEN.generate(PieceColor.BLACK);
        String current = "a1";
        String target = "a2";
        gameBoard = new Board(new HashMap<Tile, Piece>() {{
            put(Tile.of(current), queen);
        }});

        assertDoesNotThrow(() -> gameBoard.order(current, target));

        assertThrows(RuntimeException.class, () -> gameBoard.at(current));
        assertThat(gameBoard.at(target)).isEqualTo(queen);
    }

    @Test
    void 말_이동_불가_케이스() {
        Piece bishop = PieceGenerator.BISHOP.generate(PieceColor.BLACK);
        String current = "a1";
        String target = "a2";
        gameBoard = new Board(new HashMap<Tile, Piece>() {{
            put(Tile.of(current), bishop);
        }});

        assertThrows(RuntimeException.class, () -> gameBoard.order(current, target));

        assertThat(gameBoard.at(current)).isEqualTo(bishop);
        assertThrows(RuntimeException.class, () -> gameBoard.at(target));
    }

    @Test
    void 점수_계산1() {
        Map<Tile, Piece> boardState = new HashMap<Tile, Piece>() {{
            put(Tile.of("a1"), PieceGenerator.QUEEN.generate(PieceColor.BLACK));
            put(Tile.of("a2"), PieceGenerator.ROOK.generate(PieceColor.WHITE));
        }};
        Board board1 = new Board(boardState);
        assertThat(board1.status(PieceColor.BLACK)).isEqualTo(9);
    }

    @Test
    void 점수_계산2() {
        Map<Tile, Piece> boardState = new HashMap<Tile, Piece>() {{
            put(Tile.of("a1"), PieceGenerator.PAWN.generate(PieceColor.BLACK));
            put(Tile.of("b1"), PieceGenerator.PAWN.generate(PieceColor.BLACK));
        }};
        Board board1 = new Board(boardState);
        assertThat(board1.status(PieceColor.BLACK)).isEqualTo(2);
    }

    @Test
    void 점수_계산3() {
        Map<Tile, Piece> boardState = new HashMap<Tile, Piece>() {{
            put(Tile.of("a1"), PieceGenerator.PAWN.generate(PieceColor.BLACK));
            put(Tile.of("a2"), PieceGenerator.PAWN.generate(PieceColor.BLACK));
        }};
        Board board1 = new Board(boardState);
        assertThat(board1.status(PieceColor.BLACK)).isEqualTo(1);
    }
}