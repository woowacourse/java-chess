package chess.domain;

import static chess.domain.piece.moveRule.TestFixture.A1;
import static chess.domain.piece.moveRule.TestFixture.A3;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class BoardTest {
    @Test
    void 팀_기물_점수_계산() {
        Board board = new Board();
        double score = board.calculateScoreByColor(Color.BLACK);
        assertThat(score).isEqualTo(38);
    }

    @Test
    void 세로줄에_폰이_겹칠때_팀_기물_점수_계산() {
        Map<Position, Piece> boardWhenDuplicatePawn = new HashMap<>();
        boardWhenDuplicatePawn.put(A1, Pawn.from(Color.BLACK));
        boardWhenDuplicatePawn.put(A3, Pawn.from(Color.BLACK));
        Board board = new Board(boardWhenDuplicatePawn);

        double score = board.calculateScoreByColor(Color.BLACK);
        assertThat(score).isEqualTo(1);
    }
}
