package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.move.MovingInfo;
import chess.domain.move.Position;
import chess.factory.BoardFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.game.Team.BLACK;
import static org.assertj.core.api.Assertions.assertThat;

public class GameStatusTest {
    @Test
    @DisplayName("isGameEnd 테스트 - 게임이 끝났을 경우")
    void isGameEnd() {
        Board board = BoardFactory.createBoard();
        GameStatus.initialize();

        board.move(new MovingInfo(Position.of(2, 6), Position.of(3, 6)));
        board.move(new MovingInfo(Position.of(7, 5), Position.of(6, 5)));
        board.move(new MovingInfo(Position.of(1, 5), Position.of(2, 6)));
        board.move(new MovingInfo(Position.of(8, 4), Position.of(4, 8)));
        board.move(new MovingInfo(Position.of(2, 6), Position.of(3, 7)));
        board.move(new MovingInfo(Position.of(4, 8), Position.of(3, 7)));
        assertThat(GameStatus.isGameEnd()).isTrue();
    }

    @Test
    @DisplayName("isGameEnd 테스트 - 게임이 끝나지 않았을 경우")
    void isGameEnd_IfNotEnd_ReturnFalse() {
        Board board = BoardFactory.createBoard();
        GameStatus.initialize();

        board.move(new MovingInfo(Position.of(2, 6), Position.of(3, 6)));
        board.move(new MovingInfo(Position.of(7, 5), Position.of(6, 5)));
        board.move(new MovingInfo(Position.of(1, 5), Position.of(2, 6)));
        board.move(new MovingInfo(Position.of(8, 4), Position.of(4, 8)));
        board.move(new MovingInfo(Position.of(2, 6), Position.of(3, 7)));
        assertThat(GameStatus.isGameEnd()).isFalse();
    }

    @Test
    @DisplayName("updateGameEnd 테스트")
    void updateGameEnd() {
        GameStatus.initialize();
        GameStatus.updateGameEnd();
        assertThat(GameStatus.isGameEnd()).isTrue();
    }

    @Test
    @DisplayName("getTotalScore 테스트")
    void getTotalScore() {
        Board board = BoardFactory.createBoard();
        GameStatus.initialize();

        board.move(new MovingInfo(Position.of(2, 4), Position.of(4, 4)));
        board.move(new MovingInfo(Position.of(7, 5), Position.of(5, 5)));
        board.move(new MovingInfo(Position.of(4, 4), Position.of(5, 5)));
        assertThat(GameStatus.getTotalScore(board)).isEqualTo(37);
    }

    @Test
    @DisplayName("getNowPlayingTeam 테스트")
    void getNowPlayingTeam() {
        Board board = BoardFactory.createBoard();
        GameStatus.initialize();

        board.move(new MovingInfo(Position.of(2, 4), Position.of(4, 4)));
        assertThat(GameStatus.getNowPlayingTeam()).isEqualTo(BLACK);
    }

    @Test
    @DisplayName("changePlayingTeam 테스트")
    void changePlayingTeam() {
        GameStatus.initialize();
        GameStatus.changePlayingTeam();
        assertThat(GameStatus.getNowPlayingTeam()).isEqualTo(BLACK);
    }
}
