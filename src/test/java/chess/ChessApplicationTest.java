package chess;

import static camp.nextstep.edu.missionutils.test.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import camp.nextstep.edu.missionutils.test.NsTest;

public class ChessApplicationTest extends NsTest {

    private static final String NEXT_LINE = System.lineSeparator();

    @Test
    @DisplayName("입력 명령어가 예외일 경우")
    void command_exception() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("status"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 이 명령문은 존재하지 않습니다."));
    }

    @Test
    @DisplayName("입력 명령어가 start 경우")
    void command_start() {
        assertSimpleTest(() ->
            Assertions.assertDoesNotThrow(() -> runException("start"))
        );
    }

    @Test
    @DisplayName("입력 명령어가 end 경우")
    void command_end() {
        assertSimpleTest(() ->
            Assertions.assertDoesNotThrow(() -> runException("end"))
        );
    }

    @Test
    @DisplayName("체스판 초기화 출력 검증")
    void board() {
        assertSimpleTest(() -> {
            runException("start");
            assertThat(output()).contains(
                "RNBQKBNR" + NEXT_LINE
                    + "PPPPPPPP" + NEXT_LINE
                    + "........" + NEXT_LINE
                    + "........" + NEXT_LINE
                    + "........" + NEXT_LINE
                    + "........" + NEXT_LINE
                    + "pppppppp" + NEXT_LINE
                    + "rnbqkbnr");
        });
    }

    @Test
    @DisplayName("게임 시작 후 입력 명령어가 start 경우 예외를 던진다.")
    void start_after_start_exception() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("start", "start"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 이 명령문은 존재하지 않습니다."));
    }

    @Test
    @DisplayName("게임 시작 후 입력 명령어가 end인 경우 점수를 출력하고 종료한다.")
    void end_after_start_exception() {
        assertSimpleTest(() -> {
            runException("start", "end");
            assertThat(output()).contains("38.0점 입니다");
        });
    }

    @Test
    @DisplayName("게임 시작 후 입력 명령어가 status인 경우 점수를 출력한다.")
    void status_after_start_exception() {
        assertSimpleTest(() -> {
            runException("start", "status");
            assertThat(output()).contains("38.0점 입니다");
        });
    }

    @Test
    @DisplayName("게임 시작 후 move로 Pawn을 두 칸 이동시킨다.")
    void move_pawn_two_step() {
        assertSimpleTest(() -> {
            runException("start", "move a2 a4");
            assertThat(output()).contains(
                "RNBQKBNR" + NEXT_LINE
                    + "PPPPPPPP" + NEXT_LINE
                    + "........" + NEXT_LINE
                    + "........" + NEXT_LINE
                    + "p......." + NEXT_LINE
                    + "........" + NEXT_LINE
                    + ".ppppppp" + NEXT_LINE
                    + "rnbqkbnr");
        });
    }

    @Test
    @DisplayName("게임 시작 후 move 입력이 형식에 맞지 않으면 예외를 던진다.")
    void move_illegal_argument() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("start", "move a0 a4"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 이 명령문은 존재하지 않습니다."));
    }

    @Test
    @DisplayName("Pawn, Rook을 이동시켜 King을 잡고 게임을 종료시킨다.")
    void rook_capture_king() {
        assertSimpleTest(() -> {
            runException("start", "move a2 a4", "move a7 a5", "move a1 a3",
                "move h7 h5", "move a3 e3", "move h5 h4", "move e3 e7", "move h4 h3", "move e7 e8");
            assertThat(output()).contains(
                "RNBQrBNR" + NEXT_LINE
                    + ".PPP.PP." + NEXT_LINE
                    + "........" + NEXT_LINE
                    + "P......." + NEXT_LINE
                    + "p......." + NEXT_LINE
                    + ".......P" + NEXT_LINE
                    + ".ppppppp" + NEXT_LINE
                    + ".nbqkbnr").contains("38.0점 입니다.");
        });
    }

    @Override
    protected void runMain() {
        ChessApplication.main(new String[] {});
    }
}
