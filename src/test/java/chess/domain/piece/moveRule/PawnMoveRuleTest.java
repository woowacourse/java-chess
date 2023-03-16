package chess.domain.piece.moveRule;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class PawnMoveRuleTest {
    @Test
    void 폰_대각선_움직임() {
        Map<Position, Piece> board = new HashMap<>();
        PawnMoveRule blackPawnMoveRule = PawnMoveRule.of(Color.BLACK);

        Position currentPosition = Position.of(File.FILE_B, Rank.RANK2);
        Piece blackPiece = new Piece(PawnMoveRule.of(Color.BLACK), Color.BLACK);
        board.put(currentPosition, blackPiece);

        Position nextPosition = Position.of(File.FILE_A, Rank.RANK1);
        Piece whitePiece = new Piece(PawnMoveRule.of(Color.WHITE), Color.WHITE);
        board.put(nextPosition, whitePiece);

        blackPawnMoveRule.move(currentPosition, nextPosition, board);

        assertThat(board.get(nextPosition)).isEqualTo(blackPiece);
    }

    @Test
    void 폰_움직임_성공_두칸이동(){
        Map<Position, Piece> board = new HashMap<>();
        PawnMoveRule blackPawnMoveRule = PawnMoveRule.of(Color.BLACK);

        Position currentPosition = Position.of(File.FILE_A, Rank.RANK7);
        Piece blackPiece = new Piece(blackPawnMoveRule, Color.BLACK); // A7에 있는 블랙 폰
        board.put(currentPosition, blackPiece);

        Position nextPosition = Position.of(File.FILE_A, Rank.RANK5);

        blackPawnMoveRule.move(currentPosition, nextPosition, board);

        assertThat(board.get(nextPosition)).isEqualTo(blackPiece);
    }
}
