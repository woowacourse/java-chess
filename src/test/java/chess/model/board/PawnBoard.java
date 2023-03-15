package chess.model.board;

import static chess.model.board.PositionFixture.*;
import static chess.model.piece.PieceColor.*;

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
    private Board pawnBoard;

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
