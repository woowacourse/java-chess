package domain;

import static domain.Position.*;
import static domain.Team.*;

import java.util.List;

public class InitialPieces {
	public static final List<Piece> INITIAL_PIECES = List.of(
		new Pawn(A2, WHITE), new Pawn(B2, WHITE), new Pawn(C2, WHITE), new Pawn(D2, WHITE),
		new Pawn(E2, WHITE), new Pawn(F2, WHITE), new Pawn(G2, WHITE), new Pawn(H2, WHITE),
		new Rook(A1, WHITE), new Rook(H1, WHITE), new Knight(B1, WHITE), new Rook(G1, WHITE),
		new Bishop(C1, WHITE), new Bishop(F1, WHITE), new Queen(D1, WHITE), new King(E1, WHITE),
		new Pawn(A7, BLACK), new Pawn(B7, BLACK), new Pawn(C7, BLACK), new Pawn(D7, BLACK),
		new Pawn(E7, BLACK), new Pawn(F7, BLACK), new Pawn(G7, BLACK), new Pawn(H7, BLACK),
		new Rook(A8, BLACK), new Rook(H8, BLACK), new Knight(B8, BLACK), new Rook(G8, BLACK),
		new Bishop(C8, BLACK), new Bishop(F8, BLACK), new Queen(D8, BLACK), new King(E8, BLACK)
	);
}
