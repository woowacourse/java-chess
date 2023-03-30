package chess.model.board;

import static chess.model.board.PositionFixture.A2;
import static chess.model.board.PositionFixture.A3;
import static chess.model.board.PositionFixture.A4;
import static chess.model.board.PositionFixture.A5;
import static chess.model.board.PositionFixture.A6;
import static chess.model.board.PositionFixture.A7;
import static chess.model.board.PositionFixture.B2;
import static chess.model.board.PositionFixture.B3;
import static chess.model.board.PositionFixture.B4;
import static chess.model.board.PositionFixture.B5;
import static chess.model.board.PositionFixture.B6;
import static chess.model.board.PositionFixture.B7;
import static chess.model.board.PositionFixture.C2;
import static chess.model.board.PositionFixture.C3;
import static chess.model.board.PositionFixture.C4;
import static chess.model.board.PositionFixture.C5;
import static chess.model.board.PositionFixture.C6;
import static chess.model.board.PositionFixture.C7;
import static chess.model.board.PositionFixture.D2;
import static chess.model.board.PositionFixture.D3;
import static chess.model.board.PositionFixture.D4;
import static chess.model.board.PositionFixture.D5;
import static chess.model.board.PositionFixture.D6;
import static chess.model.board.PositionFixture.D7;
import static chess.model.board.PositionFixture.E2;
import static chess.model.board.PositionFixture.E3;
import static chess.model.board.PositionFixture.E4;
import static chess.model.board.PositionFixture.E5;
import static chess.model.board.PositionFixture.E6;
import static chess.model.board.PositionFixture.E7;
import static chess.model.board.PositionFixture.F2;
import static chess.model.board.PositionFixture.F3;
import static chess.model.board.PositionFixture.F4;
import static chess.model.board.PositionFixture.F5;
import static chess.model.board.PositionFixture.F6;
import static chess.model.board.PositionFixture.F7;
import static chess.model.board.PositionFixture.G2;
import static chess.model.board.PositionFixture.G3;
import static chess.model.board.PositionFixture.G4;
import static chess.model.board.PositionFixture.G5;
import static chess.model.board.PositionFixture.G6;
import static chess.model.board.PositionFixture.G7;
import static chess.model.board.PositionFixture.H2;
import static chess.model.board.PositionFixture.H3;
import static chess.model.board.PositionFixture.H4;
import static chess.model.board.PositionFixture.H5;
import static chess.model.board.PositionFixture.H6;
import static chess.model.board.PositionFixture.H7;
import static chess.model.piece.PieceColor.BLACK;
import static chess.model.piece.PieceColor.WHITE;

import chess.model.position.Position;

public class PawnBoard {

    /**
     * ........
     * ........
     * ........
     * PPPPPPPP
     * pppppppp
     * ........
     * ........
     * ........
     */
    private final Board pawnBoard;

    private PawnBoard() {
        pawnBoard = Board.create();
        initializeWhitePawn();
        initializeBlackPawn();
    }

    public static Board create() {
        final PawnBoard pawnBoard = new PawnBoard();
        return pawnBoard.pawnBoard;
    }

    private void initializeWhitePawn() {
        moveWhitePawn(A2, A3, A4);
        moveWhitePawn(B2, B3, B4);
        moveWhitePawn(C2, C3, C4);
        moveWhitePawn(D2, D3, D4);
        moveWhitePawn(E2, E3, E4);
        moveWhitePawn(F2, F3, F4);
        moveWhitePawn(G2, G3, G4);
        moveWhitePawn(H2, H3, H4);
    }

    private void initializeBlackPawn() {
        moveBlackPawn(A7, A6, A5);
        moveBlackPawn(B7, B6, B5);
        moveBlackPawn(C7, C6, C5);
        moveBlackPawn(D7, D6, D5);
        moveBlackPawn(E7, E6, E5);
        moveBlackPawn(F7, F6, F5);
        moveBlackPawn(G7, G6, G5);
        moveBlackPawn(H7, H6, H5);
    }

    private void moveWhitePawn(final Position source, final Position waypoint,
            final Position target) {
        pawnBoard.move(source, waypoint, WHITE);
        pawnBoard.move(waypoint, target, WHITE);
    }

    private void moveBlackPawn(final Position source, final Position waypoint,
            final Position target) {
        pawnBoard.move(source, waypoint, BLACK);
        pawnBoard.move(waypoint, target, BLACK);
    }

}
