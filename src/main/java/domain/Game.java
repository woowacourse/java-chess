package domain;

import domain.piece.EmptyPiece;
import domain.piece.Piece;
import domain.piece.Position;

import java.util.List;
import java.util.Map;

public class Game {
    private final Map<Position, Piece> chessBoard;

    public Game(Map<Position, Piece> chessBoard) {
        this.chessBoard = chessBoard;
    }

    public static Game create() {
        return new Game(new ChessBoardGenerator().generate());
    }

    public void move(Position sourcePosition, Position targetPosition) {
        Piece sourcePiece = chessBoard.get(sourcePosition);
        Piece targetPiece = chessBoard.get(targetPosition);
        if (sourcePiece.isEmptyPiece()) {
            throw new IllegalArgumentException("source위치에 말이 없습니다.");
        }
        if (!sourcePiece.isMovable(targetPiece, sourcePosition, targetPosition)) {
            throw new IllegalArgumentException("올바른 움직임이 아닙니다.");
        }
        List<Position> path = sourcePiece.collectPath(sourcePosition, targetPosition);
        for (Position position : path) {
            if (!chessBoard.get(position).isEmptyPiece()) {
                throw new IllegalArgumentException("경로에 다른 말이 있습니다.");
            }
        }
        chessBoard.put(sourcePosition, new EmptyPiece());
        chessBoard.put(targetPosition, sourcePiece);
    }
}
