package chess.piece;

import chess.board.ChessBoard;
import chess.board.Position;
import java.util.HashMap;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class BishopTest {

    private ChessBoard bishopTestBoard() {
        Map<Position, Piece> pieceByPosition = new HashMap<>();
        pieceByPosition.put(new Position(5, 5), new Bishop(Team.WHITE));

        pieceByPosition.put(new Position(4, 4), new Rook(Team.BLACK));
        pieceByPosition.put(new Position(3, 7), new Pawn(Team.BLACK));
        return new ChessBoard(pieceByPosition);
    }

    /*
........
........
......p.
...r....
....B...
........
........
........
    * */

    @Test
    void 비숍은_기물을_뛰어넘어서_이동하지_못한다() {
        // given
        ChessBoard board = bishopTestBoard();
        Position source = new Position(5, 5);
        Position destination = new Position(3, 3);

        // when & then
        Assertions.assertThat(board.canMove(source, destination))
                .isFalse();
    }

    @Test
    void 비숍은_막히지_않을_때까지_대각선으로_이동하여_빈칸으로_이동할_수_있다() {
        // given
        ChessBoard board = bishopTestBoard();
        Position source = new Position(5, 5);
        Position destination = new Position(8, 2);

        // when & then
        Assertions.assertThat(board.canMove(source, destination))
                .isTrue();
    }

    @Test
    void 비숍은_막히지_않을_때까지_대각선으로_이동하여_적_기물을_공격할_수_있다() {
        // given
        ChessBoard board = bishopTestBoard();
        Position source = new Position(5, 5);
        Position destination = new Position(3, 7);

        // when & then
        Assertions.assertThat(board.canAttack(source, destination))
                .isTrue();
    }
}