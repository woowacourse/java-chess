package chess.domain;

import java.util.Arrays;
import java.util.List;

class PieceFactory {
    static List<Piece> generateFirstPieces(Team team) {
        return Arrays.asList(new Rook(team),
                new Knight(team),
                new Bishop(team),
                new Queen(team),
                new King(team),
                new Bishop(team),
                new Knight(team),
                new Rook(team));
    }
}
