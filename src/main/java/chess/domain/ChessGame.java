package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.position.move.PieceMove;
import java.util.List;

public class ChessGame {
    private static final String UNABLE_TO_MOVE = "이동할 수 없습니다.";
    private final PiecesPosition piecesPosition;

    public ChessGame(PiecesPosition piecesPosition) {
        this.piecesPosition = piecesPosition;
    }

    public void move(Position fromPosition, Position toPosition) {
        Piece piece = piecesPosition.choicePiece(fromPosition);
        PieceMove pieceMove = piece.getMovement(fromPosition, toPosition);

        List<Position> pathPositions = fromPosition.getBetweenPositions(toPosition);

        for (Position position : pathPositions) {
            validateMovable(pieceMove, position);
        }
    }

    private void validateMovable(PieceMove pieceMove, Position position) {
        Piece betweenPiece = piecesPosition.choicePiece(position);
        if (!pieceMove.isMovable(betweenPiece)) {
            throw new IllegalArgumentException(UNABLE_TO_MOVE);
        }
    }

    public PiecesPosition getPiecesPosition() {
        return piecesPosition;
    }
}

