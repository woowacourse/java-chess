package model.piece;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Objects;
import model.piece.role.Role;
import model.piece.role.Square;
import model.position.Position;
import model.position.Route;

public class PieceHolder {
    private Role role;

    public PieceHolder(Role role) {
        this.role = role;
    }

    public Route findRoute(Position source, Position destination) {
        return this.role.findDirectRoute(source, destination);
    }

    public void progressMoveToDestination(List<PieceHolder> pieceHoldersInRoute) {
        Deque<PieceHolder> pieceHolders = new ArrayDeque<>(pieceHoldersInRoute);
        PieceHolder destination = Objects.requireNonNull(pieceHolders.pollLast());
        List<Role> rolesInRoute = extractRolesFromPieceHolders(pieceHolders);
        this.role.traversalRoles(rolesInRoute, destination.role);
        destination.changeRoleTo(role);
        leave();
    }

    public boolean checkPieceHoldersOnMovingRoute(List<PieceHolder> pieceHoldersInRoute) {
        Deque<PieceHolder> pieceHolders = new ArrayDeque<>(pieceHoldersInRoute);
        PieceHolder destination = Objects.requireNonNull(pieceHolders.pollLast());
        List<Role> rolesInRoute = extractRolesFromPieceHolders(pieceHolders);
        try {
            this.role.traversalRoles(rolesInRoute, destination.role);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private List<Role> extractRolesFromPieceHolders(Deque<PieceHolder> pieceHoldersInRoute) {
        return pieceHoldersInRoute.stream()
                .map(pieceHolder -> pieceHolder.role)
                .toList();
    }

    public boolean isKing() {
        return role.isKing();
    }

    private void changeRoleTo(Role sourceRole) {
        this.role = sourceRole;
    }

    private void leave() {
        this.role = new Square();
    }

    public boolean hasSameColor(Color color) {
        return this.role.isSameColor(color);
    }

    public Role getRole() {
        return role;
    }

    public Color getColor() {
        return role.getColor();
    }
}
