package chess.dto.board;

import chess.domain.Symbol;
import chess.domain.postion.File;
import chess.domain.postion.Rank;

public class PieceDto {

    private final String symbol;
    private final PositionDto position;
    private final String background;

    private PieceDto(final String symbol, final int file, final int rank, final String background) {
        this.symbol = Symbol.consoleSymbolToWebSymbol(symbol);
        this.position = new PositionDto(file, rank);
        this.background = background;
    }

    public static PieceDto of(final String symbol, final File file, final Rank rank, final String background) {
        return new PieceDto(symbol, file.getNumber(), rank.getNumber(), background);
    }

    public String getSymbol() {
        return symbol;
    }

    public PositionDto getPosition() {
        return position;
    }

    public String getBackground() {
        return background;
    }
}
