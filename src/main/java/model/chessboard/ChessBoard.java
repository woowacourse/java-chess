package model.chessboard;

import java.util.Map;
import model.piece.Color;
import model.piece.PieceHolder;
import model.position.Position;
import model.position.Route;

public class ChessBoard {
    private final Map<Position, PieceHolder> chessBoard;
    private Color lastMovedColor;

    public ChessBoard() {
        this.chessBoard = ChessBoardFactory.create();
        lastMovedColor = Color.BLACK;
    }

    public void move(Position source, Position destination) {
        PieceHolder sourcePieceHolder = chessBoard.get(source);
        validateCurrentTurn(sourcePieceHolder);
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
                    throw new IllegalArgumentException("경로에 기물이 위치하여 이동할 수 없습니다.");
                });
    }

    private void validateCurrentTurn(PieceHolder source) {
        try {
            source.checkSameColor(lastMovedColor);
        } catch (Exception e) {
            throw new IllegalArgumentException("상대 턴입니다.");
        }
    }

    public Map<Position, PieceHolder> getChessBoard() {
        return Map.copyOf(chessBoard);
    }
}
