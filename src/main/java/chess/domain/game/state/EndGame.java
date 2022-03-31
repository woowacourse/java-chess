package chess.domain.game.state;

import java.util.HashMap;
import java.util.Map;

import chess.domain.piece.Piece;
import chess.domain.piece.position.Position;
import chess.domain.piece.property.Color;

public class EndGame implements GameState {

    @Override
    public GameState start() {
        throw new UnsupportedOperationException("이미 종료된 게임입니다.");
    }

    @Override
    public GameState move(Position source, Position target) {
        throw new UnsupportedOperationException("이미 종료된 게임입니다.");
    }

    @Override
    public Map<Color, Double> status() {
        throw new UnsupportedOperationException("이미 종료된 게임입니다.");
    }

    @Override
    public GameState end() {
        throw new UnsupportedOperationException("이미 종료된 게임입니다.");
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public Map<Position, Piece> getBoard() {
        return new HashMap<>();
    }
}
