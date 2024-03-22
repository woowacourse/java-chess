package chess.game.state;

import chess.piece.Color;

public interface TurnAction {

    void perform(Color turnColor);
}
