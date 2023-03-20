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
    void ready() {
        Board board = new Board(ChessBoardFactory.create());
        State state = new Ready();

        State start = state.run(Command.of("start"), board);

        assertThat(start).isInstanceOf(White.class);
    }

    @Test
    @DisplayName("흰팀 상태 이후는 검은팀 상태 테스트")
    void white() {
        Board board = new Board(ChessBoardFactory.create());

        State state = new Ready();

        State start = state.run(Command.of("start"), board);
        State move = start.run(Command.of("move a2 a4"), board);

        assertThat(move).isInstanceOf(Black.class);
    }

    @Test
    @DisplayName("검은팀 상태 이후는 흰 팀 상태 태스트")
    void black() {
        Board board = new Board(ChessBoardFactory.create());
        State state = new Ready();

        State start = state.run(Command.of("start"), board);
        State whiteMove = start.run(Command.of("move a2 a4"), board);
        State blackMove = whiteMove.run(Command.of("move a7 a5"), board);

        assertThat(blackMove).isInstanceOf(White.class);
    }

    @Test
    @DisplayName("end 입력 시 종료 상태가 되는지 테스트")
    void end() {
        Board board = new Board(ChessBoardFactory.create());
        State state = new Ready();

        State end = state.run(Command.of("end"), board);

        assertThat(end).isInstanceOf(End.class);
    }
}
