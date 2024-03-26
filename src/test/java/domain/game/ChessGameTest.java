package domain.game;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.coordinate.Coordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameTest {
    ChessGame chessGame;
    Coordinate start;
    Coordinate destination;

    @BeforeEach
    void setUp() {
        chessGame = new ChessGame();
    }

    @DisplayName("흰색 말 부터 이동을 시작해야 한다.")
    @Test
    void startTest() {
        // 검정 폰을 한 칸 아래로 이동한다.
        start = new Coordinate(1, 0);
        destination = new Coordinate(2, 0);

        assertThatThrownBy(() -> chessGame.startTurn(start, destination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("색상이 다른 말을 움직일 수 없습니다.");
    }

    @DisplayName("같은 색의 말을 두 번 연속 이동할 수 없다.")
    @Test
    void moveSameColorTwice() {
        // 맨 왼쪽의 흰색 폰을 두 칸 위로 전진시킨다.
        chessGame.startTurn(new Coordinate(6, 0), new Coordinate(4, 0));

        // 맨 왼쪽의 룩을 두 칸 위로 전진시킨다. 이때, 이전에 흰색 폰을 움직였으므로 흰색 룩을 움직일 수 없다.
        start = new Coordinate(7, 0);
        destination = new Coordinate(5, 0);

        assertThatThrownBy(() -> chessGame.startTurn(start, destination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("색상이 다른 말을 움직일 수 없습니다.");
    }
}
