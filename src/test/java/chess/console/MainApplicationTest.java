package chess.console;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import camp.nextstep.edu.missionutils.test.NsTest;

class MainApplicationTest extends NsTest {

    @DisplayName("게임 진행 인수 테스트")
    @Test
    void run() {
        assertSimpleTest(() -> {
            run("start\n"
                    + "move d2 d4\n"
                    + "move c7 c5\n"
                    + "move d4 c5\n"
                    + "status\n"
                    + "move d7 d6\n"
                    + "move c5 c6\n"
                    + "move d6 d5\n"
                    + "move c6 c7\n"
                    + "move d5 d4\n"
                    + "move c7 b8\n"
                    + "Rook\n"
                    + "status\n"
                    + "move d4 d3\n"
                    + "move b8 c8\n"
                    + "move d3 d2\n"
                    + "move c8 d8\n"
                    + "move a7 a5\n"
                    + "move d8 e8");
            assertThat(output()).contains(String.format("%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s",
                    "R...rBNR",
                    ".P..PPPP",
                    "........",
                    "P.......",
                    "........",
                    "........",
                    "pppPpppp",
                    "rnbqkbnr"
            ));
        });
    }

    @Override
    public void runMain() {
        MainApplication.main(new String[]{});
    }
}
