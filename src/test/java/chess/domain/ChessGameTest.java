package chess.domain;

import chess.domain.board.GameOverException;
import chess.domain.board.InvalidMovingException;
import chess.domain.board.Tile;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceGenerator;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ChessGameTest {
    @Test
    void 말_이동_불가() {
        Map<Tile, Piece> boardState = new HashMap<Tile, Piece>() {{
            put(Tile.of("c3"), PieceGenerator.KING.generate(PieceColor.BLACK));
            put(Tile.of("a5"), PieceGenerator.QUEEN.generate(PieceColor.BLACK));
            put(Tile.of("a2"), PieceGenerator.KING.generate(PieceColor.WHITE));
        }};

        ChessGame chessGame = new ChessGame(PieceColor.BLACK, boardState);

        assertThrows(InvalidMovingException.class, () -> {
            chessGame.move("a5", "c3");
        });
    }

    @Test
    void 게임_종료() {
        Map<Tile, Piece> boardState = new HashMap<Tile, Piece>() {{
            put(Tile.of("c3"), PieceGenerator.KING.generate(PieceColor.BLACK));
            put(Tile.of("a5"), PieceGenerator.QUEEN.generate(PieceColor.BLACK));
            put(Tile.of("a2"), PieceGenerator.KING.generate(PieceColor.WHITE));
        }};

        ChessGame chessGame = new ChessGame(PieceColor.BLACK, boardState);

        assertThrows(GameOverException.class, () -> {
            chessGame.move("a5", "a2");
        });
    }

    @Test
    void status1() {
        Map<PieceColor, Double> status = new ChessGame().status();

        assertThat(status.get(PieceColor.BLACK)).isEqualTo(38);
        assertThat(status.get(PieceColor.WHITE)).isEqualTo(38);
    }

    @Test
    void status2() {
        Map<Tile, Piece> boardState = new HashMap<Tile, Piece>() {{
            put(Tile.of("c3"), PieceGenerator.KING.generate(PieceColor.BLACK));
            put(Tile.of("a5"), PieceGenerator.QUEEN.generate(PieceColor.BLACK));
            put(Tile.of("a2"), PieceGenerator.KING.generate(PieceColor.WHITE));
        }};

        Map<PieceColor, Double> status = new ChessGame(PieceColor.BLACK, boardState).status();

        assertThat(status.get(PieceColor.BLACK)).isEqualTo(9);
        assertThat(status.get(PieceColor.WHITE)).isEqualTo(0);
    }
}