package chess.domain.player;

import chess.domain.piece.Owner;

public enum Turn {
    BLACK(Owner.BLACK), WHITE(Owner.WHITE);

    private final Owner owner;

    Turn(final Owner owner) {
        this.owner = owner;
    }

    public Turn change() {
        if (this.equals(BLACK)) {
            return WHITE;
        }

        return BLACK;
    }

    public void validate(final Owner mover) {
        if(!owner.isSameTeam(mover)){
            throw new IllegalArgumentException("현재 순서의 사용자가 아닙니다.");
        }
    }
}
