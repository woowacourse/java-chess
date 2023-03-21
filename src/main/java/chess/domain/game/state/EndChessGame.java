package chess.domain.game.state;

import chess.domain.PiecesPosition;
import chess.domain.game.ChessGame;
import chess.domain.game.ChessGameCommand;

public class EndChessGame implements ChessGame {

    private static final String UNABLE_TO_PLAY = "게임이 종료되었습니다.";

    private final PiecesPosition piecesPosition;

    public EndChessGame(PiecesPosition piecesPosition) {
        this.piecesPosition = piecesPosition;
    }

    @Override
    public boolean isRunnableGame() {
        return false;
    }

    @Override
    public ChessGame playByCommand(ChessGameCommand gameCommand) {
        throw new IllegalStateException(UNABLE_TO_PLAY);
    }

    @Override
    public PiecesPosition getPiecesPosition() {
        return this.piecesPosition;
    }
}
