package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Position;
import chess.domain.state.GameState;
import chess.domain.state.WhiteTurn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {


    @DisplayName("대각선 직선 이동 시 중간에 기물이 존재한다면 이동할 수 없다.")
    @Test
    void diagonalStraightMove_Obstacle_Fails() {
        Board board = new Board(BoardInitializer.initBoard());

        assertThatThrownBy(() -> board.movePiece(new Position("c1"), new Position("a3")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("경로에 기물이 존재합니다.");
    }

    @DisplayName("대각선으로 이동하려는 위치에 아군 말이 존재하면 예외가 발생한다.")
    @Test
    void diagonalStraightMove_ObstacleAlly_Fails() {
        Board board = new Board(BoardInitializer.initBoard());

        assertThatThrownBy(() -> board.movePiece(new Position("c1"), new Position("b2")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동이 불가능한 위치입니다.");
    }

    @DisplayName("세로로 직선 이동 시 중간에 기물이 존재한다면 이동할 수 없다.")
    @Test
    void verticalStraightMove_Obstacle_Fails() {
        Board board = new Board(BoardInitializer.initBoard());

        assertThatThrownBy(() -> board.movePiece(new Position("a1"), new Position("a3")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("경로에 기물이 존재합니다.");
    }

    @DisplayName("세로로 이동하려는 위치에 아군 말이 존재하면 예외가 발생한다.")
    @Test
    void verticalStraightMove_ObstacleAlly_Fails() {
        Board board = new Board(BoardInitializer.initBoard());

        assertThatThrownBy(() -> board.movePiece(new Position("a1"), new Position("a2")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동이 불가능한 위치입니다.");
    }

    @DisplayName("가로로 직선 이동 시 중간에 기물이 존재한다면 이동할 수 없다.")
    @Test
    void horizontalStraightMove_Obstacle_Fails() {
        Board board = new Board(BoardInitializer.initBoard());

        assertThatThrownBy(() -> board.movePiece(new Position("a1"), new Position("c1")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("경로에 기물이 존재합니다.");
    }

    @DisplayName("가로로 이동하려는 위치에 아군 말이 존재하면 예외가 발생한다.")
    @Test
    void horizontalStraightMove_ObstacleAlly_Fails() {
        Board board = new Board(BoardInitializer.initBoard());

        assertThatThrownBy(() -> board.movePiece(new Position("a1"), new Position("b1")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동이 불가능한 위치입니다.");
    }

    @DisplayName("게임이 종료되었을 때 흑팀의 남은 말들을 가지고 점수를 계산한다.")
    @Test
    void calculateBlackScore() {
        GameState state = new WhiteTurn(new Board(BoardInitializer.initBoard()));

        state = state.move(new Position("b2"), new Position("b4"));
        state = state.move(new Position("a7"), new Position("a5"));
        state = state.move(new Position("b4"), new Position("a5"));
        state = state.terminate();

        assertThat(state.getBoard().calculateBlackScore()).isEqualTo(37);
    }

    @DisplayName("게임이 종료되었을 때 백팀의 남은 말들을 가지고 점수를 계산한다.")
    @Test
    void calculateWhiteScore() {
        GameState state = new WhiteTurn(new Board(BoardInitializer.initBoard()));

        state = state.move(new Position("b2"), new Position("b4"));
        state = state.move(new Position("a7"), new Position("a5"));
        state = state.move(new Position("b4"), new Position("a5"));
        state = state.terminate();

        assertThat(state.getBoard().calculateWhiteScore()).isEqualTo(37);
    }
}
