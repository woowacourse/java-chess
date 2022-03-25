package chess.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.board.Board;
import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.domain.board.strategy.CreateMockBoardStrategy;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;

@DisplayName("Status 테스트")
class StatusTest {

    @Test
    @DisplayName("킹이 없으면 -1점을 반환한다")
    void check_Score_Without_King() {
        Board board = new Board(new CreateMockBoardStrategy(new HashMap<>()));

        Status status = new Status(board);

        assertThat(status.getBlackScore()).isEqualTo(-1);
    }

    @Test
    @DisplayName("킹이 있으면 남은 말의 점수를 반환한다")
    void check_Score_With_King() {
        Piece king = new King(Color.BLACK);
        Piece queen = new Queen(Color.BLACK);
        Position p1 = new Position(Row.FIRST, Column.a);
        Position p2 = new Position(Row.THIRD, Column.a);
        Map<Position, Piece> pieces = Map.of(p1, king, p2, queen);
        Board board = new Board(new CreateMockBoardStrategy(pieces));

        Status status = new Status(board);

        assertThat(status.getBlackScore()).isEqualTo(9);
    }
}
