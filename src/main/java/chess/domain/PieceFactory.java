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
        return Type.valueOf(name).create(team);
    }
}