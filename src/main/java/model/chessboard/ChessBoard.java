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
        chessState.validateCheck(chessBoard);   // 내 차례에 나의 기물의 이동으로 인해 내가 체크인 경우를 위해
        chessState.passTheTurn();
        chessState.validateCheck(chessBoard);   // 내 기물의 이동으로 인해 체크가 됐다면 상대방이 다음 턴에 바로 체크인 상태를 가지게 하기 위해
    }

    public Map<Position, Piece> getChessBoard() {
        return Map.copyOf(chessBoard);
    }
}
