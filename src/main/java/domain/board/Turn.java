package domain.board;

import domain.board.piece.Camp;

public final class Turn {

    private final Camp camp;

    private Turn(final Camp camp) {
        this.camp = camp;
    }

    public static Turn white() {
        return new Turn(Camp.WHITE);
    }

    public static Turn black() {
        return new Turn(Camp.BLACK);
    }

    public Turn next() {
        if (camp == Camp.BLACK) {
            return Turn.white();
        }
        return Turn.black();
    }

    public boolean isTurn(Camp camp) {
        return this.camp == camp;
    }

    public static Turn byCampName(String campName) {
        return new Turn(Camp.findByName(campName));
    }

    public Camp getCamp() {
        return camp;
    }
}
