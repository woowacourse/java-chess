package model.chessboard.state;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import model.piece.Color;
import model.piece.PieceHolder;
import model.position.Position;
import model.position.Route;

public class CurrentTurn extends DefaultState {
    public CurrentTurn(Map<Position, PieceHolder> chessBoard, Color currentColor) {
        super(chessBoard, currentColor);
    }

    @Override
    public DefaultState move(Position source, Position destination) {
        PieceHolder sourcePieceHolder = chessBoard.get(source);
        checkTurn(sourcePieceHolder);
        Route route = sourcePieceHolder.findRoute(source, destination);
        runMove(sourcePieceHolder, route);
        if (isCheckedBy(currentColor)) {
            return new Checked(chessBoard, currentColor.opponent(), route.reverseRouteTowardSource());
        }
        return this;
    }

    protected void runMove(PieceHolder sourcePieceHolder, Route route) {
        Map<Position, PieceHolder> chessBoardBackUp = Map.copyOf(chessBoard);
        sourcePieceHolder.progressMoveToDestination(pieceHoldersInRoute(route));
        if (isCheckedBy(currentColor.opponent())) {
            chessBoard = chessBoardBackUp;
            throw new IllegalArgumentException("해당 위치는 체크이므로 움직일 수 없습니다.");
        }
    }

    private void checkTurn(PieceHolder selectedPieceHolderColor) {
        if (!selectedPieceHolderColor.hasSameColor(this.currentColor)) {
            throw new IllegalArgumentException(currentColor.name() + " 진영의 기물을 움직여야 합니다.");
        }
    }

    @Override
    protected boolean isCheckedBy(Color attackingColor) {
        Position kingPosition = findKingPosition(attackingColor.opponent());
        Set<Entry<Position, PieceHolder>> currentTurnPieces = findPieceHoldersByColor(attackingColor);
        return currentTurnPieces.stream()
                .anyMatch(positionPieceHolderEntry -> isReachablePosition(positionPieceHolderEntry, kingPosition));
    }

    protected Set<Entry<Position, PieceHolder>> findPieceHoldersByColor(Color color) {
        return chessBoard.entrySet()
                .stream()
                .filter(positionPieceHolderEntry -> positionPieceHolderEntry.getValue()
                        .hasSameColor(color))
                .collect(Collectors.toSet());
    }

    protected Position findKingPosition(Color targetColor) {
        return findPieceHoldersByColor(targetColor).stream()
                .filter(positionPieceHolderEntry -> positionPieceHolderEntry.getValue()
                        .isKing())
                .findFirst()
                .map(Entry::getKey)
                .orElseThrow(() -> new IllegalStateException("찾고자 하는 King이 존재하지 않습니다."));
    }

    protected boolean isReachablePosition(Entry<Position, PieceHolder> positionPieceEntry,
                                        Position targetPosition) {
        Position currentPosition = positionPieceEntry.getKey();
        PieceHolder currentPieceHolder = positionPieceEntry.getValue();
        try {
            Route routeToKing = currentPieceHolder.findRoute(currentPosition, targetPosition);
            return currentPieceHolder.checkPieceHoldersOnMovingRoute(pieceHoldersInRoute(routeToKing));
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    protected List<PieceHolder> pieceHoldersInRoute(Route route) {
        return route.getPositions()
                .stream()
                .map(chessBoard::get)
                .toList();
    }

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public DefaultState nextState() {
        return new CurrentTurn(chessBoard, currentColor.opponent());
    }
}
