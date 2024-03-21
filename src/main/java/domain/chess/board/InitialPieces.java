package domain.chess.board;

import static domain.Position.A1;
import static domain.Position.A2;
import static domain.Position.A7;
import static domain.Position.A8;
import static domain.Position.B1;
import static domain.Position.B2;
import static domain.Position.B7;
import static domain.Position.B8;
import static domain.Position.C1;
import static domain.Position.C2;
import static domain.Position.C7;
import static domain.Position.C8;
import static domain.Position.D1;
import static domain.Position.D2;
import static domain.Position.D7;
import static domain.Position.D8;
import static domain.Position.E1;
import static domain.Position.E2;
import static domain.Position.E7;
import static domain.Position.E8;
import static domain.Position.F1;
import static domain.Position.F2;
import static domain.Position.F7;
import static domain.Position.F8;
import static domain.Position.G1;
import static domain.Position.G2;
import static domain.Position.G7;
import static domain.Position.G8;
import static domain.Position.H1;
import static domain.Position.H2;
import static domain.Position.H7;
import static domain.Position.H8;
import static domain.chess.piece.Team.BLACK;
import static domain.chess.piece.Team.WHITE;

import domain.chess.piece.Bishop;
import domain.chess.piece.King;
import domain.chess.piece.Knight;
import domain.chess.piece.Pawn;
import domain.chess.piece.Piece;
import domain.chess.piece.Queen;
import domain.chess.piece.Rook;
import java.util.List;

public class InitialPieces {
    public static final List<Piece> INITIAL_PIECES = List.of(
            new Pawn(A2, WHITE), new Pawn(B2, WHITE), new Pawn(C2, WHITE), new Pawn(D2, WHITE),
            new Pawn(E2, WHITE), new Pawn(F2, WHITE), new Pawn(G2, WHITE), new Pawn(H2, WHITE),
            new Rook(A1, WHITE), new Rook(H1, WHITE), new Knight(B1, WHITE), new Knight(G1, WHITE),
            new Bishop(C1, WHITE), new Bishop(F1, WHITE), new Queen(D1, WHITE), new King(E1, WHITE),
            new Pawn(A7, BLACK), new Pawn(B7, BLACK), new Pawn(C7, BLACK), new Pawn(D7, BLACK),
            new Pawn(E7, BLACK), new Pawn(F7, BLACK), new Pawn(G7, BLACK), new Pawn(H7, BLACK),
            new Rook(A8, BLACK), new Rook(H8, BLACK), new Knight(B8, BLACK), new Knight(G8, BLACK),
            new Bishop(C8, BLACK), new Bishop(F8, BLACK), new Queen(D8, BLACK), new King(E8, BLACK)
    );
}
