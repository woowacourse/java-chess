package chess.domain.testutil;

import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspoint.ChessPoint;

public class ChessPair {
    public final ChessPoint chessPoint;
    public final ChessPiece chessPiece;

    private ChessPair(ChessPoint chessPoint, ChessPiece chessPiece) {
        this.chessPoint = chessPoint;
        this.chessPiece = chessPiece;
    }

    public static ChessPair of(ChessPoint chessPoint, ChessPiece chessPiece) {
        return new ChessPair(chessPoint, chessPiece);
    }
}
