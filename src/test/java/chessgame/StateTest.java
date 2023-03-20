package chessgame;

import chessgame.domain.Board;
import chessgame.domain.Command;
import chessgame.domain.state.*;
import chessgame.factory.ChessBoardFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StateTest {
    @Test
    @DisplayName("start 입력시 상태 테스트")
    public void ready() {
        Board board = new Board(ChessBoardFactory.create());
        State state = new Ready();

        State start = state.click(Command.of("start"), board);

        assertThat(start).isInstanceOf(White.class);
    }

    @DisplayName("흰팀 상태 이후는 검은팀 상태 테스트")
    @Test
    public void white() {
        Board board = new Board(ChessBoardFactory.create());

        State state = new Ready();

        State start = state.click(Command.of("start"), board);
        State move = start.click(Command.of("move a2 a4"), board);

        assertThat(move).isInstanceOf(Black.class);
    }

    @DisplayName("검은팀 상태 이후는 흰 팀 상태 태스트")
    @Test
    public void black() {
        Board board = new Board(ChessBoardFactory.create());
        State state = new Ready();

        State start = state.click(Command.of("start"), board);
        State whiteMove = start.click(Command.of("move a2 a4"), board);
        State blackMove = whiteMove.click(Command.of("move a7 a5"), board);

        assertThat(blackMove).isInstanceOf(White.class);
    }

    @DisplayName("end 입력 시 종료 상태가 되는지 테스트")
    @Test
    public void end() {
        Board board = new Board(ChessBoardFactory.create());
        State state = new Ready();

        State end = state.click(Command.of("end"), board);

        assertThat(end).isInstanceOf(End.class);
    }
}
