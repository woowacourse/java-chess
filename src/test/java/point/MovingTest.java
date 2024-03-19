package point;

import java.util.List;
import model.GameBoard;
import model.Square;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import piece.Pawn;

class MovingTest {

    @Test
    @DisplayName("기물이 없는 위치가 주어졌을 때 예외가 발생한다.")
    void blankPosition() {
        //given
        GameBoard gameBoard = new GameBoard();
        gameBoard.setting();

        Moving moving = new Moving(new Position(Row.FOURTH, Column.FIFTH), new Position(Row.FIFTH, Column.FIFTH));

        //when & then
        Assertions.assertThatThrownBy(() -> moving.move(gameBoard))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("올바른 위치가 주어졌을 때 기물을 이동시킨다.")
    void movePiece() {
        //given
        GameBoard gameBoard = new GameBoard();
        gameBoard.setting();

        Position pawnPosition = new Position(Row.SEVENTH, Column.FIRST);
        Position nextPosition = new Position(Row.SIXTH, Column.FIRST);
        Moving moving = new Moving(pawnPosition, nextPosition);

        //when
        moving.move(gameBoard);

        var board = gameBoard.getBoard();
        for (List<Square> pieces : board) {
            System.out.println(pieces.toString());
        }

        Square movedPawn = gameBoard.findByPosition(nextPosition);

        //then
        Assertions.assertThat(gameBoard.findByPosition(nextPosition)).isEqualTo(movedPawn);
    }
}
