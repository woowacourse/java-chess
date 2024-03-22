package model.chessboard;

import java.util.List;
import java.util.Map;
import model.piece.Color;
import model.piece.PieceHolder;
import model.position.Position;
import model.position.Route;

public class ChessBoard {
    private final Map<Position, PieceHolder> chessBoard;
    private Color currentTurnColor;

    public ChessBoard() {
        this.chessBoard = ChessBoardFactory.create();
        currentTurnColor = Color.WHITE;
    }

    public void move(Position source, Position destination) {
        PieceHolder sourcePieceHolder = chessBoard.get(source);
        checkTurn(sourcePieceHolder);
        Route route = sourcePieceHolder.findRoute(source, destination);
        sourcePieceHolder.progressMoveToDestination(pieceHoldersInRoute(route));
        changeTurn();
    }

    private List<PieceHolder> pieceHoldersInRoute(Route route) {
        return route.getPositions()
                .stream()
                .map(chessBoard::get)
                .toList();
    }

    private void checkTurn(PieceHolder source) {
        if (!source.isSameColor(currentTurnColor)) {
            throw new IllegalArgumentException("상대 턴입니다.");
        }
    }

    private void changeTurn(){
        currentTurnColor = currentTurnColor.next();
    }

    public Map<Position, PieceHolder> getChessBoard() {
        return Map.copyOf(chessBoard);
    }
}
