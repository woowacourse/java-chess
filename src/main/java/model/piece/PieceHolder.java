package model.piece;

import java.util.List;
import model.piece.state.Role;
import model.piece.state.Square;
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

    public void progressMoveToDestination(List<PieceHolder> pieceHoldersInRoute, PieceHolder destination) {
        List<Role> rolesInRoute = pieceHoldersInRoute.stream()
                .map(pieceHolder -> pieceHolder.role)
                .toList();
        this.role.traversalRoles(rolesInRoute);
        destination.changeRoleTo(role);
        leave();
    }

    private void changeRoleTo(Role sourceRole) {
        if (isSameColor(sourceRole.getColor())) {
            throw new IllegalArgumentException("해당 위치에 같은 색의 기물이 존재합니다.");
        }
        this.role = sourceRole;
    }

    private void leave() {
        this.role = new Square();
    }

    public boolean isSameColor(Color color) {
        return this.role.isSameColor(color);
    }

    public Role getRole() {
        return role;
    }

    public Color getColor() {
        return role.getColor();
    }
}
