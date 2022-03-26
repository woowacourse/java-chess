package chess;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

class ApplicationTest extends NsTest {

    @Test
    void 게임판_생성() {
        assertSimpleTest(() -> {
            run("start", "end");
            assertThat(output()).contains(String.format("%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s",
                    "RNBQKBNR",
                    "PPPPPPPP",
                    "........",
                    "........",
                    "........",
                    "........",
                    "pppppppp",
                    "rnbqkbnr"));
        });
    }

    @Test
    void 처음_위치_폰_2칸_이동() {
        assertSimpleTest(() -> {
            run("start", "move a2 a4", "move f7 f5", "end");
            assertThat(output()).contains(String.format("%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s",
                    "RNBQKBNR",
                    "PPPPP.PP",
                    "........",
                    ".....P..",
                    "p.......",
                    "........",
                    ".ppppppp",
                    "rnbqkbnr"));
        });
    }

    @Test
    void 본인_기물이_아닌_기물_이동시_예외발생() {
        assertSimpleTest(() -> {
            assertThatThrownBy(() -> runException("start", "move a2 a4", "move f7 f5", "move f5 f4"))
                    .isInstanceOf(IllegalStateException.class);
        });
    }

    @Test
    void 현재_게임상태_점수계산() {
        assertSimpleTest(() -> {
            run("start", "move d2 d4", "move c7 c5", "move d4 c5", "status", "end");
            assertThat(output()).contains(String.format("%s%n%s",
                    "WHITE의 점수는 37.0입니다.",
                    "BLACK의 점수는 37.0입니다."
            ));
        });
    }

    @Test
    void 검은색이_잡혀_게임_종료() {
        assertSimpleTest(() -> {
            run("start",
                    "move d2 d4",
                    "move c7 c5",
                    "move d4 c5",
                    "move d7 d6",
                    "move c5 c6",
                    "move d6 d5",
                    "move c6 c7",
                    "move d5 d4",
                    "move c7 b8",
                    "R",
                    "move d4 d3",
                    "move b8 c8",
                    "move d3 d2",
                    "move c8 d8",
                    "move a7 a5",
                    "status",
                    "move d8 e8");
            assertThat(output()).contains(String.format("%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s",
                    "R...rBNR",
                    ".P..PPPP",
                    "........",
                    "P.......",
                    "........",
                    "........",
                    "pppPpppp",
                    "rnbqkbnr"),
                    "WHITE의 점수는 42.0입니다.",
                    "BLACK의 점수는 22.5입니다.",
                    "WHITE가 이겨 게임이 종료됩니다."
            );
        });
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
