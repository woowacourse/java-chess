package chess.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.board.strategy.CreateMockBoardStrategy;
import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.common.Queen;
import chess.domain.piece.ranged.King;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        Position p1 = CachedPosition.a1;
        Position p2 = CachedPosition.a3;
        Map<Position, Piece> pieces = Map.of(p1, king, p2, queen);
        Board board = new Board(new CreateMockBoardStrategy(pieces));

        Status status = new Status(board);

        assertThat(status.getBlackScore()).isEqualTo(9);
    }

    @Test
    @DisplayName("폰이 같은 열에 있으면 폰 하나를 0.5점으로 계산한다")
    void pawns_Score() {
        Piece pawn = new Pawn(Color.BLACK);
        Position p1 = CachedPosition.a1;
        Position p2 = CachedPosition.a3;
        Piece king = new King(Color.BLACK);
        Position p3 = CachedPosition.a2;
        Map<Position, Piece> pieces = Map.of(p1, pawn, p2, pawn, p3, king);
        Board board = new Board(new CreateMockBoardStrategy(pieces));

        Status status = new Status(board);

        assertThat(status.getBlackScore()).isEqualTo(1.0);
    }
}
