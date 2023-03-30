package chessgame.state;

import chessgame.domain.Board;
import chessgame.domain.ChessBoardFactory;
import chessgame.domain.Command;
import chessgame.domain.Team;
import chessgame.domain.state.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static chessgame.point.PointFixture.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StateTest {
    @Nested
    @DisplayName("ready 상태 일 경우")
    class ReadyState {
        State state = new Ready();

        @Test
        @DisplayName("start 입력시 white 상태로 전이된다.")
        void Should_ChangeWhiteState_When_ReadyState() {
            Board board = new Board(ChessBoardFactory.create());

            State start = state.run(Command.of("start"), board);
            assertThat(start).isInstanceOf(White.class);
        }

        @Test
        @DisplayName("move 입력시 예외가 발생한다.")
        void Should_ThrowException_When_ReadyState() {
            Board board = new Board(ChessBoardFactory.create());
            assertThatThrownBy(() -> state.run(Command.of("move a2 a4"), board))
                    .isInstanceOf(UnsupportedOperationException.class)
                    .hasMessage("move를 입력할 수 없습니다.");
        }

        @Test
        @DisplayName("end 입력시 end상태로 전이된다.")
        void Should_ChangeEndState_When_ReadyState() {
            Board board = new Board(ChessBoardFactory.create());

            State start = state.run(Command.of("end"), board);
            assertThat(start).isInstanceOf(End.class);
        }

        @Test
        @DisplayName("status 입력시 end상태로 전이된다.")
        void Should_ChangeEndState_When_ReadyStateInputState() {
            Board board = new Board(ChessBoardFactory.create());
            assertThatThrownBy(() -> state.run(Command.of("status"), board))
                    .isInstanceOf(UnsupportedOperationException.class)
                    .hasMessage("status를 입력할 수 없습니다.");
        }
    }

    @Nested
    @DisplayName("white 상태 일 경우")
    class WhiteState {
        State state = new White();

        @Test
        @DisplayName("start 입력시 White상태가 유지된다.")
        void Should_ThrowException_When_WhiteState() {
            Board board = new Board(ChessBoardFactory.create());
            assertThat(state.run(Command.of("start"), board))
                    .isInstanceOf(White.class);
        }

        @Test
        @DisplayName("move 입력시 black상태로 전이된다.")
        void Should_ChangeBlackState_When_WhiteState() {
            Board board = new Board(ChessBoardFactory.create());

            State move = state.run(Command.of("move a2 a4"), board);

            assertThat(move).isInstanceOf(Black.class);
        }

        @Test
        @DisplayName("move 입력시 왕이 없다면 end상태로 전이된다.")
        void Should_ChangeEndState_When_NoKingWhiteTeam() {
            Board board = new Board(ChessBoardFactory.create());

            board.move(B1, A3, Team.WHITE);
            board.move(H7, H6, Team.BLACK);
            board.move(A3, B5, Team.WHITE);
            board.move(H8, H7, Team.BLACK);
            board.move(B5, D6, Team.WHITE);
            board.move(A7, A5, Team.BLACK);

            State move = state.run(Command.of("move d6 e8"), board);
            assertThat(move).isInstanceOf(End.class);
        }

        @Test
        @DisplayName("end 입력시 end상태로 전이된다.")
        void Should_ChangeEndState_When_WhiteState() {
            Board board = new Board(ChessBoardFactory.create());

            State start = state.run(Command.of("end"), board);
            assertThat(start).isInstanceOf(End.class);
        }

        @Test
        @DisplayName("status 입력시 상태는 전이되지 않는다.")
        void Should_NotChangeState_When_WhiteState() {
            Board board = new Board(ChessBoardFactory.create());

            State start = state.run(Command.of("status"), board);
            assertThat(start).isInstanceOf(White.class);
        }
    }

    @Nested
    @DisplayName("black 상태 일 경우")
    class BlackState {
        State state = new Black();

        @Test
        @DisplayName("start 입력시 상태가 유지된다.")
        void Should_ThrowException_When_BlackState() {
            Board board = new Board(ChessBoardFactory.create());
            assertThat(state.run(Command.of("start"), board))
                    .isInstanceOf(Black.class);
        }

        @Test
        @DisplayName("move 입력시 white상태로 전이된다.")
        void Should_ChangeWhiteState_When_BlackState() {
            Board board = new Board(ChessBoardFactory.create());

            State move = state.run(Command.of("move a7 a5"), board);

            assertThat(move).isInstanceOf(White.class);
        }

        @Test
        @DisplayName("move 입력시 왕이 없다면 end상태로 전이된다.")
        void Should_ChangeEndState_When_NoKingBlackTeam() {
            Board board = new Board(ChessBoardFactory.create());
            board.move(A2,A3, Team.WHITE);
            board.move(B8, C6, Team.BLACK);
            board.move(A3, A4, Team.WHITE);
            board.move(C6, D4, Team.BLACK);
            board.move(A4, A5, Team.WHITE);
            board.move(D4, F3, Team.BLACK);
            board.move(A5, A6, Team.WHITE);

            State move = state.run(Command.of("move f3 e1"), board);

            assertThat(move).isInstanceOf(End.class);
        }

        @Test
        @DisplayName("end 입력시 end상태로 전이된다.")
        void Should_ChangeEndState_When_BlackState() {
            Board board = new Board(ChessBoardFactory.create());

            State start = state.run(Command.of("end"), board);
            assertThat(start).isInstanceOf(End.class);
        }

        @Test
        @DisplayName("status 입력시 상태는 전이되지 않는다.")
        void Should_NotChangeState_When_BlackState() {
            Board board = new Board(ChessBoardFactory.create());

            State start = state.run(Command.of("status"), board);
            assertThat(start).isInstanceOf(Black.class);
        }
    }

    @Nested
    @DisplayName("end 상태 일 경우")
    class EndState {

        State state = new End();

        @Test
        @DisplayName("start 입력시 예외가 발생한다.")
        void Should_ThrowException_When_EndState() {
            Board board = new Board(ChessBoardFactory.create());

            assertThatThrownBy(() -> state.run(Command.of("start"), board))
                    .isInstanceOf(UnsupportedOperationException.class)
                    .hasMessage("start만 입력 가능 합니다.");
        }

        @Test
        @DisplayName("move 입력시 예외가 발생한다.")
        void Should_ThrowException2_When_EndState() {
            Board board = new Board(ChessBoardFactory.create());

            assertThatThrownBy(() -> state.run(Command.of("move b2 b4"), board))
                    .isInstanceOf(UnsupportedOperationException.class)
                    .hasMessage("start만 입력 가능 합니다.");
        }

        @Test
        @DisplayName("end 입력시 end상태로 전이된다.")
        void Should_NoChange_When_EndState() {
            Board board = new Board(ChessBoardFactory.create());

            State start = state.run(Command.of("end"), board);
            assertThat(start).isInstanceOf(End.class);
        }

        @Test
        @DisplayName("status 입력시 예외가 발생한다.")
        void Should_ThrowException3_When_EndState() {
            Board board = new Board(ChessBoardFactory.create());

            assertThatThrownBy(() -> state.run(Command.of("status"), board))
                    .isInstanceOf(UnsupportedOperationException.class)
                    .hasMessage("start만 입력 가능 합니다.");
        }
    }
}
