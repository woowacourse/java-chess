package model.chessboard;

import java.util.Map;
import model.piece.PieceHolder;
import model.position.Position;
import model.position.Route;

public class ChessBoard {
    private final Map<Position, PieceHolder> chessBoard;

    public ChessBoard() {
        this.chessBoard = ChessBoardFactory.create();
    }

    public void move(Position source, Position destination) {
        PieceHolder sourcePieceHolder = chessBoard.get(source);
        PieceHolder destinationPieceHolder = chessBoard.get(destination);
        Route route = sourcePieceHolder.findRoute(source, destination);
        validateMovingRoute(route);
        destinationPieceHolder.changeRoleTo(sourcePieceHolder);
        sourcePieceHolder.leave();
    }

    private void validateMovingRoute(Route route) {
        route.getPositions()
                .stream()
                .filter(position -> chessBoard.get(position)
                        .isOccupied())
                .findFirst()
                .ifPresent(position -> {
                    throw new IllegalArgumentException("목적 지점에 기물이 위치하여 이동할 수 없습니다.");
                });
    }

    public Map<Position, PieceHolder> getChessBoard() {
        return Map.copyOf(chessBoard);
    }
}
