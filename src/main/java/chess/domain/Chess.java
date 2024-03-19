package chess.domain;

import java.util.HashSet;
import java.util.Set;

import chess.domain.attribute.Color;
import chess.domain.attribute.File;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.StartingPawn;

public class Chess {

    private final Set<Piece> pieces = new HashSet<>();

    public Chess() {
        pieces.addAll(createPieces(Color.WHITE));
        pieces.addAll(createPieces(Color.BLACK));
    }

    private static Set<Piece> createPieces(final Color color) {
        Set<Piece> pieces = new HashSet<>();
        pieces.add(new King(color));
        pieces.add(new Queen(color));
        pieces.add(new Bishop(color, File.C));
        pieces.add(new Bishop(color, File.F));
        pieces.add(new Knight(color, File.B));
        pieces.add(new Knight(color, File.G));
        pieces.add(new Rook(color, File.A));
        pieces.add(new Rook(color, File.H));
        for (final File file : File.values()) {
            pieces.add(new StartingPawn(color, file));
        }
        return pieces;
    }
}
