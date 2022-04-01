package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.ChessBoardPosition;
import chess.domain.Team;

public class Pawn extends ChessPiece {
    private static final int DEFAULT_DISTANCE = 1;
    private static final int OPTIONAL_DISTANCE = 1;
    private static final int WHITE_INITIAL_ROW_POSITION = 2;
    private static final int BLACK_INITIAL_ROW_POSITION = 7;
    private static final double SCORE = 1.0;

    public Pawn(Team team) {
        super(SCORE, team);
    }

    private boolean isWhiteInitialPosition(ChessBoardPosition position) {
        return position.isSameRow(WHITE_INITIAL_ROW_POSITION);
    }

    private boolean isBlackInitialPosition(ChessBoardPosition position) {
        return position.isSameRow(BLACK_INITIAL_ROW_POSITION);
    }

    private int calculateRowDistance(int highRow, int lowRow) {
        return highRow - lowRow;
    }

    private int calculateColumnDistance(char highColumn, char lowColumn) {
        return Math.abs(highColumn - lowColumn);
    }

    @Override
    public boolean isMovable(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition,
                             ChessBoard chessBoard) {
        if (isDiagonalMove(sourcePosition, targetPosition)) {
            return chessBoard.existChessPieceOf(targetPosition, team.reverse());
        }

        if (team.isWhite()) {
            return isMovableWhite(sourcePosition, targetPosition, chessBoard);
        }

        return isMovableBlack(sourcePosition, targetPosition, chessBoard);
    }

    private boolean isDiagonalMove(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition) {
        if (team.isWhite()) {
            return isDiagonalMoveWhite(sourcePosition, targetPosition);
        }
        return isDiagonalMoveBlack(sourcePosition, targetPosition);
    }

    private boolean isDiagonalMoveBlack(ChessBoardPosition sourcePosition,
                                        ChessBoardPosition targetPosition) {
        int rowDistance = calculateRowDistance(sourcePosition.getRow(), targetPosition.getRow());
        int columnDistance = calculateColumnDistance(sourcePosition.getColumn(), targetPosition.getColumn());
        return rowDistance == DEFAULT_DISTANCE && columnDistance == DEFAULT_DISTANCE;
    }

    private boolean isDiagonalMoveWhite(ChessBoardPosition sourcePosition,
                                        ChessBoardPosition targetPosition) {
        int rowDistance = calculateRowDistance(targetPosition.getRow(), sourcePosition.getRow());
        int columnDistance = calculateColumnDistance(targetPosition.getColumn(), sourcePosition.getColumn());
        return rowDistance == DEFAULT_DISTANCE && columnDistance == DEFAULT_DISTANCE;
    }

    private boolean isMovableBlack(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition,
                                   ChessBoard chessBoard) {
        if (isBlackInitialPosition(sourcePosition)) {
            return isMovableBlackInitialPosition(sourcePosition, targetPosition, chessBoard);
        }
        return calculateRowDistance(sourcePosition.getRow(), targetPosition.getRow()) == DEFAULT_DISTANCE;
    }

    private boolean isMovableBlackInitialPosition(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition,
                                                  ChessBoard chessBoard) {
        int rowDistance = calculateRowDistance(sourcePosition.getRow(), targetPosition.getRow());
        if (rowDistance == DEFAULT_DISTANCE + OPTIONAL_DISTANCE) {
            return isUnobstructed(sourcePosition, chessBoard, -DEFAULT_DISTANCE);
        }
        return rowDistance == DEFAULT_DISTANCE;
    }

    private boolean isMovableWhite(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition,
                                   ChessBoard chessBoard) {
        if (isWhiteInitialPosition(sourcePosition)) {
            return isMovableWhiteInitialPosition(sourcePosition, targetPosition, chessBoard);
        }
        return calculateRowDistance(targetPosition.getRow(), sourcePosition.getRow()) == DEFAULT_DISTANCE;
    }

    private boolean isMovableWhiteInitialPosition(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition,
                                                  ChessBoard chessBoard) {
        int rowDistance = calculateRowDistance(targetPosition.getRow(), sourcePosition.getRow());
        if (rowDistance == DEFAULT_DISTANCE + OPTIONAL_DISTANCE) {
            return isUnobstructed(sourcePosition, chessBoard, DEFAULT_DISTANCE);
        }
        return rowDistance == DEFAULT_DISTANCE;
    }

    private boolean isUnobstructed(ChessBoardPosition sourcePosition, ChessBoard chessBoard, int forwardDirection) {
        ChessBoardPosition pathPosition = ChessBoardPosition.of(sourcePosition.getColumn(),
                sourcePosition.getRow() + forwardDirection);
        return !chessBoard.existChessPieceAt(pathPosition);
    }
}
