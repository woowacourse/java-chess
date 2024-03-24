package domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import java.util.List;
import org.junit.jupiter.api.Test;

class ChessGameTest {
    @Test
    void 체스_게임을_시작한다() {
        ChessGame chessGame = new ChessGame();

        chessGame.start();

        assertThat(chessGame.isRunning()).isTrue();
    }

    @Test
    void 체스_게임을_종료한다() {
        ChessGame chessGame = new ChessGame();
        chessGame.start();

        chessGame.end();

        assertThat(chessGame.isRunning()).isFalse();
    }

    @Test
    void 체스_게임을_시작하고_말을_움직인다() {
        ChessGame chessGame = new ChessGame();
        chessGame.start();

        MovePosition movePosition = new MovePosition(List.of("move", "a2", "a4"));
        chessGame.move(movePosition);

        assertThat(chessGame.getBoard().getPositionAndPieces()).containsKey(new Position(File.A, Rank.FOUR));
    }
}
