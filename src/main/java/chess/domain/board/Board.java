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
    private final Color tune;

    public Board(Map<Position, Piece> pieces, Color color) {
        piecePosition = new HashMap<>(pieces);
        tune = color;
    }

    public Board movePiece(Position origin, Position destination) {
        validateMoveRequest(origin, destination);
        Piece targetPiece = piecePosition.get(origin);
        if (!targetPiece.canMove(origin, destination, piecePosition.get(destination))) {
            throw new IllegalPieceMoveException();
        }
        return new Board(movedBoard(origin, destination), nextTune());
    }

    private Map<Position, Piece> movedBoard(Position origin, Position destination) {
        HashMap<Position, Piece> movedBoard = new HashMap<>(piecePosition);
        movedBoard.put(destination, piecePosition.get(origin));
        movedBoard.put(origin, EmptyPiece.getInstance());
        return movedBoard;
    }

    private Color nextTune() {
        if (tune == Color.BLACK) {
            return Color.WHITE;
        }
        return Color.BLACK;
    }

    private void validateMoveRequest(Position origin, Position destination) {
        if (piecePosition.get(origin).getColor() != tune) {
            throw new IllegalPieceMoveException();
        }
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

    public Map<Position, Piece> getBoardData() {
        return new HashMap<>(piecePosition);
    }

}
