package chess.domain.board.state;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.piece.Position;
import java.util.HashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EndTest {

    @DisplayName("게임이 종료되었는지 확인한다.")
    @Test
    void isEnd() {
        BlackWin blackWin = new BlackWin(new Board(BoardInitializer.initBoard()));
        WhiteWin whiteWin = new WhiteWin(new Board(BoardInitializer.initBoard()));

        assertThat(blackWin.isEnd()).isTrue();
        assertThat(whiteWin.isEnd()).isTrue();
    }

    @DisplayName("게임이 종료되었을 때 흑팀의 남은 말들을 가지고 점수를 계산한다.")
    @Test
    void calculateBlackScore() {
        GameState state = new WhiteTurn(new Board(BoardInitializer.initBoard()));
        state = state.move(new Position("b2"), new Position("b4"));
        state = state.move(new Position("a7"), new Position("a5"));
        state = state.move(new Position("b4"), new Position("a5"));
        state = state.terminate();

        assertThat(state.calculateBlackScore()).isEqualTo(37);
    }

    @DisplayName("게임이 종료되었을 때 백팀의 남은 말들을 가지고 점수를 계산한다.")
    @Test
    void calculateWhiteScore() {
        GameState state = new WhiteTurn(new Board(BoardInitializer.initBoard()));
        state = state.move(new Position("b2"), new Position("b4"));
        state = state.move(new Position("a7"), new Position("a5"));
        state = state.move(new Position("b4"), new Position("a5"));
        state = state.terminate();

        assertThat(state.calculateWhiteScore()).isEqualTo(37);
    }
}
