package chess.domain.state;

import chess.domain.MoveParameter;
import chess.domain.board.Board;
import chess.domain.board.initializer.EnumRepositoryBoardInitializer;
import chess.domain.game.ChessGame;
import chess.domain.game.Turn;
import chess.domain.player.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EndStateTest {

    EndState endState;

    @BeforeEach
    void setUp() {
        ChessGame chessGame = new ChessGame(Board.of(new EnumRepositoryBoardInitializer()), Turn.from(Team.WHITE));
        endState = new EndState(chessGame, Team.WHITE);
    }

    @Test
    @DisplayName("EndState는 기존의 board를 가지고 게임을 다시 시작")
    void start() {
        assertThat(endState.start(Arrays.asList("new")))
                .isInstanceOf(RunningState.class);
    }

    @Test
    @DisplayName("EndState는 move 메서드를 지원하지 않음")
    void move() {
        assertThatThrownBy(() -> endState.move(MoveParameter.of(Arrays.asList("B1", "A3"))))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("EndState는 end 메서드를 지원하지 않음")
    void end() {
        assertThatThrownBy(() -> endState.end(new ArrayList<>()))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("EndState는 getBoard 메서드를 지원하지 않음")
    void getBoard() {
        assertThat(endState.getChessGame())
                .isInstanceOf(ChessGame.class);
    }
}