package chess.domain.MovableStrategy;

import chess.domain.chessPiece.PieceColor;
import chess.domain.position.Position;

import java.util.Objects;

public class PawnMovableStrategy extends NonLeapableStrategy {
    public PawnMovableStrategy(PieceColor pieceColor) {
        Objects.requireNonNull(pieceColor, "체스 피스 색깔이 존재하지 않습니다.");
        this.movableDirections.add(pieceColor.getPawnMovableDirection());
    }

    @Override
    protected boolean canMoveRange(Position source, Position target) {
        int fileInterval = Math.abs(source.calculateFileIntervalTo(target));
        int rankInterval = Math.abs(source.calculateRankIntervalTo(target));

        return fileInterval == 0 && rankInterval == 1;
    }
}
