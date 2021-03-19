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
        Piece piece = board.of(Vertical.B, Horizontal.TWO);
        assertThat(piece).isInstanceOf(Pawn.class);
        assertThat(piece).isEqualTo(Pawn.getInstanceOf(Owner.WHITE));
    }

    @DisplayName("입력한 위치로 이동된다.")
    @Test
    void moveTest() {
        Position source = new Position(Vertical.B, Horizontal.TWO);
        Position target = new Position(Vertical.C, Horizontal.THREE);

        int i =0;
        for(Piece piece : board.getBoard().values()) {
            System.out.print(piece.decideUpperOrLower(piece.getSymbol()));
            if(i++ >6){
                i =0;
                System.out.println();
            }
        }

        board.move(source, target);

        int k =0;
        for(Piece piece : board.getBoard().values()) {
            System.out.print(piece.decideUpperOrLower(piece.getSymbol()));
            if(k++ >6){
                k =0;
                System.out.println();
            }
        }

        assertThat(board.of(target)).isInstanceOf(Pawn.class);
    }
}
