package chess.dao;

import chess.domain.piece.Bishop;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;

public class PieceConverter {

    public static Piece of(String team, String name) {
        if (name.equals("PAWN")) {
            return new Pawn(Team.valueOf(team));
        }
        if (name.equals("ROOK")) {
            return new Rook(Team.valueOf(team));
        }
        if (name.equals("KNIGHT")) {
            return new Knight(Team.valueOf(team));
        }
        if (name.equals("BISHOP")) {
            return new Bishop(Team.valueOf(team));
        }
        if (name.equals("QUEEN")) {
            return new Queen(Team.valueOf(team));
        }
        if (name.equals("KING")) {
            return new King(Team.valueOf(team));
        }
        if (name.equals("EMPTY")) {
            return new EmptyPiece();
        }
        throw new IllegalArgumentException("[ERROR] 없는 기물입니다.");
    }
}
