import chess.domain.ChessBoard;
import chess.domain.position.Position;
import chess.domain.position.Positions;
import chess.domain.position.component.Column;
import chess.domain.position.component.Row;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ChessBoardTest {
    @DisplayName("초기 체스판 개수 확인")
    @Test
    void initChessBoard() {
        ChessBoard chessBoard = new ChessBoard();
        int actual = chessBoard.getChessBoard().size();
        int expected = Column.values().length * Row.values().length;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 같은_위치로_이동했을때() {
        ChessBoard chessBoard = new ChessBoard();
        Position position = Positions.of("a1");
        assertThatThrownBy(() -> chessBoard.move(position, position))
                .isInstanceOf(IllegalArgumentException.class);
    }
}