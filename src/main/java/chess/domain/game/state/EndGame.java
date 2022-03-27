package chess.domain.game.state;

import java.util.Map;

import chess.domain.piece.Piece;
import chess.domain.piece.position.Position;

public class EndGame implements GameState {

    @Override
    public GameState start() {
        throw new IllegalArgumentException("이미 종료된 게임입니다.");
    }

    @Override
    public GameState move(Position source, Position target) {
        throw new IllegalArgumentException("이미 종료된 게임입니다.");
    }

    @Override
    public GameState status() {
        throw new IllegalArgumentException("이미 종료된 게임입니다.");
    }

    @Override
    public GameState end() {
        throw new IllegalArgumentException("이미 종료된 게임입니다.");
    }

    @Override
    public Map<Position, Piece> getBoard() {
        throw new IllegalArgumentException("이미 종료된 게임입니다.");
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
