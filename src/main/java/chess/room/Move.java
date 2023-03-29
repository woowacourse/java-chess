package chess.room;

import chess.domain.board.Square;
import chess.renderer.FileRenderer;
import chess.renderer.RankRenderer;

public class Move {
    private final Square source;
    private final Square target;

    public Move(final Square source, final Square target) {
        this.source = source;
        this.target = target;
    }

    public String getSourceString() {
        return FileRenderer.renderFile(source.getFile())
                + RankRenderer.renderRank(source.getRank());
    }

    public String getTargetString() {
        return FileRenderer.renderFile(target.getFile())
                + RankRenderer.renderRank(target.getRank());
    }

    public Square getSource() {
        return source;
    }

    public Square getTarget() {
        return target;
    }
}
