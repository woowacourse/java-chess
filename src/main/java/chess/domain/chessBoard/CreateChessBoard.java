package chess.domain.chessBoard;

import chess.domain.chessPiece.ChessPiece;
import chess.domain.chessPiece.ChessPieceCache;
import chess.domain.position.Position;

import java.util.HashMap;
import java.util.Map;

public class CreateChessBoard {

    public static Map<Position, ChessPiece> from(Map<String, String> chessPieces) {
        Map<Position, ChessPiece> chessBoard = new HashMap<>();
        Map<String, String> chessBoardPieces = new HashMap<>(chessPieces);

        chessBoardPieces.forEach((key, value) ->
                chessBoard.put(Position.of(key), ChessPieceCache.getChessPiece(key, value)));

        return chessBoard;
    }
}
