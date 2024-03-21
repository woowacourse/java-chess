package chess.domain.board;

import java.util.HashMap;
import java.util.Map;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;

public class BoardFactory {

    private BoardFactory() {
    }

    public static Map<Coordinate, Piece> createInitialPieces(
            int initialWhiteSpecialRank,
            int initialWhitePawnRank,
            int initialBlackSpecialRank,
            int initialBlackPawnRank) {

        HashMap<Coordinate, Piece> pieces = new HashMap<>();
        initializeWhitePiece(initialWhiteSpecialRank, initialWhitePawnRank, pieces);
        initializeBlackPiece(initialBlackSpecialRank, initialBlackPawnRank, pieces);

        return pieces;
    }

    private static void initializeWhitePiece(
            int initialWhiteSpecialRank,
            int initialWhitePawnRank,
            HashMap<Coordinate, Piece> pieces) {
        initializeSpecialPiece(initialWhiteSpecialRank, Team.WHITE, pieces);
        initializePawn(initialWhitePawnRank, Team.WHITE, pieces);
    }

    private static void initializeBlackPiece(
            int initialBlackSpecialRank,
            int initialBlackPawnRank,
            HashMap<Coordinate, Piece> pieces) {
        initializeSpecialPiece(initialBlackSpecialRank, Team.BLACK, pieces);
        initializePawn(initialBlackPawnRank, Team.BLACK, pieces);
    }

    private static void initializePawn(
            int rankValue,
            Team team,
            HashMap<Coordinate, Piece> pieces) {
        for (char fileValue = 'a'; fileValue <= 'h'; fileValue++) {
            pieces.put(new Coordinate(rankValue, fileValue), new Pawn(team));
        }
    }

    private static void initializeSpecialPiece(int rankValue, Team team, HashMap<Coordinate, Piece> pieces) {
        pieces.put(new Coordinate(rankValue, 'e'), new King(team));
        pieces.put(new Coordinate(rankValue, 'd'), new Queen(team));
        pieces.put(new Coordinate(rankValue, 'c'), new Bishop(team));
        pieces.put(new Coordinate(rankValue, 'f'), new Bishop(team));
        pieces.put(new Coordinate(rankValue, 'b'), new Knight(team));
        pieces.put(new Coordinate(rankValue, 'g'), new Knight(team));
        pieces.put(new Coordinate(rankValue, 'a'), new Rook(team));
        pieces.put(new Coordinate(rankValue, 'h'), new Rook(team));
    }
}
