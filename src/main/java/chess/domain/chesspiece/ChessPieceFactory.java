package chess.domain.chesspiece;

import chess.domain.chesspiece.concrete.*;
import chess.domain.game.Player;

import java.util.LinkedHashMap;
import java.util.Map;

public class ChessPieceFactory {
    private static final Map<String, Piece> chessPieces = new LinkedHashMap<>();

    static {
        chessPieces.put("bishop_white", new Bishop(Player.WHITE));
        chessPieces.put("rook_white", new Rook(Player.WHITE));
        chessPieces.put("pawn_white", new Pawn(Player.WHITE));
        chessPieces.put("knight_white", new Knight(Player.WHITE));
        chessPieces.put("king_white", new King(Player.WHITE));
        chessPieces.put("queen_white", new Queen(Player.WHITE));

        chessPieces.put("bishop_black", new Bishop(Player.BLACK));
        chessPieces.put("rook_black", new Rook(Player.BLACK));
        chessPieces.put("pawn_black", new Pawn(Player.BLACK));
        chessPieces.put("knight_black", new Knight(Player.BLACK));
        chessPieces.put("king_black", new King(Player.BLACK));
        chessPieces.put("queen_black", new Queen(Player.BLACK));
    }

    public static Piece of(String piece) {
        return chessPieces.get(piece);
    }
}
