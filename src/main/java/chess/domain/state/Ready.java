package chess.domain.state;

import chess.domain.ScoreStatus;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.Pieces;
import chess.domain.piece.info.Color;
import chess.domain.position.Position;

import java.util.List;

public class Ready implements State {
    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public State start() {
        return new WhiteTurn(new Pieces(PieceFactory.initialPieces()));
    }

    @Override
    public State end() {
        return new End();
    }

    @Override
    public State next() {
        throw new IllegalArgumentException("[ERROR] 게임 시작 전입니다.");
    }

    @Override
    public Color color() {
        throw new IllegalArgumentException("[ERROR] 게임 시작 전입니다.");
    }

    @Override
    public List<Piece> allPieces() {
        throw new IllegalArgumentException("[ERROR] 게임 시작 전입니다.");
    }

    @Override
    public void movePieceFromSourceToTarget(Position source, Position target) {
        throw new IllegalArgumentException("[ERROR] 게임 시작 전입니다.");
    }

    @Override
    public ScoreStatus scoreStatus() {
        throw new IllegalArgumentException("[ERROR] 게임 시작 전입니다.");
    }

    @Override
    public State checkRunnable() {
        throw new IllegalArgumentException("[ERROR] 게임 시작 전입니다.");
    }
}
