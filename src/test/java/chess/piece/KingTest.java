package chess.piece;

import chess.board.ChessBoard;
import chess.board.Position;
import java.util.HashMap;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class KingTest {

    private ChessBoard kingTestBoard() {
        Map<Position, Piece> pieceByPosition = new HashMap<>();
        pieceByPosition.put(new Position(2, 1), new King(Team.BLACK));

        pieceByPosition.put(new Position(1, 1), new Rook(Team.BLACK));
        pieceByPosition.put(new Position(2, 2), new Rook(Team.WHITE));
        return new ChessBoard(pieceByPosition);
    }

    @Test
    void 왕은_빈칸으로_한칸_움직일_수_있다() {
        // given
        ChessBoard board = kingTestBoard();
        Position source = new Position(2, 1);
        Position destination = new Position(3, 1);

        // when & then
        Assertions.assertThat(board.canMove(source, destination))
                .isTrue();
    }

    @Test
    void 왕은_오른쪽에_있는_적_팀_기물을_공격할_수_있다() {
        // given
        ChessBoard board = kingTestBoard();
        Position source = new Position(2, 1);
        Position destination = new Position(2, 2);

        // when & then
        Assertions.assertThat(board.canAttack(source, destination))
                .isTrue();
    }

    @Test
    void 왕은_같은_팀이_있는_위치로_움직일_수_없다() {
        // given
        ChessBoard board = kingTestBoard();
        Position source = new Position(2, 1);
        Position destination = new Position(1, 1);

        // when & then
        Assertions.assertThat(board.canMove(source, destination))
                .isFalse();
    }
}