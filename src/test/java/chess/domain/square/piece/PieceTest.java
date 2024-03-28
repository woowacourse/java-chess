package chess.domain.square.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.EmptySquaresMaker;
import chess.domain.position.File;
import chess.domain.position.PathFinder;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.square.Square;
import chess.domain.square.piece.unified.Rook;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {

    @DisplayName("아군은 공격할 수 없다.")
    @Test
    void canNotAttackFriendly() {
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece attacker = Rook.from(Color.BLACK);
        Piece attackedPiece = Rook.from(Color.BLACK);
        board.put(new Position(Rank.FIRST, File.A), attacker);
        board.put(new Position(Rank.SECOND, File.A), attackedPiece);
        PathFinder pathFinder = new PathFinder(new Position(Rank.FIRST, File.A), new Position(Rank.SECOND, File.A));

        final boolean canAttack = attacker.canArrive(pathFinder, board);

        assertThat(canAttack).isFalse();
    }
}
