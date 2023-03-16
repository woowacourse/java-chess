package chess.domain;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.moveRule.BishopMoveRule;
import chess.domain.piece.moveRule.MoveRule;
import chess.domain.piece.moveRule.RookMoveRule;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RookMoveRuleTest {

    @Test
    void 룩_움직임_실패() {
        MoveRule moveRule = RookMoveRule.getInstance();
        Map<Position, Piece> board = new HashMap<>();

        Position currentPosition = Position.of(File.FILE_A, Rank.RANK1);
        Piece startPiece = new Piece(moveRule, Color.BLACK);
        board.put(currentPosition, startPiece);

        Position nextPosition = Position.of(File.FILE_B, Rank.RANK3);
        Piece endPiece = new Piece(moveRule, Color.WHITE);
        board.put(nextPosition, endPiece);

        assertThatThrownBy(() -> moveRule.move(currentPosition, nextPosition, board))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("룩은 직선상으로만 움직일 수 있습니다.");
    }

    @Test
    void 룩_움직임_실패_중간경로에_기물_존재() {
        MoveRule moveRule = RookMoveRule.getInstance();
        Map<Position, Piece> board = new HashMap<>();

        Position currentPosition = Position.of(File.FILE_A, Rank.RANK1);
        Position nextPosition = Position.of(File.FILE_D, Rank.RANK1);

        board.put(Position.of(File.FILE_B, Rank.RANK1), new Piece(moveRule, Color.WHITE));
        board.put(currentPosition, new Piece(moveRule, Color.WHITE));
        board.put(nextPosition, new Piece(moveRule, Color.BLACK));

        assertThatThrownBy(() -> moveRule.move(currentPosition, nextPosition, board))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("경로상에 다른 기물이 있어 움직일 수 없습니다.");
    }
}