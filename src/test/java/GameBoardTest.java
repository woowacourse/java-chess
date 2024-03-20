import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import model.GameBoard;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import piece.Piece;
import point.Position;

class GameBoardTest {

    @Test
    @DisplayName("초기에는 32개의 기물이 생성된다.")
    void initPieces() {
        //given
        GameBoard gameBoard = new GameBoard();

        //when
        gameBoard.setting();

        //then
        var board = gameBoard.getBoard();

        Assertions.assertThat(board.keySet()).hasSize(32);

    }

    @Test
    @DisplayName("기물들의 시작 위치를 확인한다.")
    void checkInitialPosition() {
        //given
        GameBoard gameBoard = new GameBoard();

        //when
        gameBoard.setting();

        Map<Position, Piece> board = gameBoard.getBoard();
        StringBuilder stringBuilder = new StringBuilder();

        String[][] res = new String[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                res[i][j] = ".";
            }
        }

        for (Entry<Position, Piece> entry : board.entrySet()) {
            res[entry.getKey().getRow().getIndex()][entry.getKey().getColumn().getIndex()] = entry.getValue()
                    .toString();
        }

        for (String[] ans : res) {
            stringBuilder.append(Arrays.toString(ans));
            stringBuilder.append(System.lineSeparator());
        }

        String expected = String.format("[R, N, B, Q, K, B, N, R]%n"
                + "[P, P, P, P, P, P, P, P]%n"
                + "[., ., ., ., ., ., ., .]%n"
                + "[., ., ., ., ., ., ., .]%n"
                + "[., ., ., ., ., ., ., .]%n"
                + "[., ., ., ., ., ., ., .]%n"
                + "[p, p, p, p, p, p, p, p]%n"
                + "[r, n, b, q, k, b, n, r]%n");

        //then
        Assertions.assertThat(stringBuilder.toString()).hasToString(expected);
    }
}
