package chess.domain.board;

import chess.domain.board.position.Horizontal;
import chess.domain.board.position.Position;
import chess.domain.board.position.Vertical;
import chess.domain.piece.*;
import chess.domain.piece.bishop.Bishop;
import chess.domain.piece.king.King;
import chess.domain.piece.knight.Knight;
import chess.domain.piece.pawn.Pawn;
import chess.domain.piece.queen.Queen;
import chess.domain.piece.rook.Rook;
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

        for (Vertical vertical : Vertical.values()) {
            assertThat(board.of(vertical, Horizontal.ONE).getSymbol()).isEqualTo(pieces[vertical.getIndex() - 1].getSymbol());
        }
    }

    private static Piece[] getPiecesOfFirstLine(Owner owner) {
        return new Piece[]{
                Rook.getInstanceOf(owner),
                Knight.getInstanceOf(owner),
                Bishop.getInstanceOf(owner),
                Queen.getInstanceOf(owner),
                King.getInstanceOf(owner),
                Bishop.getInstanceOf(owner),
                Knight.getInstanceOf(owner),
                Rook.getInstanceOf(owner)
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
        final Position source = Position.of(Vertical.B, Horizontal.TWO);
        final Position target = Position.of(Vertical.B, Horizontal.THREE);

        board.move(source, target);

        assertThat(board.of(source)).isInstanceOf(Empty.class);
        assertThat(board.of(target)).isInstanceOf(Pawn.class);
    }
}
