package chess.board;

import chess.coordinate.Coordinate;
import chess.observer.Observable;
import chess.observer.Publishable;
import chess.piece.Team;

import java.util.Map;

public class ChessBoardAdapter implements Publishable {
    private final ChessBoard chessBoard;
    private Observable observable;

    public ChessBoardAdapter(final ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    @Override
    public void subscribe(final Observable observable) {
        this.observable = observable;
    }

    @Override
    public void push(final Object object) {
        observable.update(object);
    }

    public boolean isNotSameTeam(final String source, final Team currentTeam) {
        return chessBoard.isNotSameTeam(source, currentTeam);
    }

    public boolean move(final String source, final String target) {
        try {
            push(chessBoard.replace(source, target));
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public double calculateScore(final Team currentTeam) {
        return chessBoard.calculateScore(currentTeam);
    }

    public Map<Coordinate, Tile> getChessBoard() {
        return chessBoard.getChessBoard();
    }
}
