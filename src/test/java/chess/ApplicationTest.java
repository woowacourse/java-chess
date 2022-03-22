package chess;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import camp.nextstep.edu.missionutils.test.NsTest;

class ApplicationTest extends NsTest {

    @Test
    @DisplayName("게임 시작 시 체스판 초기화")
    void initializeChessBoard() {
        assertSimpleTest(() -> {
            run("start");
            assertThat(output()).contains(String.format("%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s",
                    "RKBQKBKR",
                    "PPPPPPPP",
                    "........",
                    "........",
                    "........",
                    "........",
                    "pppppppp",
                    "rkbqkbkr"
            ));
        });
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}