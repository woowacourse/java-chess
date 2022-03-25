package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.ChessBoard;
import chess.domain.Command;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StateTest {
    @DisplayName("start입력이 들어왔을 상태 테스트")
    @Test
    public void ready() {
        //given
        State state = new Ready();

        //when
        State start = state.start();

        //then
        assertThat(start).isInstanceOf(White.class);
    }

    @DisplayName("white에서 이동 입력이 들어왔을 상태 테스트")
    @Test
    public void white() {
        //given
        ChessBoard chessBoard = new ChessBoard();

        Command move = Command.from("move a2 a3");

        //when
        State state = new Ready().start();
        State nextState = state.changeTurn(move, chessBoard);

        //then
        assertThat(nextState).isInstanceOf(Black.class);
    }

    @DisplayName("end에서 종료 상태가 되는지 테스트")
    @Test
    public void end() {
        //given
        State state = new Ready().start();

        //when
        State nextState = state.stop();

        //then
        assertThat(nextState).isInstanceOf(End.class);
    }

    @DisplayName("white에서 이동 입력이 들어왔을 상태 테스트")
    @Test
    public void againWhite() {
        //given
        ChessBoard chessBoard = new ChessBoard();

        Command move1 = Command.from("move a2 a3");
        Command move2 = Command.from("move a7 a6");

        //when
        State state = new Ready().start();
        State nextState = state.changeTurn(move1, chessBoard).changeTurn(move2, chessBoard);

        //then
        assertThat(nextState).isInstanceOf(White.class);
    }
}
