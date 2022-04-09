package chess.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Result;
import chess.domain.piece.Bishop;
import chess.domain.piece.BlackPawn;
import chess.domain.piece.Knight;

import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import chess.domain.piece.WhitePawn;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultTest {
    @DisplayName("생성 후 값 확인")
    @Test
    void test() {
        Map<Position, Piece> board = new HashMap<>();
        board.put(new Position(Column.A, Row.EIGHT), new Rook(Team.BLACK)); // 5.0
        board.put(new Position(Column.B, Row.EIGHT), new Knight(Team.BLACK)); // 2.5
        board.put(new Position(Column.C, Row.EIGHT), new Bishop(Team.BLACK)); // 3.0

        Result result = new Result(board);
        Map<Team, Double> value = result.getValue();

        assertThat(value.get(Team.BLACK)).isEqualTo(10.5f);
    }

    @DisplayName("같은 열에 pawn 존재")
    @Test
    void test2() {
        Map<Position, Piece> board = new HashMap<>();
        board.put(new Position(Column.A, Row.ONE), new BlackPawn()); // 0.5
        board.put(new Position(Column.A, Row.TWO), new BlackPawn()); // 0.5
        board.put(new Position(Column.A, Row.THREE), new BlackPawn()); // 0.5
        board.put(new Position(Column.B, Row.THREE), new BlackPawn()); // 1.0
        board.put(new Position(Column.A, Row.FOUR), new WhitePawn()); // 1.0

        Result result = new Result(board);
        Map<Team, Double> value = result.getValue();

        assertThat(value.get(Team.BLACK)).isEqualTo(2.5f);
    }
}