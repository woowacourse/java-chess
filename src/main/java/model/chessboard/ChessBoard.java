package model.chessboard;

import model.direction.Route;
import model.piece.Piece;
import model.position.Position;
import model.state.ChessState;

import java.util.List;
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
        chessState.checkTheTurn(sourcePiece);
        Piece targetPiece = chessBoard.get(target);
        Route route = sourcePiece.findRoute(source, target);
        validateNotExistWayPoints(route, target);
        sourcePiece.moveTo(route.getDirection(), targetPiece);
        chessState.passTheTurn();
    }


    private void validateNotExistWayPoints(final Route route, final Position target) {
        route.exclude(target);
        List<Position> positions = route.positions();
        positions.stream()
                 .map(chessBoard::get)
                 .filter(Piece::isOccupied)
                 .findAny()
                 .ifPresent(position -> {
                     throw new IllegalArgumentException("목적 지점까지의 경로에 기물이 위치하여 이동할 수 없습니다.");
                 });
    }

    public Map<Position, Piece> getChessBoard() {
        return Map.copyOf(chessBoard);
    }
}
