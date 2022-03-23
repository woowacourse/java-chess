package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.Position;

public final class Knight extends Piece {

    private static final String KNIGHT_NAME = "K";
    private static final int KNIGHT_MOVABLE_DISTANCE = 3;

    public Knight(Color color, Position position) {
        super(color, KNIGHT_NAME, position);
    }

    public Knight(Knight knight, Position position) {
        super(knight, position);
    }

    public Knight move(Position position, ChessBoard chessBoard) {
        if (!isPossibleToMove(position)) {
            throw new IllegalStateException("나이트는 L자 형태로만 움직일 수 있습니다.");
        }
        if (!chessBoard.isPositionEmpty(position)) {
            Piece chessPiece = chessBoard.pieceByPosition(position);

            if (isEqualColor(chessPiece)) {
                throw new IllegalStateException("해당 위치에 같은 색상의 기물이 존재하여 움직일 수 없습니다.");
            }
        }

        // 그 기물이 다른 기물인지
        // 다른 기물이 있으면 먹고 해당 위치 이동 반환

        return new Knight(this, position);
    }

    private boolean isPossibleToMove(Position position) {
        return !position.equalsColumnOrRow(getPosition())
                && position.calculateDistance(getPosition()) == KNIGHT_MOVABLE_DISTANCE;
    }
}
