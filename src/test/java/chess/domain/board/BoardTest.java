package chess.domain.board;

import chess.domain.board.position.Horizontal;
import chess.domain.board.position.Position;
import chess.domain.board.position.Vertical;
import chess.domain.piece.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest {

    @DisplayName("올바른 보드 생성된다.")
    @Test
    void createTest() {
        Board board = BoardInitializer.initiateBoard();

        Piece[] pieces = getPiecesOfFirstLine(Owner.WHITE);

        for (Horizontal horizontal : Horizontal.values()) {
            assertThat(board.of(Vertical.A, horizontal).getSymbol()).isEqualTo(pieces[horizontal.getIndex()].getSymbol());
        }
    }

    private static Piece[] getPiecesOfFirstLine(Owner owner){
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

    @DisplayName("입력한 위치의 Square를 가져온다")
    @Test
    void of(){
        Board board = BoardInitializer.initiateBoard();

        Piece piece = board.of(Vertical.B, Horizontal.TWO);
        assertThat(piece).isInstanceOf(Pawn.class);
        assertThat(piece).isEqualTo(Pawn.getInstanceOf(Owner.WHITE));
    }
}
