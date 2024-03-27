package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.point.Point;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardFactoryTest {

    @Test
    @DisplayName("체스 초기 상태의 기물들을 생성할 수 있다.")
    void create() {
        Board board = BoardFactory.createInitialChessBoard();
        Map<Point, Piece> pieces = board.getBoard();

        long emptyCount = piecesCount(pieces, Piece.empty());
        long WhiteKingCount = piecesCount(pieces, Piece.kingFrom(Team.WHITE));
        long BlackKingCount = piecesCount(pieces, Piece.kingFrom(Team.BLACK));

        Assertions.assertAll(
                () -> assertThat(pieces).hasSize(64),
                () -> assertThat(emptyCount).isEqualTo(32),
                () -> assertThat(WhiteKingCount).isEqualTo(1),
                () -> assertThat(BlackKingCount).isEqualTo(1)
        );
    }

    private long piecesCount(Map<Point, Piece> pieces, Piece piece) {
        return pieces.values().stream()
                .filter(piece::equals)
                .count();
    }
}
