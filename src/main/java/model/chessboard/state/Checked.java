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

public class Checked extends CurrentTurn {
    private final Route checkedRoute;

    protected Checked(Map<Position, PieceHolder> chessBoard, Color currentColor, Route checkedRoute) {
        super(chessBoard, currentColor);
        this.checkedRoute = checkedRoute;
    }

    @Override
    public DefaultState nextState() {
        if (hasAvailableBlockingMoves()) {
            return this;
        }
        return new CheckMate(chessBoard, currentColor);
    }

    private boolean hasAvailableBlockingMoves() {
        List<Position> checkedPositionsTowardKing = checkedRoute.getPositions();
        Set<Entry<Position, PieceHolder>> currentColorPieces = findPieceHoldersByColor(currentColor);
        return canDefendKing(currentColorPieces, checkedPositionsTowardKing);
    }

    private boolean canDefendKing(Set<Entry<Position, PieceHolder>> currentColorPieces, List<Position> attackingRoute) {
        boolean canBlock = false;
        for (Entry<Position, PieceHolder> entry : currentColorPieces) {
            Position currentPosition = entry.getKey();
            PieceHolder currentPieceHolder = entry.getValue();
            Set<Route> availableRoutes = attackingRoute.stream()
                    .filter(checkedPosition -> isReachablePosition(entry, checkedPosition))
                    .map(destination -> currentPieceHolder.findRoute(currentPosition, destination))
                    .collect(Collectors.toSet());
            canBlock = canBlock || availableRoutes.stream()
                    .anyMatch(route -> canMoveToBlockAttack(currentPieceHolder, route));
        }
        return canBlock;
    }

    private boolean canMoveToBlockAttack(PieceHolder defenderPieceHolder, Route avaliableRoute) {
        try {
            runMove(defenderPieceHolder, avaliableRoute);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
