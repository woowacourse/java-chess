package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceType;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BoardTest {
    Map<Tile, Piece> boardState = new HashMap<Tile, Piece>() {
        {
            put(Tile.of("a1"), PieceType.QUEEN.generate(PieceColor.BLACK));
        }
    };
    Board customBoard = new Board(boardState);
    Board gameBoard;

    @Test
    void 보드_초기화_테스트1() {
        assertThat(customBoard.at("a1"))
                .isEqualTo(Optional.of(PieceType.QUEEN.generate(PieceColor.BLACK)));
    }

    @Test
    void 보드_초기화_테스트2() {
        assertThat(customBoard.at("a2"))
                .isEqualTo(Optional.empty());
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
        Piece queen = PieceType.QUEEN.generate(PieceColor.BLACK);
        String current = "a1";
        String target = "a2";
        gameBoard = new Board(new HashMap<Tile, Piece>() {{
                put(Tile.of(current), queen);
        }});

        assertDoesNotThrow(() -> gameBoard.order(current, target));

        assertThat(gameBoard.at(current)).isEqualTo(Optional.empty());
        assertThat(gameBoard.at(target)).isEqualTo(Optional.of(queen));
    }

    @Test
    void 말_이동_불가_케이스() {
        Piece bishop = PieceType.BISHOP.generate(PieceColor.BLACK);
        String current = "a1";
        String target = "a2";
        gameBoard = new Board(new HashMap<Tile, Piece>() {{
            put(Tile.of(current), bishop);
        }});

        assertThrows(RuntimeException.class, () -> gameBoard.order(current, target));

        assertThat(gameBoard.at(current)).isEqualTo(Optional.of(bishop));
        assertThat(gameBoard.at(target)).isEqualTo(Optional.empty());
    }
}