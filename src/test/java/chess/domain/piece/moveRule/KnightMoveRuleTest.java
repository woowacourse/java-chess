package chess.domain.piece.moveRule;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KnightMoveRuleTest {
    @Test
    void 나이트_움직임_실패() {
        MoveRule moveRule = KnightMoveRule.getInstance();
        Map<Position, Piece> board = new HashMap<>();

        Position currentPosition = Position.of(File.FILE_A, Rank.RANK1);
        Piece startPiece = new Piece(moveRule, Color.BLACK);
        board.put(currentPosition, startPiece);

        Position nextPosition = Position.of(File.FILE_B, Rank.RANK2);
        Piece endPiece = new Piece(moveRule, Color.WHITE);
        board.put(nextPosition, endPiece);

        assertThatThrownBy(() -> moveRule.move(currentPosition, nextPosition, board))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("나이트는 L 모양으로만 움직일 수 있습니다.");
    }
}
