package chess.domain;

import chess.domain.pieces.*;

import java.util.Arrays;
import java.util.List;

public class PieceFactory {
    public static List<Piece> generateFirstPieces(Team team) {
        return Arrays.asList(new Rook(team),
                new Knight(team),
                new Bishop(team),
                new Queen(team),
                new King(team),
                new Bishop(team),
                new Knight(team),
                new Rook(team));
    }

    public static Piece of(String name, Team team) {
        if (name.equals("ROOK")) return new Rook(team);
        if (name.equals("KNIGHT")) return new Knight(team);
        if (name.equals("BISHOP")) return new Bishop(team);
        if (name.equals("QUEEN")) return new Queen(team);
        if (name.equals("KING")) return new King(team);
        if (name.equals("PAWN")) return new Pawn(team);
        return new Blank();
    }
}