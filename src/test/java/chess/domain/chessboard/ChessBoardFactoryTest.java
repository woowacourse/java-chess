package chess.domain.chessboard;

import chess.domain.PiecePosition;
import chess.domain.Position;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class ChessBoardFactoryTest {
    @Test
    void create() {
        List<PiecePosition> chessBoard = ChessBoardFactory.create();
        assertThat(chessBoard.size()).isEqualTo(64);
    }
}