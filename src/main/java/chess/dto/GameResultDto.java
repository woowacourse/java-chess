package chess.dto;

import chess.domain.game.GameResult;
import chess.domain.piece.Side;

public class GameResultDto {

    private final Side advantageSide;
    private final double whiteSidePrice;
    private final double blackSidePrice;

    private GameResultDto(final Side advantageSide, final double whiteSidePrice, final double blackSidePrice) {
        this.advantageSide = advantageSide;
        this.whiteSidePrice = whiteSidePrice;
        this.blackSidePrice = blackSidePrice;
    }

    public static GameResultDto from(final GameResult gameResult) {
        final Side advantageSide = gameResult.calculateAdvantageSide();
        final double whiteSidePrice = gameResult.calculatePriceBySide(Side.WHITE);
        final double blackSidePrice = gameResult.calculatePriceBySide(Side.BLACK);

        return new GameResultDto(advantageSide, whiteSidePrice, blackSidePrice);
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
