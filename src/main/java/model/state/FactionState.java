package model.state;

import model.direction.Destination;
import model.direction.Route;
import model.direction.WayPoints;
import model.piece.Color;
import model.piece.Piece;
import model.piece.role.RoleStatus;
import model.position.Position;

import java.util.HashMap;
import java.util.Map;

public sealed interface FactionState permits BlackFaction, BlackFactionCheck, WhiteFaction, WhiteFactionCheck {

    void checkSameFaction(final Piece piece);

    FactionState pass();

    boolean isCheck(final Map<Position, Piece> chessBoard);

    FactionState check();

    default Map<Position, Piece> factionOf(final Map<Position, Piece> chessBoard, final Color color) {
        Map<Position, Piece> faction = new HashMap<>();
        chessBoard.entrySet()
                  .stream()
                  .filter(entry -> entry.getValue().color() == color)
                  .forEach(entry -> faction.put(entry.getKey(), entry.getValue()));
        return faction;
    }

    default Position positionOfKing(final Map<Position, Piece> chessBoard, final Color color) {
        return chessBoard.entrySet()
                         .stream()
                         .filter(entry -> entry.getValue().role().status() == RoleStatus.KING
                                 && entry.getValue().color() == color)
                         .map(Map.Entry::getKey)
                         .findFirst()
                         .get();
        // TODO 킹이 없는 경우는 게임이 종료된 경우 -> 3단계
    }

    default boolean possibleAttacked(final Map<Position, Piece> chessBoard, final Position kingPosition, final Map.Entry<Position, Piece> entry) {
        Position position = entry.getKey();
        Piece piece = entry.getValue();
        try {
            Route route = piece.findRoute(position, kingPosition);
            piece.validateMoving(WayPoints.of(chessBoard, route, kingPosition), new Destination(kingPosition, chessBoard.get(kingPosition)));
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
