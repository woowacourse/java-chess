import java.util.List;
import model.Camp;
import model.GameBoard;
import model.Square;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import piece.Piece;
import piece.Rook;
import point.Column;
import point.Position;
import point.Row;

class GameBoardTest {

    @DisplayName("체스판의 세로 길이는 8이다.")
    @Test
    void rowLengthIs8() {
        GameBoard gameBoard = new GameBoard();

        Assertions.assertThat(gameBoard.getBoard()).hasSize(8);
    }

    @DisplayName("체스판의 가로 길이는 8이다.")
    @Test
    void columnLengthIs8() {
        GameBoard gameBoard = new GameBoard();
        List<List<Square>> board = gameBoard.getBoard();

        Assertions.assertThat(board).allMatch(line -> line.size() == 8);
    }

    @Test
    @DisplayName("기물들의 시작 위치를 확인한다.")
    void checkInitialPosition() {
        //given
        GameBoard gameBoard = new GameBoard();

        //when
        gameBoard.setting();

        List<List<Square>> board = gameBoard.getBoard();
        StringBuilder stringBuilder = new StringBuilder();

        for (List<Square> squares : board) {
            stringBuilder.append(squares.toString());
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

    @Test
    @DisplayName("위치가 주어졌을 때 해당 위치의 기물을 반환한다.")
    void findByPosition() {
        GameBoard gameBoard = new GameBoard();
        gameBoard.setting();

        Position position = new Position(Row.EIGHTH, Column.FIRST);

        Piece piece = gameBoard.findByPosition(position).getPiece();
        Rook expected = new Rook(Camp.BLACK, position);

        Assertions.assertThat(piece).isEqualTo(expected);
    }
}
