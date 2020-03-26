package chess.domain.piece;

import chess.domain.BoardState;
import chess.domain.player.Player;
import chess.domain.position.Position;

public interface PieceState {

    PieceState move(Position target, BoardState boardState);

    Player getPlayer();

    double getPoint();

    String getFigure();
}
