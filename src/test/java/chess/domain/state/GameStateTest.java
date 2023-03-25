package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class GameStateTest {

    private GameState state;
    private final Runnable runnable = () -> {
        int value = 4 + 3;
    };

    @Nested
    class state가_ReadyState일때 {

        @BeforeEach
        void init() {
            state = ReadyState.STATE;
        }

        @Test
        void startGame을_호출하면_정상동작한다() {
            //given

            //when
            final Executable executable = () -> state.startGame(runnable);

            //then
            assertDoesNotThrow(executable);
        }

        @Test
        void enterLoad을_호출하면_정상동작한다() {
            //given

            //when
            final Executable executable = () -> state.enterLoad(runnable);

            //then
            assertDoesNotThrow(executable);
        }

        @Test
        void loadGame을_호출하면_예외처리한다() {
            //given

            //when
            final ThrowingCallable throwingCallable = () -> state.loadGame(runnable);

            //then
            assertThatThrownBy(throwingCallable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력된 명령어가 올바르지 않습니다.");
        }

        @Test
        void cancelLoad을_호출하면_예외처리한다() {
            //given

            //when
            final ThrowingCallable throwingCallable = () -> state.cancelLoad(runnable);

            //then
            assertThatThrownBy(throwingCallable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력된 명령어가 올바르지 않습니다.");
        }

        @Test
        void movePiece을_호출하면_예외처리한다() {
            //given

            //when
            final ThrowingCallable throwingCallable = () -> state.movePiece(runnable);

            //then
            assertThatThrownBy(throwingCallable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력된 명령어가 올바르지 않습니다.");
        }

        @Test
        void displayGameStatus을_호출하면_예외처리한다() {
            //given

            //when
            final ThrowingCallable throwingCallable = () -> state.displayGameStatus(runnable);

            //then
            assertThatThrownBy(throwingCallable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력된 명령어가 올바르지 않습니다.");
        }

        @Test
        void finishGame을_호출하면_예외처리한다() {
            //given

            //when
            final ThrowingCallable throwingCallable = () -> state.finishGame(runnable);

            //then
            assertThatThrownBy(throwingCallable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력된 명령어가 올바르지 않습니다.");
        }

        @Test
        void isFinished를_호출하면_false를_반환한다() {
            //given

            //when
            boolean actual = state.isFinished();

            //then
            assertThat(actual).isFalse();
        }
    }

    @Nested
    class state가_LoadingState일때 {

        @BeforeEach
        void init() {
            state = LoadingState.STATE;
        }

        @Test
        void startGame을_호출하면_예외처리한다() {
            //given

            //when
            final ThrowingCallable throwingCallable = () -> state.startGame(runnable);

            //then
            assertThatThrownBy(throwingCallable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력된 명령어가 올바르지 않습니다.");
        }

        @Test
        void enterLoad을_호출하면_예외처리한다() {
            //given

            //when
            final ThrowingCallable throwingCallable = () -> state.enterLoad(runnable);

            //then
            assertThatThrownBy(throwingCallable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력된 명령어가 올바르지 않습니다.");
        }

        @Test
        void loadGame을_호출하면_정상동작한다() {
            //given

            //when
            final Executable executable = () -> state.loadGame(runnable);

            //then
            assertDoesNotThrow(executable);
        }

        @Test
        void cancelLoad을_호출하면_정상동작한다() {
            //given

            //when
            final Executable executable = () -> state.cancelLoad(runnable);

            //then
            assertDoesNotThrow(executable);
        }

        @Test
        void movePiece을_호출하면_예외처리한다() {
            //given

            //when
            final ThrowingCallable throwingCallable = () -> state.movePiece(runnable);

            //then
            assertThatThrownBy(throwingCallable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력된 명령어가 올바르지 않습니다.");
        }

        @Test
        void displayGameStatus을_호출하면_예외처리한다() {
            //given

            //when
            final ThrowingCallable throwingCallable = () -> state.displayGameStatus(runnable);

            //then
            assertThatThrownBy(throwingCallable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력된 명령어가 올바르지 않습니다.");
        }

        @Test
        void finishGame을_호출하면_예외처리한다() {
            //given

            //when
            final ThrowingCallable throwingCallable = () -> state.finishGame(runnable);

            //then
            assertThatThrownBy(throwingCallable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력된 명령어가 올바르지 않습니다.");
        }

        @Test
        void isFinished를_호출하면_false를_반환한다() {
            //given

            //when
            boolean actual = state.isFinished();

            //then
            assertThat(actual).isFalse();
        }
    }

    @Nested
    class state가_RunningState일때 {

        @BeforeEach
        void init() {
            state = RunningState.STATE;
        }

        @Test
        void startGame을_호출하면_예외처리한다() {
            //given

            //when
            final ThrowingCallable throwingCallable = () -> state.startGame(runnable);

            //then
            assertThatThrownBy(throwingCallable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력된 명령어가 올바르지 않습니다.");
        }

        @Test
        void enterLoad을_호출하면_예외처리한다() {
            //given

            //when
            final ThrowingCallable throwingCallable = () -> state.enterLoad(runnable);

            //then
            assertThatThrownBy(throwingCallable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력된 명령어가 올바르지 않습니다.");
        }

        @Test
        void loadGame을_호출하면_예외처리한다() {
            //given

            //when
            final ThrowingCallable throwingCallable = () -> state.loadGame(runnable);

            //then
            assertThatThrownBy(throwingCallable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력된 명령어가 올바르지 않습니다.");
        }

        @Test
        void cancelLoad을_호출하면_예외처리한다() {
            //given

            //when
            final ThrowingCallable throwingCallable = () -> state.cancelLoad(runnable);

            //then
            assertThatThrownBy(throwingCallable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력된 명령어가 올바르지 않습니다.");
        }

        @Test
        void movePiece을_호출하면_정상동작한다() {
            //given

            //when
            final Executable executable = () -> state.movePiece(runnable);

            //then
            assertDoesNotThrow(executable);
        }

        @Test
        void displayGameStatus을_호출하면_정상동작한다() {
            //given

            //when
            final Executable executable = () -> state.displayGameStatus(runnable);

            //then
            assertDoesNotThrow(executable);
        }

        @Test
        void finishGame을_호출하면_정상동작한다() {
            //given

            //when
            final Executable executable = () -> state.finishGame(runnable);

            //then
            assertDoesNotThrow(executable);
        }

        @Test
        void isFinished를_호출하면_false를_반환한다() {
            //given

            //when
            boolean actual = state.isFinished();

            //then
            assertThat(actual).isFalse();
        }
    }

    @Nested
    class state가_FinishedState일때 {

        @BeforeEach
        void init() {
            state = FinishedState.STATE;
        }

        @Test
        void startGame을_호출하면_예외처리한다() {
            //given

            //when
            final ThrowingCallable throwingCallable = () -> state.startGame(runnable);

            //then
            assertThatThrownBy(throwingCallable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력된 명령어가 올바르지 않습니다.");
        }

        @Test
        void enterLoad을_호출하면_예외처리한다() {
            //given

            //when
            final ThrowingCallable throwingCallable = () -> state.enterLoad(runnable);

            //then
            assertThatThrownBy(throwingCallable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력된 명령어가 올바르지 않습니다.");
        }

        @Test
        void loadGame을_호출하면_예외처리한다() {
            //given

            //when
            final ThrowingCallable throwingCallable = () -> state.loadGame(runnable);

            //then
            assertThatThrownBy(throwingCallable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력된 명령어가 올바르지 않습니다.");
        }

        @Test
        void cancelLoad을_호출하면_예외처리한다() {
            //given

            //when
            final ThrowingCallable throwingCallable = () -> state.cancelLoad(runnable);

            //then
            assertThatThrownBy(throwingCallable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력된 명령어가 올바르지 않습니다.");
        }

        @Test
        void movePiece을_호출하면_예외처리한다() {
            //given

            //when
            final ThrowingCallable throwingCallable = () -> state.movePiece(runnable);

            //then
            assertThatThrownBy(throwingCallable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력된 명령어가 올바르지 않습니다.");
        }

        @Test
        void displayGameStatus을_호출하면_예외처리한다() {
            //given

            //when
            final ThrowingCallable throwingCallable = () -> state.displayGameStatus(runnable);

            //then
            assertThatThrownBy(throwingCallable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력된 명령어가 올바르지 않습니다.");
        }

        @Test
        void finishGame을_호출하면_예외처리한다() {
            //given

            //when
            final ThrowingCallable throwingCallable = () -> state.finishGame(runnable);

            //then
            assertThatThrownBy(throwingCallable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력된 명령어가 올바르지 않습니다.");
        }

        @Test
        void isFinished를_호출하면_true를_반환한다() {
            //given

            //when
            boolean actual = state.isFinished();

            //then
            assertThat(actual).isTrue();
        }
    }
}