package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public class Path {

    private final List<Position> positions;

    public Path(List<Position> positions) {
        this.positions = positions;
    }

    public boolean isAble(Position position) {
        if (positions.contains(position)) {
            return true;
        }
        throw new IllegalArgumentException("해당 말이 이동할 수 없는 위치입니다.");
    }

    public List<Position> removeObstacleInPath(Piece piece, Board board) {
        List<Position> cleanPath = new ArrayList<>();
        if (piece.isPawn()) {
            calculatePawnPath(piece, board, cleanPath);
            return cleanPath;
        }
        calculateNonePawnPath(piece, board, cleanPath);
        return cleanPath;
    }

    private void calculatePawnPath(Piece piece, Board board, List<Position> cleanPath) {
        for (Position position : positions) {
            Piece thatPiece = board.findPieceBy(position);
            Position thisPosition = board.findPositionBy(piece);
            if (piece.isSameSide(thatPiece)) {
                break;
            }
            if (piece.hasColor(PieceColor.WHITE) && !thisPosition.isWhitePawnStartLine()) {
                if (thisPosition.rowGap(position) != 1) {
                    break;
                }
            }
            if (piece.hasColor(PieceColor.BLACK) && !thisPosition.isBlackPawnStartLine()) {
                if (thisPosition.rowGap(position) != 1) {
                    break;
                }
            }
            if (thisPosition.isDiagonal(position)) {
                if (thatPiece.isEmpty()) {
                    break;
                }
            }
            if (thisPosition.isStraight(position)) {
                if (piece.isEnemy(thatPiece)) {
                    break;
                }
            }
            cleanPath.add(position);
        }
    }

    private void calculateNonePawnPath(Piece piece, Board board, List<Position> cleanPath) {
        for (Position position : positions) {
            Piece thatPiece = board.findPieceBy(position);
            if (!piece.isSameSide(thatPiece)) {
                break;
            }
            cleanPath.add(position);
            if (piece.isEnemy(thatPiece)) {
                break;
            }
        }
    }

    public List<Position> positions() {
        return positions;
    }
}
