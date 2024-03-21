package model;

import java.util.Map;
import model.piece.PieceHolder;
import model.position.Position;
import model.position.Route;

public class ChessBoard {
    private final Map<Position, PieceHolder> chessBoard;

    public ChessBoard() {
        this.chessBoard = ChessBoardFactory.create();
    }

    public Map<Position, PieceHolder> getChessBoard() {
        return Map.copyOf(chessBoard);
    }

    public void move(Position source, Position destination) {
        PieceHolder sourcePiece = chessBoard.get(source);
        PieceHolder destinationPiece = chessBoard.get(destination);
        Route route = sourcePiece.findRoute(source, destination);
        route.getPositions()
                .stream()
                .filter(position -> chessBoard.get(position)
                        .isOccupied())
                .findFirst()
                .ifPresent(position -> {
                    throw new IllegalArgumentException("목적 지점에 기물이 위치하여 이동할 수 없습니다.");
                });
        destinationPiece.moveFrom(sourcePiece);
        sourcePiece.leave();
    }
}
