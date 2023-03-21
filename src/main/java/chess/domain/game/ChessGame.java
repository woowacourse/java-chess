package chess.domain.game;

import chess.domain.PiecesPosition;

public interface ChessGame {

    boolean isRunnableGame();

    ChessGame playByCommand(ChessGameCommand gameCommand);

    PiecesPosition getPiecesPosition();
}

