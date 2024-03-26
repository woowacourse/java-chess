package domain.game;

import static org.assertj.core.api.Assertions.*;

import domain.position.Position;
import fixture.PositionFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameTest {

    @DisplayName("게임이 이미 시작했으면, start 할 수 없다.")
    @Test
    void startGame() {
        ChessGame chessGame = new ChessGame();

        chessGame.start();

        assertThatThrownBy(chessGame::start)
                .isInstanceOf(IllegalStateException.class);

    }

    @DisplayName("게임이 진행 중이 아니라면, move를 실행 할 수 없다.")
    @Test
    void move() {
        ChessGame chessGame = new ChessGame();
        Position sourcePosition = PositionFixture.createB2();
        Position targetPosition = PositionFixture.createB3();

        assertThatThrownBy(() -> chessGame.move(sourcePosition, targetPosition))
                .isInstanceOf(IllegalStateException.class);

    }

    @DisplayName("게임이 진행중이 아니라면, end 할 수 없다.")
    @Test
    void endGame() {
        ChessGame chessGame = new ChessGame();

        assertThatThrownBy(chessGame::end)
                .isInstanceOf(IllegalStateException.class);

    }
}
