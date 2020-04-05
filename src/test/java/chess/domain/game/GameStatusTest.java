package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.move.Coordinate;
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
        GameStatus gameStatus = board.getGameStatus();

        board.move(MovingInfo.of(Position.of(Coordinate.of(2), Coordinate.of(6)), Position.of(Coordinate.of(3), Coordinate.of(6))));
        board.move(MovingInfo.of(Position.of(Coordinate.of(7), Coordinate.of(5)), Position.of(Coordinate.of(6), Coordinate.of(5))));
        board.move(MovingInfo.of(Position.of(Coordinate.of(1), Coordinate.of(5)), Position.of(Coordinate.of(2), Coordinate.of(6))));
        board.move(MovingInfo.of(Position.of(Coordinate.of(8), Coordinate.of(4)), Position.of(Coordinate.of(4), Coordinate.of(8))));
        board.move(MovingInfo.of(Position.of(Coordinate.of(2), Coordinate.of(6)), Position.of(Coordinate.of(3), Coordinate.of(7))));
        board.move(MovingInfo.of(Position.of(Coordinate.of(4), Coordinate.of(8)), Position.of(Coordinate.of(3), Coordinate.of(7))));
        assertThat(gameStatus.isGameEnd()).isTrue();
    }

    @Test
    @DisplayName("isGameEnd 테스트 - 게임이 끝나지 않았을 경우")
    void isGameEnd_IfNotEnd_ReturnFalse() {
        Board board = BoardFactory.createBoard();
        GameStatus gameStatus = board.getGameStatus();

        board.move(MovingInfo.of(Position.of(Coordinate.of(2), Coordinate.of(6)), Position.of(Coordinate.of(3), Coordinate.of(6))));
        board.move(MovingInfo.of(Position.of(Coordinate.of(7), Coordinate.of(5)), Position.of(Coordinate.of(6), Coordinate.of(5))));
        board.move(MovingInfo.of(Position.of(Coordinate.of(1), Coordinate.of(5)), Position.of(Coordinate.of(2), Coordinate.of(6))));
        board.move(MovingInfo.of(Position.of(Coordinate.of(8), Coordinate.of(4)), Position.of(Coordinate.of(4), Coordinate.of(8))));
        board.move(MovingInfo.of(Position.of(Coordinate.of(2), Coordinate.of(6)), Position.of(Coordinate.of(3), Coordinate.of(7))));
        assertThat(gameStatus.isGameEnd()).isFalse();
    }

    @Test
    @DisplayName("updateGameEnd 테스트")
    void updateGameEnd() {
        Board board = BoardFactory.createBoard();
        GameStatus gameStatus = board.getGameStatus();

        gameStatus.updateGameEnd();
        assertThat(gameStatus.isGameEnd()).isTrue();
    }

    @Test
    @DisplayName("getTotalScore 테스트")
    void getTotalScore() {
        Board board = BoardFactory.createBoard();
        GameStatus gameStatus = board.getGameStatus();

        board.move(MovingInfo.of(Position.of(Coordinate.of(2), Coordinate.of(4)), Position.of(Coordinate.of(4), Coordinate.of(4))));
        board.move(MovingInfo.of(Position.of(Coordinate.of(7), Coordinate.of(5)), Position.of(Coordinate.of(5), Coordinate.of(5))));
        board.move(MovingInfo.of(Position.of(Coordinate.of(4), Coordinate.of(4)), Position.of(Coordinate.of(5), Coordinate.of(5))));
        assertThat(gameStatus.getTotalScore(board)).isEqualTo(37);
    }

    @Test
    @DisplayName("getNowPlayingTeam 테스트")
    void getNowPlayingTeam() {
        Board board = BoardFactory.createBoard();
        GameStatus gameStatus = board.getGameStatus();

        board.move(MovingInfo.of(Position.of(Coordinate.of(2), Coordinate.of(4)), Position.of(Coordinate.of(4), Coordinate.of(4))));
        assertThat(gameStatus.getNowPlayingTeam()).isEqualTo(BLACK);
    }

    @Test
    @DisplayName("changePlayingTeam 테스트")
    void changePlayingTeam() {
        Board board = BoardFactory.createBoard();
        GameStatus gameStatus = board.getGameStatus();
        gameStatus.changePlayingTeam();
        assertThat(gameStatus.getNowPlayingTeam()).isEqualTo(BLACK);
    }
}
