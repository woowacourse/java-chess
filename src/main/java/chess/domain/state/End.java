package chess.domain.state;

import chess.domain.ScoreStatus;
import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import chess.domain.piece.info.Color;
import chess.domain.position.Position;

import java.util.List;

public class End implements State {
    protected final Pieces pieces;
    private final Color color;

    public End(Pieces pieces, Color color) {
        this.pieces = pieces;
        this.color = color;
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public boolean isFinish() {
        return true;
    }

    @Override
    public State start() {
        throw new IllegalArgumentException("[ERROR] 이미 게임이 끝났습니다.");
    }

    @Override
    public State end() {
        throw new IllegalArgumentException("[ERROR] 이미 게임이 끝났습니다.");
    }

    @Override
    public State next() {
        throw new IllegalArgumentException("[ERROR] 이미 게임이 끝났습니다.");
    }

    @Override
    public Color color() {
        return color;
    }

    @Override
    public List<Piece> allPieces() {
        return pieces.piecesByAllPosition();
    }

    @Override
    public void movePieceFromSourceToTarget(Position source, Position target) {
        throw new IllegalArgumentException("[ERROR] 이미 게임이 끝났습니다.");
    }

    @Override
    public ScoreStatus scoreStatus() {
        return ScoreStatus.generateByColor(pieces);
    }

    @Override
    public State checkRunnable() {
        return this;
    }
}
