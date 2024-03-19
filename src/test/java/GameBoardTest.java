import java.util.List;
import model.GameBoard;
import model.Square;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameBoardTest {

    @DisplayName("체스판의 세로 길이는 8이다.")
    @Test
    void rowLengthIs8() {
        GameBoard gameBoard = new GameBoard();

        Assertions.assertThat(gameBoard.getBoard().size()).isEqualTo(8);
    }

    @DisplayName("체스판의 가로 길이는 8이다.")
    @Test
    void columnLengthIs8() {
        GameBoard gameBoard = new GameBoard();

        Assertions.assertThat(gameBoard.getBoard()).allMatch(line -> line.size() == 8);
    }

    @Test
    @DisplayName("Test name")
    void methodName() {
        //given

        var gameBoard = new GameBoard();

        gameBoard.setting();

        var board = gameBoard.getBoard();

        for (List<Square> list : board) {
            System.out.println(list);
        }
        //when

        //then
    }
}
