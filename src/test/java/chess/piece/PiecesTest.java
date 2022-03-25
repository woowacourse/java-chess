package chess.piece;

import chess.Position;
import chess.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PiecesTest {

    @Test
    @DisplayName("말의 위치를 확인하여 체스판 초기화 테스트")
    void findByPosition() {
        Pieces pieces = Pieces.create();
        Piece piece = pieces.findByPosition(Position.of('a', '8'));

        Rook rook = new Rook(Position.of('a', '8'), Team.BLACK);
        assertThat(piece).isEqualTo(rook);
    }
}
