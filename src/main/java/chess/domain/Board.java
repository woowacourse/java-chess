package chess.domain;

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

public class Board {

    private static final int INITIAL_WHITE_SPECIAL_RANK = 1;
    private static final int INITIAL_WHITE_PAWN_RANK = 2;
    private static final int INITIAL_BLACK_SPECIAL_RANK = 8;
    private static final int INITIAL_BLACK_PAWN_RANK = 7;

    private final Map<Coordinate, Piece> pieces = new HashMap<>();

    public Board() {
        initializeWhitePiece();
        initializeBlackPiece();
    }

    private void initializeWhitePiece() {
        initializeSpecialPiece(INITIAL_WHITE_SPECIAL_RANK, Team.WHITE);
        initializePawn(INITIAL_WHITE_PAWN_RANK, Team.WHITE);
    }

    private void initializeBlackPiece() {
        initializeSpecialPiece(INITIAL_BLACK_SPECIAL_RANK, Team.BLACK);
        initializePawn(INITIAL_BLACK_PAWN_RANK, Team.BLACK);
    }

    private void initializePawn(int rankValue, Team team) {
        for (char fileValue = 'a'; fileValue <= 'h'; fileValue++) {
            pieces.put(new Coordinate(rankValue, fileValue), new Pawn(team));
        }
    }

    private void initializeSpecialPiece(int rankValue, Team team) {
        pieces.put(new Coordinate(rankValue, 'e'), new King(team));
        pieces.put(new Coordinate(rankValue, 'd'), new Queen(team));
        pieces.put(new Coordinate(rankValue, 'c'), new Bishop(team));
        pieces.put(new Coordinate(rankValue, 'f'), new Bishop(team));
        pieces.put(new Coordinate(rankValue, 'b'), new Knight(team));
        pieces.put(new Coordinate(rankValue, 'g'), new Knight(team));
        pieces.put(new Coordinate(rankValue, 'a'), new Rook(team));
        pieces.put(new Coordinate(rankValue, 'h'), new Rook(team));
    }

    public Piece findByCoordinate(Coordinate coordinate) {
        return pieces.get(coordinate);
    }

    public int pieceSize() {
        return pieces.size();
    }
}
