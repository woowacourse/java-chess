package chessgame;

import chessgame.domain.Board;
import chessgame.domain.Command;
import chessgame.domain.state.*;
import chessgame.factory.ChessBoardFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StateTest {
    @Nested
    @DisplayName("ready 상태 일 경우")
    class ReadyState {
        State state = new Ready();
        @Test
        @DisplayName("start 입력시 상태 테스트")
        void Should_ChangeWhiteState_When_ReadyState() {
            Board board = new Board(ChessBoardFactory.create());

            State start = state.run(Command.of("start"), board);
            assertThat(start).isInstanceOf(White.class);
        }

        @Test
        @DisplayName("move 입력시 상태 테스트")
        void Should_ThrowException_When_ReadyState() {
            Board board = new Board(ChessBoardFactory.create());
            assertThatThrownBy(() -> state.run(Command.of("move a2 a4"), board))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("move를 입력할 수 없습니다.");
        }

        @Test
        @DisplayName("end 입력시 상태 테스트")
        void Should_ChangeEndState_When_ReadyState() {
            Board board = new Board(ChessBoardFactory.create());

            State start = state.run(Command.of("end"), board);
            assertThat(start).isInstanceOf(End.class);
        }
    }

    @Nested
    @DisplayName("white 상태 일 경우")
    class WhiteState {
        State state = new White();
        @Test
        @DisplayName("start 입력시 상태 테스트")
        void Should_ThrowException_When_WhiteState() {
            Board board = new Board(ChessBoardFactory.create());
            assertThatThrownBy(() -> state.run(Command.of("start"), board))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("start를 입력할 수 없습니다.");
        }

        @Test
        @DisplayName("move 입력시 상태 테스트")
        void Should_ChangeBlackState_When_WhiteState() {
            Board board = new Board(ChessBoardFactory.create());

            State move = state.run(Command.of("move a2 a4"), board);

            assertThat(move).isInstanceOf(Black.class);
        }

        @Test
        @DisplayName("end 입력시 상태 테스트")
        void Should_ChangeEndState_When_WhiteState() {
            Board board = new Board(ChessBoardFactory.create());

            State start = state.run(Command.of("end"), board);
            assertThat(start).isInstanceOf(End.class);
        }
    }

    @Nested
    @DisplayName("black 상태 일 경우")
    class BlackState {
        State state = new Black();
        @Test
        @DisplayName("start 입력시 상태 테스트")
        void Should_ThrowException_When_BlackState() {
            Board board = new Board(ChessBoardFactory.create());
            assertThatThrownBy(() -> state.run(Command.of("start"), board))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("start를 입력할 수 없습니다.");
        }

        @Test
        @DisplayName("move 입력시 상태 테스트")
        void Should_ChangeWhiteState_When_BlackState() {
            Board board = new Board(ChessBoardFactory.create());

            State move = state.run(Command.of("move a7 a5"), board);

            assertThat(move).isInstanceOf(White.class);
        }

        @Test
        @DisplayName("end 입력시 상태 테스트")
        void Should_ChangeEndState_When_BlackState() {
            Board board = new Board(ChessBoardFactory.create());

            State start = state.run(Command.of("end"), board);
            assertThat(start).isInstanceOf(End.class);
        }
    }

    @Nested
    @DisplayName("end 상태 일 경우")
    class EndState {

        State state = new End();
        @Test
        @DisplayName("start 입력시 상태 테스트")
        void Should_ThrowException_When_EndState() {
            Board board = new Board(ChessBoardFactory.create());

            assertThatThrownBy(() -> state.run(Command.of("start"), board))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("start만 입력 가능 합니다.");
        }

        @Test
        @DisplayName("move 입력시 상태 테스트")
        void Should_ThrowException2_When_EndState() {
            Board board = new Board(ChessBoardFactory.create());

            assertThatThrownBy(() -> state.run(Command.of("move b2 b4"), board))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("start만 입력 가능 합니다.");
        }

        @Test
        @DisplayName("end 입력시 상태 테스트")
        void Should_NoChange_When_EndState() {
            Board board = new Board(ChessBoardFactory.create());

            State start = state.run(Command.of("end"), board);
            assertThat(start).isInstanceOf(End.class);
        }
    }
}
