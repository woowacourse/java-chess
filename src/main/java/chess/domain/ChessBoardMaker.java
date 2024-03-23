package chess.domain;

import chess.domain.piece.Color;
import chess.domain.piece.File;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import chess.domain.piece.Rank;
import chess.domain.piece.type.Bishop;
import chess.domain.piece.type.King;
import chess.domain.piece.type.Night;
import chess.domain.piece.type.Pawn;
import chess.domain.piece.type.Queen;
import chess.domain.piece.type.Rook;
import java.util.HashSet;
import java.util.Set;

public class ChessBoardMaker {

    private ChessBoardMaker() {
    }

    public static ChessBoard init() {
        final Set<Piece> pieces = new HashSet<>();

        pieces.addAll((createPieceWithoutPawn(Color.BLACK, Rank.EIGHT)));
        pieces.addAll((createPawn(Color.BLACK, Rank.SEVEN)));
        pieces.addAll((createPawn(Color.WHITE, Rank.TWO)));
        pieces.addAll((createPieceWithoutPawn(Color.WHITE, Rank.ONE)));

        return new ChessBoard(pieces);
    }

    private static Set<Piece> createPawn(final Color color, final Rank rank) {
        return Set.of(new Pawn(color, new Position(File.A, rank)),
                new Pawn(color, new Position(File.B, rank)),
                new Pawn(color, new Position(File.C, rank)),
                new Pawn(color, new Position(File.D, rank)),
                new Pawn(color, new Position(File.E, rank)),
                new Pawn(color, new Position(File.F, rank)),
                new Pawn(color, new Position(File.G, rank)),
                new Pawn(color, new Position(File.H, rank)));
    }

    private static Set<Piece> createPieceWithoutPawn(final Color color, final Rank rank) {
        return Set.of(new Rook(color, new Position(File.A, rank)),
                new Night(color, new Position(File.B, rank)),
                new Bishop(color, new Position(File.C, rank)),
                new Queen(color, new Position(File.D, rank)),
                new King(color, new Position(File.E, rank)),
                new Bishop(color, new Position(File.F, rank)),
                new Night(color, new Position(File.G, rank)),
                new Rook(color, new Position(File.H, rank)));
    }
}
