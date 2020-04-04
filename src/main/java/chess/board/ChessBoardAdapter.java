package chess.board;

import chess.coordinate.Coordinate;
import chess.observer.Observable;
import chess.observer.Publishable;
import chess.piece.Piece;
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
    public void push(final Piece piece) {
        observable.update(piece);
    }

    public boolean isNotSameTeam(final String source, final Team currentTeam) {
        return chessBoard.isNotSameTeam(source, currentTeam);
    }

    public Piece move(final String source, final String target) {
        Piece deadPiece = chessBoard.replace(source, target);
        push(deadPiece);
        return deadPiece;
    }

    public double calculateScore(final Team team) {
        return chessBoard.calculateScore(team);
    }

    public Map<Coordinate, Tile> getChessBoard() {
        return chessBoard.getChessBoard();
    }

    public Piece findByKey(String key) {
        return chessBoard.findByKey(key);
    }
}
