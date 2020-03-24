package chess.domain.chessboard;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class ChessBoardFactoryTest {
    @Test
    void create() {
        List<Piece> chessBoard = ChessBoardFactory.create();
        assertThat(chessBoard.size()).isEqualTo(64);
    }
}