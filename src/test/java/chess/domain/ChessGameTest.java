package chess.domain;

import chess.exception.UnmovableException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ChessGameTest {
    private ChessGame chessGame;

    @BeforeEach
    void setUp() {
        ChessBoard chessBoard = ChessInitialPosition.generateChessBoard();
        chessGame = new ChessGame(chessBoard);
    }

    @Test
    void 상대_말을_이동하려는_경우() {
        assertThrows(UnmovableException.class, () ->
                chessGame.move(Position.getPosition(2, 7), Position.getPosition(2, 6)));
    }

    @Test
    void 말이_없는데_이동하려는_경우() {
        assertThrows(UnmovableException.class, () ->
                chessGame.move(Position.getPosition(1, 3), Position.getPosition(2, 5)));
    }

    @Test
    void 도착지점에_내말이_있는_경우() {
        assertThrows(UnmovableException.class, () ->
                chessGame.move(Position.getPosition(1, 8), Position.getPosition(1, 7)));
    }

    @Test
    void 경로에_장애물이_있는_경우() {
        assertThrows(UnmovableException.class, () ->
                chessGame.move(Position.getPosition(1, 1), Position.getPosition(1, 5)));
    }

    @Test
    void 상대_말을_잡는_경우() {
        chessGame.move(Position.getPosition(1, 2), Position.getPosition(1, 4));
        chessGame.move(Position.getPosition(2, 7), Position.getPosition(2, 5));
        chessGame.move(Position.getPosition(1, 4), Position.getPosition(2, 5));

        assertThat(chessGame.getPlayerScore(Player.WHITE)).isEqualTo(new Score(37));
        assertThat(chessGame.getPlayerScore(Player.BLACK)).isEqualTo(new Score(37));
        assertThat(chessGame.findWinner()).isEqualTo(Result.DRAW);
    }

    @Test
    void 흑이_이기는_경우() {
        chessGame.move(Position.getPosition(1, 2), Position.getPosition(1, 4));
        chessGame.move(Position.getPosition(2, 7), Position.getPosition(2, 5));
        chessGame.move(Position.getPosition(1, 4), Position.getPosition(2, 5));
        chessGame.move(Position.getPosition(1, 7), Position.getPosition(1, 6));
        chessGame.move(Position.getPosition(1, 1), Position.getPosition(1, 6));
        chessGame.move(Position.getPosition(1, 8), Position.getPosition(1, 6));

        assertThat(chessGame.getPlayerScore(Player.WHITE)).isEqualTo(new Score(32));
        assertThat(chessGame.getPlayerScore(Player.BLACK)).isEqualTo(new Score(36));
        assertThat(chessGame.findWinner()).isEqualTo(Result.BLACK_WIN);
    }
}
