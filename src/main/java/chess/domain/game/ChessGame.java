package chess.domain.game;

import chess.domain.PiecesPosition;

public interface ChessGame {

    boolean isGameRunnable();

    ChessGame playByCommand(ChessGameCommand gameCommand);

    PiecesPosition getPiecesPosition();
}

