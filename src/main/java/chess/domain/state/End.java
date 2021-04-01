package chess.domain.state;

import chess.domain.ScoreStatus;
import chess.domain.piece.Piece;
import chess.domain.piece.info.Color;
import chess.domain.position.Position;

import java.util.List;

public class End implements State {
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
        throw new IllegalArgumentException("[ERROR] 이미 게임이 끝났습니다.");
    }

    @Override
    public List<Piece> allPieces() {
        throw new IllegalArgumentException("[ERROR] 이미 게임이 끝났습니다.");
    }

    @Override
    public void movePieceFromSourceToTarget(Position source, Position target) {
        throw new IllegalArgumentException("[ERROR] 이미 게임이 끝났습니다.");
    }

    @Override
    public ScoreStatus scoreStatus() {
        throw new IllegalArgumentException("[ERROR] 이미 게임이 끝났습니다.");
    }

    @Override
    public State checkRunnable() {
        return this;
    }
}
