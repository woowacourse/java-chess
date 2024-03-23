package chess.domain;

import chess.domain.piece.Color;
import chess.domain.piece.File;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import chess.domain.piece.Rank;
import chess.domain.piece.type.Bishop;
import chess.domain.piece.type.Empty;
import chess.domain.piece.type.King;
import chess.domain.piece.type.Night;
import chess.domain.piece.type.Pawn;
import chess.domain.piece.type.Queen;
import chess.domain.piece.type.Rook;
import java.util.LinkedHashMap;
import java.util.Map;

public class ChessBoardMaker {

    private ChessBoardMaker() {
    }

    public static ChessBoard init() {
        final Map<Position, Piece> pieces = new LinkedHashMap<>();

        pieces.putAll(createPieceWithoutPawn(Color.BLACK, Rank.EIGHT));
        pieces.putAll(createPawn(Color.BLACK, Rank.SEVEN));
        pieces.putAll(createEmpty(Rank.SIX));
        pieces.putAll(createEmpty(Rank.FIVE));
        pieces.putAll(createEmpty(Rank.FOUR));
        pieces.putAll(createEmpty(Rank.THREE));
        pieces.putAll(createPawn(Color.WHITE, Rank.TWO));
        pieces.putAll(createPieceWithoutPawn(Color.WHITE, Rank.ONE));

        return new ChessBoard(pieces);
    }

    private static Map<Position, Piece> createPawn(final Color color, final Rank rank) {
        final Map<Position, Piece> pieces = new LinkedHashMap<>();

        pieces.put(new Position(File.A, rank), new Pawn(color));
        pieces.put(new Position(File.B, rank), new Pawn(color));
        pieces.put(new Position(File.C, rank), new Pawn(color));
        pieces.put(new Position(File.D, rank), new Pawn(color));
        pieces.put(new Position(File.E, rank), new Pawn(color));
        pieces.put(new Position(File.F, rank), new Pawn(color));
        pieces.put(new Position(File.G, rank), new Pawn(color));
        pieces.put(new Position(File.H, rank), new Pawn(color));

        return pieces;
    }

    private static Map<Position, Piece> createPieceWithoutPawn(final Color color, final Rank rank) {
        final Map<Position, Piece> pieces = new LinkedHashMap<>();

        pieces.put(new Position(File.A, rank), new Rook(color));
        pieces.put(new Position(File.B, rank), new Night(color));
        pieces.put(new Position(File.C, rank), new Bishop(color));
        pieces.put(new Position(File.D, rank), new Queen(color));
        pieces.put(new Position(File.E, rank), new King(color));
        pieces.put(new Position(File.F, rank), new Bishop(color));
        pieces.put(new Position(File.G, rank), new Night(color));
        pieces.put(new Position(File.H, rank), new Rook(color));

        return pieces;
    }

    private static Map<Position, Piece> createEmpty(final Rank rank) {
        final Map<Position, Piece> pieces = new LinkedHashMap<>();

        pieces.put(new Position(File.A, rank), new Empty());
        pieces.put(new Position(File.B, rank), new Empty());
        pieces.put(new Position(File.C, rank), new Empty());
        pieces.put(new Position(File.D, rank), new Empty());
        pieces.put(new Position(File.E, rank), new Empty());
        pieces.put(new Position(File.F, rank), new Empty());
        pieces.put(new Position(File.G, rank), new Empty());
        pieces.put(new Position(File.H, rank), new Empty());

        return pieces;
    }

}
