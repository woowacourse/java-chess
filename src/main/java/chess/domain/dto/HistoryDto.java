package chess.domain.dto;

import chess.domain.board.Position;
import chess.domain.board.Square;

public class HistoryDto {
    private int src;
    private int trg;

    public HistoryDto(int source, int target) {
        this.src = source;
        this.trg = target;
    }

    public void setSrc(int src) {
        this.src = src;
    }

    public void setTrg(int trg) {
        this.trg = trg;
    }

    public Square getSrc() {
        return new Square(new Position((int)(src*0.1)), new Position(src%10));
    }

    public Square getTrg() {
        return new Square(new Position((int)(trg*0.1)), new Position(trg%10));
    }
}
