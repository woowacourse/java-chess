package chess.domain;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.moveRule.KingMoveRule;
import chess.domain.piece.moveRule.MoveRule;
import chess.domain.piece.moveRule.QueenMoveRule;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class KingMoveRuleTest {
    @Test
    void 킹_움직임_실패() {
        MoveRule moveRule = KingMoveRule.getInstance();
        Map<Position, Piece> board = new HashMap<>();

        Position currentPosition = Position.of(File.FILE_A, Rank.RANK1);
        Piece startPiece = new Piece(moveRule, Color.BLACK);
        board.put(currentPosition, startPiece);

        Position nextPosition = Position.of(File.FILE_A, Rank.RANK3);
        Piece endPiece = new Piece(moveRule, Color.WHITE);
        board.put(nextPosition, endPiece);

        assertThatThrownBy(() -> moveRule.move(currentPosition, nextPosition, board))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("킹은 인접한 칸으로만 이동할 수 있습니다.");
    }
}
