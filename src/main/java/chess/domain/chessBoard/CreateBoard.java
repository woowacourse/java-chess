package chess.domain.chessBoard;

import chess.domain.chessPiece.ChessPiece;
import chess.domain.chessPiece.ChessPieceCache;
import chess.domain.position.Position;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CreateBoard {
    private static final String POSITION_COLUMN = "position";
    private static final String CHESS_PIECE_COLUMN = "chessPiece";

    public static Map<Position, ChessPiece> chessBoard(ResultSet whitePiece, ResultSet blackPiece) throws SQLException {
        Map<Position, ChessPiece> chessBoard = new HashMap<>();

        while (whitePiece.next()) {
            chessBoard.put(Position.of(whitePiece.getString(POSITION_COLUMN)),
                    ChessPieceCache.getChessPiece(whitePiece.getString(CHESS_PIECE_COLUMN)
                            , whitePiece.getString(POSITION_COLUMN)));
        }
        while (blackPiece.next()) {
            chessBoard.put(Position.of(blackPiece.getString(POSITION_COLUMN)),
                    ChessPieceCache.getChessPiece(blackPiece.getString(CHESS_PIECE_COLUMN)
                            , blackPiece.getString(POSITION_COLUMN)));
        }

        return chessBoard;
    }
}
