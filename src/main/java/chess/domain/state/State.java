package chess.domain.state;

import chess.domain.ScoreStatus;
import chess.domain.piece.Piece;
import chess.domain.piece.info.Color;
import chess.domain.position.Position;

import java.util.List;

public interface State {
    boolean isReady();

    boolean isFinish();

    State start();

    State end();

    State next();

    Color color();

    List<Piece> allPieces();

    void movePieceFromSourceToTarget(Position source, Position target);

    ScoreStatus scoreStatus();

    State checkRunnable();
}
