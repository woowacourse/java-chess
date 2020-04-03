package chess.domain.piece.team;

import chess.domain.piece.position.Direction;

public enum Team {
    WHITE(Direction.NORTH, Team::isBlack, "White"),
    BLACK(Direction.SOUTH, Team::isWhite, "Black"),
    NOT_ASSIGNED(Direction.STAY, (ignored) -> {
        throw new IllegalStateException("팀이 정해져있지 않아 적을 식별할 수 없습니다.");
    }, "Not Assigned");

    private final Direction forwardDirection;
    private final OppositeIdentifier oppositeIdentifier;
    private final String name;

    Team(Direction forwardDirection, OppositeIdentifier oppositeIdentifier, String name) {
        this.forwardDirection = forwardDirection;
        this.oppositeIdentifier = oppositeIdentifier;
        this.name = name;
    }

    public Direction getForwardDirection() {
        return forwardDirection;
    }

    public boolean isSame(Team team) {
        return this == team;
    }

    public boolean isOpposite(Team other) {
        return oppositeIdentifier.isOpposite(other);
    }

    public static String convertName(String name, Team team) {
        if (team.isWhite()) {
            return name.toLowerCase();
        }

        if (team.isBlack()) {
            return name.toUpperCase();
        }

        return name;
    }

    private boolean isBlack() {
        return this == BLACK;
    }

    private boolean isWhite() {
        return this == WHITE;
    }

    public Team getOpposite() {
        if (this == WHITE) {
            return BLACK;
        }

        if (this == BLACK) {
            return WHITE;
        }

        return NOT_ASSIGNED;
    }

    private interface OppositeIdentifier {
        boolean isOpposite(Team team);
    }

    @Override
    public String toString() {
        return name;
    }
}
