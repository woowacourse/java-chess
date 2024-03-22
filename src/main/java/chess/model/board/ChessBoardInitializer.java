package chess.model.board;

import chess.model.piece.*;
import chess.model.position.ChessPosition;
import chess.model.position.File;
import chess.model.position.Rank;

import java.util.HashMap;
import java.util.Map;

public class ChessBoardInitializer {
    public ChessBoardInitializer() {
    }

    public Map<ChessPosition, Piece> create() {
        Map<ChessPosition, Piece> board = new HashMap<>();
        board.putAll(createSpecialPieces(Side.BLACK));
        board.putAll(createPawns(Side.BLACK));
        board.putAll(createSpecialPieces(Side.WHITE));
        board.putAll(createPawns(Side.WHITE));
        board.putAll(createBlanks());
        return board;
    }

    private Map<ChessPosition, Piece> createSpecialPieces(Side side) {
        Rank rank = convertSpecialPieceRankWithSide(side);
        return Map.of(
                ChessPosition.of(File.A, rank), Rook.from(side),
                ChessPosition.of(File.B, rank), Knight.from(side),
                ChessPosition.of(File.C, rank), Bishop.from(side),
                ChessPosition.of(File.D, rank), Queen.from(side),
                ChessPosition.of(File.E, rank), King.from(side),
                ChessPosition.of(File.F, rank), Bishop.from(side),
                ChessPosition.of(File.G, rank), Knight.from(side),
                ChessPosition.of(File.H, rank), Rook.from(side)
        );
    }

    private Rank convertSpecialPieceRankWithSide(Side side) {
        if (side == Side.BLACK) {
            return Rank.EIGHT;
        }
        return Rank.ONE;
    }

    private Map<ChessPosition, Piece> createPawns(Side side) {
        Rank rank = convertPawnRanksWithSide(side);
        return Map.of(
                ChessPosition.of(File.A, rank), Pawn.from(side),
                ChessPosition.of(File.B, rank), Pawn.from(side),
                ChessPosition.of(File.C, rank), Pawn.from(side),
                ChessPosition.of(File.D, rank), Pawn.from(side),
                ChessPosition.of(File.E, rank), Pawn.from(side),
                ChessPosition.of(File.F, rank), Pawn.from(side),
                ChessPosition.of(File.G, rank), Pawn.from(side),
                ChessPosition.of(File.H, rank), Pawn.from(side)
        );
    }

    private Rank convertPawnRanksWithSide(Side side) {
        if (side == Side.BLACK) {
            return Rank.SEVEN;
        }
        return Rank.TWO;
    }

    public Map<ChessPosition, Piece> createBlanks() {
        Map<ChessPosition, Piece> blanks = new HashMap<>();
        for (int rankCoordinate = 3; rankCoordinate <= 6; rankCoordinate++) {
            blanks.putAll(createBlanksInRank(rankCoordinate));
        }
        return blanks;
    }

    public Map<ChessPosition, Piece> createBlanksInRank(int rankCoordinate) {
        Map<ChessPosition, Piece> blanks = new HashMap<>();
        for (File file : File.values()) {
            blanks.put(ChessPosition.of(file, Rank.from(rankCoordinate)), Blank.INSTANCE);
        }
        return blanks;
    }
}
