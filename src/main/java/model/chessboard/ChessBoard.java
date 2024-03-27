package model.chessboard;

import model.direction.Destination;
import model.direction.Route;
import model.direction.WayPoints;
import model.piece.Piece;
import model.position.Position;
import model.state.ChessState;

import java.util.Map;

public class ChessBoard {
    private final Map<Position, Piece> chessBoard;
    private final ChessState chessState;

    public ChessBoard() {
        this.chessBoard = ChessBoardFactory.create();
        this.chessState = new ChessState();
    }

    public void move(final Position source, final Position target) {
        Piece sourcePiece = chessBoard.get(source);
        Piece targetPiece = chessBoard.get(target);
        chessState.checkTheTurn(sourcePiece);
        Route route = sourcePiece.findRoute(source, target);
        WayPoints wayPoints = WayPoints.of(chessBoard, route, target);
        Destination destination = new Destination(target, targetPiece);
        sourcePiece.validateMoving(wayPoints, destination);
        sourcePiece.moveTo(destination);
        chessState.validateCheck(chessBoard);
        chessState.passTheTurn();
        chessState.validateCheck(chessBoard);
    }

    public Map<Position, Piece> getChessBoard() {
        return Map.copyOf(chessBoard);
    }
}
