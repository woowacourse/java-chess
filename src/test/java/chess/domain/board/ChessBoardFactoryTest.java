package chess.domain.board;

import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ChessBoardFactoryTest {
    @DisplayName("유효한(1~8, a~h)의 범위가 아니면 에러가 발생한다.")
    @Test
    void initializeException() {
        ChessBoard chessBoard = ChessBoardFactory.initializeBoard();
        Map<Position, Piece> boards = chessBoard.boards();

        assertThatThrownBy(() -> boards.get(Position.valueOf("0", "a")))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> boards.get(Position.valueOf("1", "j")))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> boards.get(Position.valueOf("1", "-a")))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
