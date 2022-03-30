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
        ChessBoard chessBoard = new ChessBoard();
        State state = new Ready();

        //when
        State start = state.execute(Command.from("start"), chessBoard);

        //then
        assertThat(start).isInstanceOf(White.class);
    }

    @DisplayName("흰팀에서 이동 후 다음 턴은 검은 팀이다.")
    @Test
    public void white() {
        //given
        ChessBoard chessBoard = new ChessBoard();

        Command start = Command.from("start");
        Command move = Command.from("move a2 a3");

        //when
        State state = new Ready().execute(start, chessBoard);

        State nextState = state.execute(move, chessBoard);

        //then
        assertThat(nextState).isInstanceOf(Black.class);
    }

    @DisplayName("end에서 종료 상태가 되는지 테스트")
    @Test
    public void end() {
        //given
        ChessBoard chessBoard = new ChessBoard();
        State state = new Ready();

        //when
        State nextState = state.execute(Command.from("end"), chessBoard);

        //then
        assertThat(nextState).isInstanceOf(End.class);
    }

    @DisplayName("검은팀에서 이동 후 다음 턴은 흰 팀이다.")
    @Test
    public void againWhite() {
        //given
        ChessBoard chessBoard = new ChessBoard();

        Command start = Command.from("start");
        Command move1 = Command.from("move a2 a3");
        Command move2 = Command.from("move a7 a6");

        //when
        State state = new Ready().execute(start, chessBoard);
        State nextState = state.execute(move1, chessBoard).execute(move2, chessBoard);

        //then
        assertThat(nextState).isInstanceOf(White.class);
    }
}
