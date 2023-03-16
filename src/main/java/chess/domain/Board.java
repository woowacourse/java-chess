package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.HashMap;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> pieces;

    public Board() {
        this.pieces = Map.ofEntries(
                Map.entry(new Position(File.A, Rank.ONE), Rook.from(true)),
                Map.entry(new Position(File.B, Rank.ONE), Knight.from(true)),
                Map.entry(new Position(File.C, Rank.ONE), Bishop.from(true)),
                Map.entry(new Position(File.D, Rank.ONE), Queen.from(true)),
                Map.entry(new Position(File.E, Rank.ONE), King.from(true)),
                Map.entry(new Position(File.F, Rank.ONE), Bishop.from(true)),
                Map.entry(new Position(File.G, Rank.ONE), Knight.from(true)),
                Map.entry(new Position(File.H, Rank.ONE), Rook.from(true)),
                Map.entry(new Position(File.A, Rank.TWO), Pawn.from(true)),
                Map.entry(new Position(File.B, Rank.TWO), Pawn.from(true)),
                Map.entry(new Position(File.C, Rank.TWO), Pawn.from(true)),
                Map.entry(new Position(File.D, Rank.TWO), Pawn.from(true)),
                Map.entry(new Position(File.E, Rank.TWO), Pawn.from(true)),
                Map.entry(new Position(File.F, Rank.TWO), Pawn.from(true)),
                Map.entry(new Position(File.G, Rank.TWO), Pawn.from(true)),
                Map.entry(new Position(File.H, Rank.TWO), Pawn.from(true)),
                // Black
                Map.entry(new Position(File.A, Rank.EIGHT), Rook.from(false)),
                Map.entry(new Position(File.B, Rank.EIGHT), Knight.from(false)),
                Map.entry(new Position(File.C, Rank.EIGHT), Bishop.from(false)),
                Map.entry(new Position(File.D, Rank.EIGHT), Queen.from(false)),
                Map.entry(new Position(File.E, Rank.EIGHT), King.from(false)),
                Map.entry(new Position(File.F, Rank.EIGHT), Bishop.from(false)),
                Map.entry(new Position(File.G, Rank.EIGHT), Knight.from(false)),
                Map.entry(new Position(File.H, Rank.EIGHT), Rook.from(false)),
                Map.entry(new Position(File.A, Rank.SEVEN), Pawn.from(false)),
                Map.entry(new Position(File.B, Rank.SEVEN), Pawn.from(false)),
                Map.entry(new Position(File.C, Rank.SEVEN), Pawn.from(false)),
                Map.entry(new Position(File.D, Rank.SEVEN), Pawn.from(false)),
                Map.entry(new Position(File.E, Rank.SEVEN), Pawn.from(false)),
                Map.entry(new Position(File.F, Rank.SEVEN), Pawn.from(false)),
                Map.entry(new Position(File.G, Rank.SEVEN), Pawn.from(false)),
                Map.entry(new Position(File.H, Rank.SEVEN), Pawn.from(false))
        );
    }

    public Map<Position, Piece> getPieces() {
        return new HashMap<>(pieces);
    }
}
