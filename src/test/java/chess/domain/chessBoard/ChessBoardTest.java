package chess.domain.chessBoard;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessBoardTest {
    @Test
    void ChessBoard_MapOfPositionAndChessPiece_GenerateInstance() {
        assertThat(new ChessBoard(ChessBoardFactory.create())).isInstanceOf(ChessBoard.class);
    }
}
