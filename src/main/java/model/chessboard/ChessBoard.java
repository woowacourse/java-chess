package model.chessboard;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
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

    private void checkTurn(PieceHolder source) {
        if (!source.isSameColor(currentTurnColor)) {
            throw new IllegalArgumentException(currentTurnColor.name() + " 진영의 기물을 움직여야 합니다.");
        }
    }

    private boolean isCheck() {
        Position opponentKingPosition = findOpponentKingPosition();
        Set<Entry<Position, PieceHolder>> currentTurnPieces = chessBoard.entrySet()
                .stream()
                .filter(positionPieceHolderEntry -> positionPieceHolderEntry.getValue()
                        .isSameColor(currentTurnColor))
                .collect(Collectors.toSet());
        return currentTurnPieces.stream()
                .anyMatch(positionPieceHolderEntry -> isFacingKing(positionPieceHolderEntry, opponentKingPosition));
    }

    private boolean isFacingKing(Entry<Position, PieceHolder> positionPieceEntry, Position opponentKingPosition) {
        Position currentPosition = positionPieceEntry.getKey();
        PieceHolder currentPieceHolder = positionPieceEntry.getValue();
        try {
            Route routeToKing = currentPieceHolder.findRoute(currentPosition, opponentKingPosition);
            return currentPieceHolder.isCheck(pieceHoldersInRoute(routeToKing));
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private List<PieceHolder> pieceHoldersInRoute(Route route) {
        return route.getPositions()
                .stream()
                .map(chessBoard::get)
                .toList();
    }

    private Position findOpponentKingPosition() {
        return chessBoard.entrySet()
                .stream()
                .filter(positionPieceHolderEntry -> positionPieceHolderEntry.getValue()
                        .isKing() && !positionPieceHolderEntry.getValue()
                        .isSameColor(currentTurnColor))
                .findFirst()
                .map(Entry::getKey)
                .orElseThrow(() -> new IllegalStateException("상대방 King이 존재하지 않습니다."));
    }

    private void changeTurn() {
        currentTurnColor = currentTurnColor.next();
    }

    public Map<Position, PieceHolder> getChessBoard() {
        return Map.copyOf(chessBoard);
    }
}
