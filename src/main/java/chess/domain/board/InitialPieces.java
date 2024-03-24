package chess.domain.board;

import static chess.domain.Position.A1;
import static chess.domain.Position.A2;
import static chess.domain.Position.A7;
import static chess.domain.Position.A8;
import static chess.domain.Position.B1;
import static chess.domain.Position.B2;
import static chess.domain.Position.B7;
import static chess.domain.Position.B8;
import static chess.domain.Position.C1;
import static chess.domain.Position.C2;
import static chess.domain.Position.C7;
import static chess.domain.Position.C8;
import static chess.domain.Position.D1;
import static chess.domain.Position.D2;
import static chess.domain.Position.D7;
import static chess.domain.Position.D8;
import static chess.domain.Position.E1;
import static chess.domain.Position.E2;
import static chess.domain.Position.E7;
import static chess.domain.Position.E8;
import static chess.domain.Position.F1;
import static chess.domain.Position.F2;
import static chess.domain.Position.F7;
import static chess.domain.Position.F8;
import static chess.domain.Position.G1;
import static chess.domain.Position.G2;
import static chess.domain.Position.G7;
import static chess.domain.Position.G8;
import static chess.domain.Position.H1;
import static chess.domain.Position.H2;
import static chess.domain.Position.H7;
import static chess.domain.Position.H8;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import java.util.List;

class InitialPieces {
    static final List<Piece> INITIAL_PIECES = List.of(
            new Pawn(A2, Team.WHITE), new Pawn(B2, Team.WHITE), new Pawn(C2, Team.WHITE), new Pawn(D2, Team.WHITE),
            new Pawn(E2, Team.WHITE), new Pawn(F2, Team.WHITE), new Pawn(G2, Team.WHITE), new Pawn(H2, Team.WHITE),
            new Rook(A1, Team.WHITE), new Rook(H1, Team.WHITE), new Knight(B1, Team.WHITE), new Knight(G1, Team.WHITE),
            new Bishop(C1, Team.WHITE), new Bishop(F1, Team.WHITE), new Queen(D1, Team.WHITE), new King(E1, Team.WHITE),
            new Pawn(A7, Team.BLACK), new Pawn(B7, Team.BLACK), new Pawn(C7, Team.BLACK), new Pawn(D7, Team.BLACK),
            new Pawn(E7, Team.BLACK), new Pawn(F7, Team.BLACK), new Pawn(G7, Team.BLACK), new Pawn(H7, Team.BLACK),
            new Rook(A8, Team.BLACK), new Rook(H8, Team.BLACK), new Knight(B8, Team.BLACK), new Knight(G8, Team.BLACK),
            new Bishop(C8, Team.BLACK), new Bishop(F8, Team.BLACK), new Queen(D8, Team.BLACK), new King(E8, Team.BLACK)
    );
}
