package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.ChessBoard;
import chess.domain.Color;
import chess.domain.generator.EmptyBoardGenerator;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BishopTest {

    @DisplayName("위치 정보를 기반으로 이동 가능을 여부를 반환하다.")
    @Test
    void 위치정보를_활용하여_이동가능여부를_반환한다() {
        ChessBoard chessBoard = new ChessBoard(new EmptyBoardGenerator());
        Position sourcePosition = new Position("c1");
        Position targetPosition = new Position("g5");
        Bishop bishop = new Bishop(Color.WHITE);

        assertDoesNotThrow(() -> bishop.validateMove(chessBoard.getBoard(), sourcePosition, targetPosition));
    }

    @DisplayName("위치 정보를 기반으로 중간에 기물이 있는 경우 이동이 불가능하다.")
    @Test
    void 중간에_기물이_있는_경우_이동_불가능하다() {
        ChessBoard chessBoard = new ChessBoard(new EmptyBoardGenerator());
        chessBoard.init();

        Position sourcePosition = new Position("c1");
        Position targetPosition = new Position("g5");
        Bishop bishop = new Bishop(Color.WHITE);

        assertThatThrownBy(() -> bishop.validateMove(chessBoard.getBoard(), sourcePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
