package chess.dao;

import chess.domain.piece.Bishop;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;

public class PieceConverter {

    public static Piece of(String team, String name) {
        if (name.equals("PAWN")) {
            return new Pawn(TeamConverter.of(team));
        }
        if (name.equals("ROOK")) {
            return new Rook(TeamConverter.of(team));
        }
        if (name.equals("KNIGHT")) {
            return new Knight(TeamConverter.of(team));
        }
        if (name.equals("BISHOP")) {
            return new Bishop(TeamConverter.of(team));
        }
        if (name.equals("QUEEN")) {
            return new Queen(TeamConverter.of(team));
        }
        if (name.equals("KING")) {
            return new King(TeamConverter.of(team));
        }
        if (name.equals("EMPTY")) {
            return new EmptyPiece();
        }
        throw new IllegalArgumentException("[ERROR] 없는 기물입니다.");
    }
}
