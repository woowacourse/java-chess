package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.ChessBoardPosition;
import chess.domain.Team;

public class Pawn extends ChessPiece {
    private static final int NO_MOVEMENT = 0;
    private static final int DEFAULT_DISTANCE = 1;
    private static final int OPTIONAL_DISTANCE = 1;
    private static final int WHITE_INITIAL_ROW_POSITION = 2;
    private static final int BLACK_INITIAL_ROW_POSITION = 7;
    private static final double SCORE = 1.0;

    public Pawn(Team team) {
        super(SCORE, team);
    }

    private int calculateRowDistance(int sourceRow, int targetRow) {
        if (team.isWhite()) {
            return targetRow - sourceRow;
        }
        return sourceRow - targetRow;
    }

    private int calculateColumnDistance(char sourceColumn, char targetColumn) {
        return Math.abs(sourceColumn - targetColumn);
    }

    @Override
    public boolean isMovable(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition,
                             ChessBoard chessBoard) {
        int rowDistance = calculateRowDistance(sourcePosition.getRow(), targetPosition.getRow());
        int columnDistance = calculateColumnDistance(sourcePosition.getColumn(), targetPosition.getColumn());

        if (chessBoard.existChessPieceOf(targetPosition, team.reverse())) {
            return isDiagonalMove(rowDistance, columnDistance);
        }
        if (rowDistance == DEFAULT_DISTANCE + OPTIONAL_DISTANCE && columnDistance == NO_MOVEMENT) {
            return isOptionalMovable(sourcePosition, chessBoard);
        }
        return rowDistance == DEFAULT_DISTANCE && columnDistance == NO_MOVEMENT;
    }

    private boolean isDiagonalMove(int rowDistance, int columnDistance) {
        return rowDistance == DEFAULT_DISTANCE && columnDistance == DEFAULT_DISTANCE;
    }

    private boolean isOptionalMovable(ChessBoardPosition sourcePosition, ChessBoard chessBoard) {
        if (team.isWhite() && sourcePosition.isSameRow(WHITE_INITIAL_ROW_POSITION)) {
            return isUnobstructed(sourcePosition, chessBoard, DEFAULT_DISTANCE);
        }
        if (team.isBlack() && sourcePosition.isSameRow(BLACK_INITIAL_ROW_POSITION)) {
            return isUnobstructed(sourcePosition, chessBoard, -DEFAULT_DISTANCE);
        }
        return false;
    }

    private boolean isUnobstructed(ChessBoardPosition sourcePosition, ChessBoard chessBoard, int forwardDirection) {
        ChessBoardPosition pathPosition = ChessBoardPosition.of(sourcePosition.getColumn(),
                sourcePosition.getRow() + forwardDirection);
        return !chessBoard.existChessPieceAt(pathPosition);
    }
}
