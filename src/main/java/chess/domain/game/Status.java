package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.piece.Side;

public class Status {

    private final Side advantageSide;
    private final double whiteSidePrice;
    private final double blackSidePrice;

    private Status(final Side advantageSide, final double whiteSidePrice, final double blackSidePrice) {
        this.advantageSide = advantageSide;
        this.whiteSidePrice = whiteSidePrice;
        this.blackSidePrice = blackSidePrice;
    }

    public static Status from(final Board board) {
        final Side advantageSide = board.calculateAdvantageSide();
        final double whiteSidePrice = board.calculatePriceBySide(Side.WHITE);
        final double blackSidePrice = board.calculatePriceBySide(Side.BLACK);

        return new Status(advantageSide, whiteSidePrice, blackSidePrice);
    }

    public Side getAdvantageSide() {
        return advantageSide;
    }

    public double getWhiteSidePrice() {
        return whiteSidePrice;
    }

    public double getBlackSidePrice() {
        return blackSidePrice;
    }
}
