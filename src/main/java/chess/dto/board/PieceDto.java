package chess.dto.board;

import chess.domain.postion.File;
import chess.domain.postion.Rank;

public class PieceDto {

    private final String symbol;
    private final PositionDto position;

    private PieceDto(final String symbol, final int file, final int rank) {
        this.symbol = symbol;
        this.position = new PositionDto(file, rank);

    }

    public static PieceDto of(final String symbol, final File file, final Rank rank) {
        return new PieceDto(symbol, file.getNumber(), rank.getNumber());
    }

    public String getSymbol() {
        return symbol;
    }

    public PositionDto getPosition() {
        return position;
    }
}
