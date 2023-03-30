package chess.piece;

import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chess.board.Position;
import chess.fixture.EmptyBoardFixture;
import chess.fixture.PositionFixture;

class EmptyPieceTest {

    private Map<Position, Piece> board;

    @BeforeEach
    void setUp() {
        board = new EmptyBoardFixture().getBoard();
    }

    @Test
    void 빈_말에_대해_메소드를_수행하면_예외() {
        //given
        EmptyPiece emptyPiece = new EmptyPiece();

        Position to = PositionFixture.B3;
        Position from = PositionFixture.A1;

        //when & then
        Assertions.assertThatThrownBy(() -> emptyPiece.validateMove(from, to, board))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("빈 말은 움직일 수 없습니다.");
    }

}
