package chess.model.evaluation;

import chess.model.position.Position;

import java.util.List;

public interface PieceValue {
    double calculateValue(List<Position> positions);
}
