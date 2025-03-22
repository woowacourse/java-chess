package chess.piece;

import java.util.Map;
import chess.Color;
import chess.Column;
import chess.Position;
import chess.Row;

public class Pawn extends Piece {
    private int remainDoubleMoveChance;

    public Pawn(Color color) {
        super(color);
        remainDoubleMoveChance = 1;
    }

    @Override
    public boolean isAbleToMove(Position startPosition, Position endPosition, Map<Position, Piece> board) {
        if (super.getColor().isWhite() && (startPosition.row().toInt() > endPosition.row().toInt())) {
            return false;
        }

        if (super.getColor().isBlack() && (startPosition.row().toInt() < endPosition.row().toInt())) {
            return false;
        }

        // 백 팀
        if (super.getColor().isWhite()) {
            // 전진에 관한 로직
            if (startPosition.column() == endPosition.column()) {
                int rowDifference = Row.getDifference(startPosition.row(), endPosition.row());
                if (rowDifference == 1) {
                    Piece willCapturedPiece = board.get(endPosition);
                    if (willCapturedPiece != null) {
                        return false;
                    }
                    return true;
                }
                if (rowDifference == 2 && remainDoubleMoveChance > 0) {
                    Piece willCapturedPiece = board.get(endPosition);
                    if (willCapturedPiece != null) {
                        return false;
                    }

                    remainDoubleMoveChance -= 1; // 두칸 전진 기회 차감
                    return true;
                }
            }


            // 대각선 한칸 이동에 관한 로직
            if (Row.getDifference(startPosition.row(), endPosition.row()) == 1
            && Column.getDifference(startPosition.column(), endPosition.column()) == 1) {
                Piece willCapturedPiece = board.get(endPosition);
                if (willCapturedPiece == null) {
                    return false;
                }
                if (willCapturedPiece.getColor().isWhite()) {
                    return false;
                }
                return true;
            }

            return false;
        }

        // 흑 팀
        if (super.getColor().isBlack()) {
            // 전진에 대한 구현
            if (startPosition.column() == endPosition.column()) {
                int rowDifference = Row.getDifference(startPosition.row(), endPosition.row());
                if (rowDifference == 1) {
                    Piece willCapturedPiece = board.get(endPosition);
                    if (willCapturedPiece != null) {
                        return false;
                    }
                    return true;
                }
                if (rowDifference == 2 && remainDoubleMoveChance > 0) {
                    Piece willCapturedPiece = board.get(endPosition);
                    if (willCapturedPiece != null) {
                        return false;
                    }

                    remainDoubleMoveChance -= 1; // 두칸 전진 기회 차감
                    return true;
                }
            }


            // 대각선 한칸 이동에 관한 로직
            if (Row.getDifference(startPosition.row(), endPosition.row()) == 1
                    && Column.getDifference(startPosition.column(), endPosition.column()) == 1) {
                Piece willCapturedPiece = board.get(endPosition);
                if (willCapturedPiece == null) {
                    return false;
                }
                if (willCapturedPiece.getColor().isBlack()) {
                    return false;
                }
                return true;
            }
        }

        // 모두 불일치시 return false;
        return false;
    }
}
