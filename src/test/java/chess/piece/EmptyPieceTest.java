package chess.piece;

import java.util.HashMap;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chess.board.File;
import chess.board.Position;
import chess.board.Rank;
import chess.fixture.FixturePosition;

class EmptyPieceTest {

    private Map<Position, Piece> board;

    @BeforeEach
    void setUp() {
        board = new HashMap<>();
        for (final File file : File.values()) {
            for (final Rank rank : Rank.values()) {
                board.put(new Position(file, rank), new EmptyPiece());
            }
        }
    }

    @Test
    void 빈_말에_대해_메소드를_수행하면_예외() {
        //given
        EmptyPiece emptyPiece = new EmptyPiece();

        Position to = FixturePosition.B3;
        Position from = FixturePosition.A1;

        //when & then
        Assertions.assertThatThrownBy(() -> emptyPiece.validateMove(from, to, board))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("빈 말은 움직일 수 없습니다.");
    }

}
