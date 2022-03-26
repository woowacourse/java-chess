package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTest {
    @DisplayName("생성 후 값 확인")
    @Test
    void test() {
        Map<Position, Piece> board = new HashMap<>();
        board.put(new Position(Column.A, Row.EIGHT), new Rook(Team.BLACK)); // 5.0
        board.put(new Position(Column.B, Row.EIGHT), new Knight(Team.BLACK)); // 2.5
        board.put(new Position(Column.C, Row.EIGHT), new Bishop(Team.BLACK)); // 3.0

        Score score = new Score(board);
        Map<Team, Double> value = score.getValue();

        System.out.println(value);
    }
}