package chess.domain.piece;

import chess.domain.player.Player;
import chess.domain.position.Position;

import java.util.Map;

public interface PieceState {

    PieceState move(Position target, Map<Position, PieceDto> boardDto);

    Player getPlayer();

    String getFigure();
}
