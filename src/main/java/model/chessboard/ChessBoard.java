package model.chessboard;

import model.piece.Piece;
import model.position.Position;
import model.direction.Route;

import java.util.List;
import java.util.Map;

public class ChessBoard {
    private final Map<Position, Piece> chessBoard;

    public ChessBoard() {
        this.chessBoard = ChessBoardFactory.create();
    }

    public void move(final Position source, final Position target) {
        Piece sourcePiece = chessBoard.get(source);
        Piece targetPiece = chessBoard.get(target);
        Route route = sourcePiece.findRoute(source, target);
        validateNotExistWayPoints(route, target);
        sourcePiece.moveTo(route.getDirection(), targetPiece);
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
