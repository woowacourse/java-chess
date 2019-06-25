package chess.model;

import chess.model.board.BasicBoardInitializer;
import chess.model.board.Board;
import chess.model.unit.King;
import chess.model.unit.Piece;
import chess.model.unit.Side;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessGameTest {
    @Test
    void 양팀의_킹이_살아있을_경우_테스트() {
        Board board = new Board();
        board.initialize(new BasicBoardInitializer());
        ChessGame game = new ChessGame(board, Side.WHITE);
        assertThat(game.isKingAlive()).isTrue();
    }

    @Test
    void 한팀의_킹만_살아있을_경우_테스트() {
        Board board = new Board();
        board.initialize(() -> {
            Map<Square, Piece> map = new HashMap<>();
            map.put(Square.of(Column._7, Row.C), new King(Side.WHITE));
            return map;
        });
        ChessGame game = new ChessGame(board, Side.WHITE);
        assertThat(game.isKingAlive()).isFalse();
    }

    @Test
    void 한팀의_킹_두개만_살아있을_경우_테스트() {
        Board board = new Board();
        board.initialize(() -> {
            Map<Square, Piece> map = new HashMap<>();
            map.put(Square.of(Column._7, Row.C), new King(Side.WHITE));
            map.put(Square.of(Column._6, Row.C), new King(Side.WHITE));
            return map;
        });
        ChessGame game = new ChessGame(board, Side.WHITE);
        assertThat(game.isKingAlive()).isFalse();
    }
}
