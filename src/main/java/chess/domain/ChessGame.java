package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.position.move.PieceMove;
import java.util.List;

public class ChessGame {
    private static final String UNABLE_TO_MOVE = "이동할 수 없습니다.";
    private static final String EMPTY_CHOICE = "빈 칸은 선택할 수 없습니다.";

    private final PiecesPosition piecesPosition;

    public ChessGame(PiecesPosition piecesPosition) {
        this.piecesPosition = piecesPosition;
    }

    public void move(Position fromPosition, Position toPosition) {
        Piece fromPiece = piecesPosition.choicePiece(fromPosition);
        Piece toPiece = piecesPosition.choicePiece(toPosition);
        validateEmpty(fromPiece);
        validateSameCamp(fromPiece, toPiece);

        PieceMove pieceMove = fromPiece.getMovement(fromPosition, toPosition);

        List<Position> pathPositions = fromPosition.getBetweenPositions(toPosition);
        for (Position position : pathPositions) {
            validateMovableBetween(pieceMove, position);
        }

        validateMovable(toPiece, pieceMove);
        piecesPosition.movePieceOn(fromPosition, toPosition);
    }

    private void validateMovable(Piece toPiece, PieceMove pieceMove) {
        if (!pieceMove.isMovable(toPiece, true)) {
            throw new IllegalArgumentException(UNABLE_TO_MOVE);
        }
    }

    private void validateEmpty(Piece fromPiece) {
        if (fromPiece.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_CHOICE);
        }
    }

    private void validateMovableBetween(PieceMove pieceMove, Position position) {
        Piece betweenPiece = piecesPosition.choicePiece(position);
        if (!pieceMove.isMovable(betweenPiece, false)) {
            throw new IllegalArgumentException(UNABLE_TO_MOVE);
        }
    }

    private void validateSameCamp(Piece fromPiece, Piece toPiece) {
        if (fromPiece.isSameCamp(toPiece)) {
            throw new IllegalArgumentException(UNABLE_TO_MOVE);
        }
    }

    public PiecesPosition getPiecesPosition() {
        return piecesPosition;
    }
}

