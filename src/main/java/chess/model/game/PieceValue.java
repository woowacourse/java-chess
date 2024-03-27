package chess.model.game;

import chess.model.position.ChessPosition;

import java.util.List;

public interface PieceValue {
    double calculateScore(List<ChessPosition> positions);
}
