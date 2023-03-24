package chess.room;

import chess.domain.board.Square;
import chess.renderer.FileInputRenderer;
import chess.renderer.RankInputRenderer;

public class Move {
    private final Square source;
    private final Square target;

    public Move(Square source, Square target) {
        this.source = source;
        this.target = target;
    }

    public String getSourceString() {
        return FileInputRenderer.renderFile(source.getFile())
                + RankInputRenderer.renderRank(source.getRank());
    }

    public String getTargetString() {
        return FileInputRenderer.renderFile(target.getFile())
                + RankInputRenderer.renderRank(target.getRank());
    }
}
