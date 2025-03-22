package chess.piece;

import chess.board.ChessBoard;
import chess.board.Position;
import java.util.HashMap;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class KnightTest {

    private ChessBoard knightTestBoard() {
        Map<Position, Piece> pieceByPosition = new HashMap<>();
        pieceByPosition.put(new Position(5, 5), new Knight(Team.WHITE));

        pieceByPosition.put(new Position(4, 4), new Rook(Team.BLACK));
        pieceByPosition.put(new Position(3, 6), new Pawn(Team.BLACK));
        pieceByPosition.put(new Position(6, 3), new Pawn(Team.WHITE));
        return new ChessBoard(pieceByPosition);
    }
    /*
........
........
.....p..
...r....
....N...
........
...P....
........
    * */

    @Test
    void 나이트는_빈칸으로_이동할_수_있다() {
        // given
        ChessBoard board = knightTestBoard();
        Position source = new Position(5, 5);
        Position destination = new Position(3, 4);

        // when & then
        Assertions.assertThat(board.canMove(source, destination))
                .isTrue();
    }

    @Test
    void 나이트는_적_기물을_먹을_수_있다() {
        // given
        ChessBoard board = knightTestBoard();
        Position source = new Position(5, 5);
        Position destination = new Position(3, 6);

        // when & then
        Assertions.assertThat(board.canAttack(source, destination))
                .isTrue();
    }

    @Test
    void 나이트는_같은_팀_기물을_먹을_수_없다() {
        // given
        ChessBoard board = knightTestBoard();
        Position source = new Position(5, 5);
        Position destination = new Position(6, 3);

        // when & then
        Assertions.assertThat(board.canAttack(source, destination))
                .isFalse();
    }
}