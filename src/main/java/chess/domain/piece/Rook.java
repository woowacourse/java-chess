package chess.domain.piece;

import java.util.List;

import chess.domain.ChessBoard;
import chess.domain.piece.position.Position;
import chess.domain.piece.property.Color;
import chess.domain.piece.state.StartedRook;

public final class Rook extends Piece {
    private static final String NAME = "r";

    public Rook(Color color) {
        super(color, NAME, new StartedRook());
    }

    // @Override
    // public List<Position> getMovablePaths(Position source, ChessBoard board) {
    //     return state.getMovalblePaths();
    //
    //     List<Position> list = new ArrayList<>();
    //
    //     list.addAll(getPositions(source, board, Direction.Up));
    //     list.addAll(getPositions(source, board, Direction.Right));
    //     list.addAll(getPositions(source, board, Direction.Down));
    //     list.addAll(getPositions(source, board, Direction.Left));
    //
    //     return list;
    // }
    //
    // private List<Position> getPositions(Position source, ChessBoard board, Direction direction) {
    //     List<Position> list = new ArrayList<>();
    //
    //     Position currentPosition = source;
    //     Position nextPosition = currentPosition.getNext(direction); //움직인것
    //
    //     while ((currentPosition != nextPosition) && (!board.isFilled(nextPosition))) {
    //         list.add(nextPosition);
    //         currentPosition = nextPosition;
    //         nextPosition = currentPosition.getNext(direction);
    //     }
    //
    //     if (canKill(board, nextPosition)) {
    //         list.add(nextPosition);
    //     }
    //
    //     return list;
    // }
    //
    // private boolean canKill(ChessBoard board, Position currentPosition) {
    //     return board.isFilled(currentPosition) && !isSameColor(board.getPiece(currentPosition));
    // }
}
