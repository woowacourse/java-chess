package model.board;

import model.Position;
import model.piece.Piece;
import model.piece.PieceFactory;

import java.util.*;

public class Board implements Observer {
    public static final int SIZE = 8;

    private Map<Position, Piece> pieces;

    Board(Map<Position, Piece> pieces) {
        this.pieces = pieces;
    }

    public Piece getPieceAt(Position position) {
        return pieces.getOrDefault(position, PieceFactory.empty(position));
    }

    public List<Piece> getPieces() {
        List<Piece> pieces = new ArrayList<>(this.pieces.values());
        return pieces;
    }

    public BoardView getBoardView() {
        return new BoardView(pieces);
    }

    @Override
    // TODO: 2019-06-23 빈칸으로 움직이거나 공격했거나
    public void update(Observable o, Object arg) {
        Piece movedPiece = (Piece) o;
        Position lastPosition = (Position) arg;

        pieces.remove(lastPosition);
        pieces.put(movedPiece.getPosition(), movedPiece);
    }
}
