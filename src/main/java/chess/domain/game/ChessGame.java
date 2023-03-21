package chess.domain.game;

import chess.domain.PiecesPosition;
import chess.domain.piece.Camp;

public abstract class ChessGame {

    protected final PiecesPosition piecesPosition;
    protected final Camp turnCamp;

    public ChessGame(PiecesPosition piecesPosition, Camp turnCamp) {
        this.piecesPosition = piecesPosition;
        this.turnCamp = turnCamp;
    }

    public abstract boolean isGameRunnable();

    public abstract ChessGame playByCommand(ChessGameCommand gameCommand);

    public PiecesPosition getPiecesPosition() {
        return piecesPosition;
    }
}

