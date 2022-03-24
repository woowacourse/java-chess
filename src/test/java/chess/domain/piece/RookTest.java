package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.BoardFixtures;
import chess.domain.ChessBoard;
import chess.domain.Color;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {

    @DisplayName("위치 정보를 기반으로 이동 가능을 여부를 반환하다.")
    @Test
    void 위치정보를_활용하여_이동가능여부를_반환한다() {
        ChessBoard chessBoard = new ChessBoard(BoardFixtures::generateOnlyRookBoard);
        Position sourcePosition = new Position("a1");
        Position targetPosition = new Position("a8");
        Rook rook = new Rook(Color.WHITE);

        boolean result = rook.canMove(chessBoard.getBoard(), sourcePosition, targetPosition);

        assertThat(result).isTrue();
    }
}
