package chess.domain.piece.team;

import chess.domain.piece.position.Direction;

public enum Team {
    WHITE(Direction.NORTH, Team::isBlack),
    BLACK(Direction.SOUTH, Team::isWhite),
    NOT_ASSIGNED(Direction.STAY, (ignored) -> {
        throw new IllegalStateException("팀이 정해져있지 않아 적을 식별할 수 없습니다.");
    });

    private Direction forwardDirection;
    private OppositeIdentifier oppositeIdentifier;

    Team(Direction forwardDirection, OppositeIdentifier oppositeIdentifier) {
        this.forwardDirection = forwardDirection;
        this.oppositeIdentifier = oppositeIdentifier;
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

    public String convertName(String name) {
        if (isWhite()) {
            return name.toLowerCase();
        }

        if (isBlack()) {
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

    private interface OppositeIdentifier {
        boolean isOpposite(Team team);
    }
}
