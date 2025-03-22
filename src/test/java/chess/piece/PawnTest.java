package chess.piece;

import chess.board.ChessBoard;
import chess.board.Position;
import java.util.HashMap;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class PawnTest {

    private ChessBoard pawnTestBoard() {
        Map<Position, Piece> pieceByPosition = new HashMap<>();
        pieceByPosition.put(new Position(7, 4), new Pawn(Team.BLACK));
        pieceByPosition.put(new Position(7, 8), new Pawn(Team.BLACK));

        pieceByPosition.put(new Position(6, 3), new Pawn(Team.WHITE));
        pieceByPosition.put(new Position(6, 4), new Knight(Team.WHITE));
        pieceByPosition.put(new Position(6, 5), new Pawn(Team.BLACK));
        return new ChessBoard(pieceByPosition);
    }

    @Test
    void 폰은_기물을_뛰어넘어서_이동하지_못한다() {
        // given
        ChessBoard board = pawnTestBoard();
        Position source = new Position(7, 4);
        Position destination = new Position(5, 4);

        // when & then
        Assertions.assertThat(board.canMove(source, destination))
                .isFalse();
    }

    @Test
    void 폰은_같은_팀_기물을_공격할_수_없다() {
        // given
        ChessBoard board = pawnTestBoard();
        Position source = new Position(7, 4);
        Position destination = new Position(6, 5);

        // when & then
        Assertions.assertThat(board.canAttack(source, destination))
                .isFalse();
    }

    @Test
    void 폰은_적_팀_기물을_공격할_수_있다() {
        // given
        ChessBoard board = pawnTestBoard();
        Position source = new Position(7, 4);
        Position destination = new Position(6, 3);

        // when & then
        Assertions.assertThat(board.canAttack(source, destination))
                .isTrue();
    }

    @Test
    void 폰은_첫_움직임_시에_앞으로_두칸_이동할_수_있다() {
        // given
        ChessBoard board = pawnTestBoard();
        Position source = new Position(7, 8);
        Position destination = new Position(5, 8);

        // when & then
        Assertions.assertThat(board.canMove(source, destination))
                .isTrue();
    }

    @Test
    void 폰은_첫_움직임이_아니면_앞으로_두칸_이동할_수_없다() {
        // given
        ChessBoard board = pawnTestBoard();
        Position source1 = new Position(7, 8);
        Position destination1 = new Position(5, 8);
        board.move(source1, destination1);
        Position destination2 = new Position(3, 8);

        // when & then
        Assertions.assertThat(board.canMove(destination1, destination2))
                .isFalse();
    }
}