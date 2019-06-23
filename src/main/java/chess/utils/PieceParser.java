package chess.utils;

import java.util.HashMap;
import java.util.Map;

import chess.domain.piece.AbstractPiece;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.utils.exceptions.InvalidPieceNameException;

import static chess.domain.Team.BLACK;
import static chess.domain.Team.WHITE;

class PieceParser {
    private static Map<String, AbstractPiece> pieceCreator;

    static {
        pieceCreator = new HashMap<>();
        pieceCreator.put("p", new Pawn(WHITE));
        pieceCreator.put("k", new King(WHITE));
        pieceCreator.put("q", new Queen(WHITE));
        pieceCreator.put("r", new Rook(WHITE));
        pieceCreator.put("n", new Knight(WHITE));
        pieceCreator.put("b", new Bishop(WHITE));

        pieceCreator.put("P", new Pawn(BLACK));
        pieceCreator.put("K", new King(BLACK));
        pieceCreator.put("Q", new Queen(BLACK));
        pieceCreator.put("R", new Rook(BLACK));
        pieceCreator.put("N", new Knight(BLACK));
        pieceCreator.put("B", new Bishop(BLACK));
    }

    static AbstractPiece parse(String name) {
        validPieceName(name);
        return pieceCreator.get(name);
    }

    private static void validPieceName(String name) {
        if (!pieceCreator.containsKey(name)) {
            throw new InvalidPieceNameException("그런 말은 존재하지 않습니다.");
        }
    }
}
