package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.HashMap;
import java.util.Map;

public class NormalPiecesGenerator implements PiecesGenerator {

    @Override
    public Map<Position, Piece> generate() {
        Map<Position, Piece> pieces = new HashMap<>();
        createKing(pieces);
        createQueen(pieces);
        createRook(pieces);
        createBishop(pieces);
        createKnight(pieces);
        createPawn(pieces);

        return pieces;
    }

    private void createKing(Map<Position, Piece> pieces) {
        pieces.put(new Position(King.BLACK_INIT_LOCATION), new King(Color.BLACK));
        pieces.put(new Position(King.WHITE_INIT_LOCATION), new King(Color.WHITE));
    }

    private void createQueen(Map<Position, Piece> pieces) {
        pieces.put(new Position(Queen.BLACK_INIT_LOCATION), new Queen(Color.BLACK));
        pieces.put(new Position(Queen.WHITE_INIT_LOCATION), new Queen(Color.WHITE));
    }

    private void createRook(Map<Position, Piece> pieces) {
        for (String location : Rook.BLACK_INIT_LOCATIONS) {
            pieces.put(new Position(location), new Rook(Color.BLACK));
        }

        for (String location : Rook.WHITE_INIT_LOCATIONS) {
            pieces.put(new Position(location), new Rook(Color.WHITE));
        }
    }

    private void createBishop(Map<Position, Piece> pieces) {
        for (String location : Bishop.BLACK_INIT_LOCATIONS) {
            pieces.put(new Position(location), new Bishop(Color.BLACK));
        }

        for (String location : Bishop.WHITE_INIT_LOCATIONS) {
            pieces.put(new Position(location), new Bishop(Color.WHITE));
        }
    }

    private void createKnight(Map<Position, Piece> pieces) {
        for (String location : Knight.BLACK_INIT_LOCATIONS) {
            pieces.put(new Position(location), new Knight(Color.BLACK));
        }

        for (String location : Knight.WHITE_INIT_LOCATIONS) {
            pieces.put(new Position(location), new Knight(Color.WHITE));
        }
    }

    private void createPawn(Map<Position, Piece> pieces) {
        for (String location : Pawn.BLACK_INIT_LOCATIONS) {
            pieces.put(new Position(location), new Pawn(Color.BLACK));
        }

        for (String location : Pawn.WHITE_INIT_LOCATIONS) {
            pieces.put(new Position(location), new Pawn(Color.WHITE));
        }
    }
}
