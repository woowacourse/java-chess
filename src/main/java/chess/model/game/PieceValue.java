package chess.model.game;

import chess.model.position.Position;

import java.util.List;

public interface PieceValue {
    double calculateScore(List<Position> positions);
}
