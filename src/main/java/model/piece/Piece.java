package model.piece;

import model.Coordinate;
import model.Position;
import model.board.BoardView;

import java.util.List;
import java.util.Observable;

// 백이 위에 있고 흑이 밑에 있다.
public abstract class Piece extends Observable {
    protected PieceColor pieceColor;
    protected Position position;

    public Piece(PieceColor pieceColor, Position position) {
        this.pieceColor = pieceColor;
        this.position = position;
    }

    public PieceColor getPieceColor() {
        return pieceColor;
    }

    public Position getPosition() {
        return position;
    }

    public Coordinate x() {
        return position.getX();
    }

    public abstract double getScore();

    public void moveTo(Position position) {
        // TODO: 2019-06-23 validationCheck
        Position lastPosition = this.position;
        this.position = position;
        setChanged();
        notifyObservers(lastPosition);
        clearChanged();
    }

    public abstract List<Position> getMovablePositions(BoardView board);

    protected boolean isMovableTo(Position position, BoardView boardView) {
        return pieceColor != boardView.getPieceColorAt(position);
    }

    public boolean isEnemyOf(PieceColor other) {
        return other != PieceColor.EMPTY && pieceColor != other;
    }

    public boolean isPawn() {
        return false;
    }
}
