package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.piece.pathStrategy.PathStrategy;
import chess.exception.NotMovableException;

import java.util.List;

import static chess.util.NullValidator.validateNull;

public abstract class Piece {
    private final String name;
    protected final PieceColor pieceColor;
    private final double score;
    protected Position position;
    private final PathStrategy pathStrategy;

    public Piece(String name, double score, PieceColor pieceColor, Position position, PathStrategy pathStrategy) {
        validateNull(name, score, pieceColor, position, pathStrategy);

        this.name = name;
        this.score = score;
        this.pieceColor = pieceColor;
        this.position = position;
        this.pathStrategy = pathStrategy;
    }

    public boolean isNone() {
        return pieceColor.isNoneColor();
    }

    public String getPieceColorName() {
        return this.pieceColor.getName();
    }

    public boolean isSameColor(Piece piece) {
        return this.pieceColor == piece.pieceColor;
    }

    public boolean isSameColor(PieceColor color) {
        return this.pieceColor == color;
    }

    public String getName() {
        return pieceColor.getPieceName(name);
    }

    public List<Position> getPathTo(Position targetPosition) {
        validateNull(targetPosition);
        validateEqualPosition(targetPosition);
        pathStrategy.validateDistance(this.position, targetPosition);

        return pathStrategy.createPath(this.position, targetPosition);
    }

    private void validateEqualPosition(Position targetPosition) {
        if (this.position.equals(targetPosition)) {
            throw new NotMovableException(String.format("현재 자리한 위치(%s)로는 이동할 수 없습니다.", targetPosition.getName()));
        }
    }

    public void changeTo(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public double getScore() {
        return score;
    }
}
