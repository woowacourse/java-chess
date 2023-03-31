package chess;

import chess.piece.ChessPiece;
import chess.piece.Shape;
import chess.piece.Side;
import chess.position.Position;

public class ChessScore {
    private final ChessBoard chessBoard;

    public ChessScore(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public double calculateScore(Side side) {
        double scoreSum = 0;
        for (int horizontal = 1; horizontal <= 8; horizontal++) {
            scoreSum = getScoreSumByCol(scoreSum, horizontal, side);
        }
        return scoreSum;
    }

    private double getScoreSumByCol(double scoreSum, int horizontal, Side side) {
        double pawnCountByCol = 0;
        for (int vertical = 1; vertical <= 8; vertical++) {
            ChessPiece chessPiece = chessBoard.getChessPieceByPosition(Position.initPosition(horizontal, vertical));
            pawnCountByCol += checkPawnCount(chessPiece, side);
            scoreSum += chessPiece.getScore(side);
        }
        if (pawnCountByCol >= 2) {
            scoreSum -= (pawnCountByCol / 2);
        }
        return scoreSum;
    }

    private double checkPawnCount(ChessPiece chessPiece, Side side) {
        if (chessPiece.getShape().equals(Shape.PAWN) && chessPiece.getSide().equals(side)) {
            return 1;
        }
        return 0;
    }
}
