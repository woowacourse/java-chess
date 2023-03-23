package chess.domain.board;

import chess.domain.Position;
import chess.domain.exception.IllegalPieceMoveException;
import chess.domain.piece.Color;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private final Map<Position, Piece> piecePosition;

    public Board(Map<Position, Piece> pieces) {
        piecePosition = new HashMap<>(pieces);
    }

    public void movePiece(Position origin, Position destination) {
        validateMoveRequest(origin, destination);
        Piece targetPiece = piecePosition.get(origin);
        if (!targetPiece.canMove(origin, destination, piecePosition.get(destination))) {
            throw new IllegalPieceMoveException();
        }
        piecePosition.put(destination, targetPiece);
        piecePosition.put(origin, EmptyPiece.getInstance());
    }

    private void validateMoveRequest(Position origin, Position destination) {
        if (piecePosition.get(origin) == EmptyPiece.getInstance()) {
            throw new IllegalPieceMoveException();
        }
        if (!piecePosition.get(origin).canJump()) {
            checkPath(origin, destination);
        }
    }

    private void checkPath(Position origin, Position destination) {
        List<Position> straightPath = origin.createStraightPath(destination);
        for (Position position : straightPath) {
            if (piecePosition.get(position) != EmptyPiece.getInstance()) {
                throw new IllegalPieceMoveException();
            }
        }
    }

    public Score getScoreOf(Color color) {
        return new Score(piecePosition, color);
    }

    public Map<Position, Piece> getBoard() {
        return new HashMap<>(piecePosition);
    }

}
