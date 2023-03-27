package chess.domain.entity;

import chess.domain.pieces.*;
import chess.domain.pieces.component.Team;
import chess.domain.pieces.component.Type;

import java.util.HashMap;
import java.util.Map;

public class PieceNameConverter {
    private static final Map<Piece, String> piecesAndStrings = new HashMap<>();

    private PieceNameConverter() {
    }

    public static Piece convert(String name) {
        for (Map.Entry<Piece, String> pieceAndNames : piecesAndStrings.entrySet()) {
            if (pieceAndNames.getValue().equals(name)) {
                return pieceAndNames.getKey();
            }
        }
        throw new IllegalArgumentException("해당 이름을 가진 기물이 없습니다.");
    }

    public static void init() {
        piecesAndStrings.put(new EmptyPiece(Team.NEUTRALITY, Type.NO_PIECE), ".");
        piecesAndStrings.put(new King(Team.WHITE, Type.KING), "k");
        piecesAndStrings.put(new King(Team.BLACK, Type.KING), "K");
        piecesAndStrings.put(new Queen(Team.WHITE, Type.QUEEN), "q");
        piecesAndStrings.put(new Queen(Team.BLACK, Type.QUEEN), "Q");
        piecesAndStrings.put(new Knight(Team.WHITE, Type.KNIGHT), "n");
        piecesAndStrings.put(new Knight(Team.BLACK, Type.KNIGHT), "N");
        piecesAndStrings.put(new Bishop(Team.WHITE, Type.BISHOP), "b");
        piecesAndStrings.put(new Bishop(Team.BLACK, Type.BISHOP), "B");
        piecesAndStrings.put(new Rook(Team.WHITE, Type.ROOK), "r");
        piecesAndStrings.put(new Rook(Team.BLACK, Type.ROOK), "R");
        piecesAndStrings.put(new BlackPawn(Type.PAWN), "p");
        piecesAndStrings.put(new WhitePawn(Type.PAWN), "P");
    }
}
