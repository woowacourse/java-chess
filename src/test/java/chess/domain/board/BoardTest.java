package chess.domain.board;

import chess.domain.board.position.Horizontal;
import chess.domain.board.position.Position;
import chess.domain.board.position.Vertical;
import chess.domain.piece.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = BoardInitializer.initiateBoard();
    }

    @DisplayName("올바른 보드 생성된다.")
    @Test
    void createTest() {
        Piece[] pieces = getPiecesOfFirstLine(Owner.WHITE);

        for (Horizontal horizontal : Horizontal.values()) {
            assertThat(board.of(Vertical.A, horizontal).getSymbol()).isEqualTo(pieces[horizontal.getIndex()].getSymbol());
        }
    }

    private static Piece[] getPiecesOfFirstLine(Owner owner) {
        return new Piece[]{
                new Rook(owner),
                new Knight(owner),
                new Bishop(owner),
                new Queen(owner),
                new King(owner),
                new Bishop(owner),
                new Knight(owner),
                new Rook(owner)
        };
    }

    @DisplayName("입력한 위치의 기물을 가져온다.")
    @Test
    void of() {
        final Piece piece = board.of(Vertical.B, Horizontal.TWO);
        assertThat(piece).isInstanceOf(Pawn.class);
        assertThat(piece).isEqualTo(Pawn.getInstanceOf(Owner.WHITE));
    }

    @DisplayName("입력한 위치로 이동된다.")
    @Test
    void moveTest() {
        final Position source = new Position(Vertical.B, Horizontal.TWO);
        final Position target = new Position(Vertical.B, Horizontal.THREE);

        board.move(source, target);

        assertThat(board.of(source)).isInstanceOf(Empty.class);
        assertThat(board.of(target)).isInstanceOf(Pawn.class);
    }
}
