package chess.domain.state;

import chess.domain.ChessBoard;
import chess.domain.Command;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ReadyTest {
    @DisplayName("start입력이 들어왔을 상태 테스트")
    @Test
    public void ready() {
        //given
        ChessBoard chessBoard = new ChessBoard();
        Command command = Command.from("start");

        //when
        State state = new Ready().start();

        //then
        Assertions.assertThat(state).isInstanceOf(White.class);
    }

    @DisplayName("white에서 이동 입력이 들어왔을 상태 테스트")
    @Test
    public void white() {
        //given
        ChessBoard chessBoard = new ChessBoard();
        Command start = Command.from("start");
        Command move = Command.from("move a1 a2");

        //when
        State state = new Ready().start();
        State nextState = state.changeTurn(move, chessBoard);

        //then
        Assertions.assertThat(nextState).isInstanceOf(Black.class);
    }

    @DisplayName("end에서 종료 상태가 되는지 테스트")
    @Test
    public void end() {
        //given
        ChessBoard chessBoard = new ChessBoard();
        Command start = Command.from("start");
        Command end = Command.from("end");

        //when
        State state = new Ready().start();
        State nextState = state.changeTurn(end, chessBoard);

        //then
        Assertions.assertThat(nextState).isInstanceOf(End.class);
    }

    @DisplayName("white에서 이동 입력이 들어왔을 상태 테스트")
    @Test
    public void againWhite() {
        //given
        ChessBoard chessBoard = new ChessBoard();
        Command start = Command.from("start");
        Command move1 = Command.from("move a1 a2");
        Command move2 = Command.from("move a7 a5");

        //when
        State state = new Ready().start();
        State nextState = state.changeTurn(move1, chessBoard).changeTurn(move2, chessBoard);

        //then
        Assertions.assertThat(nextState).isInstanceOf(White.class);
    }
}
