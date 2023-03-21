package chess.domain.game;

import chess.domain.PiecesPosition;
import chess.domain.piece.Camp;

public class EndChessGame extends ChessGame{

    private static final String UNABLE_TO_PLAY = "게임이 종료되었습니다.";

    public EndChessGame(PiecesPosition piecesPosition, Camp turnCamp) {
        super(piecesPosition, turnCamp);
    }

    @Override
    public boolean isGameRunnable() {
        return false;
    }

    @Override
    public ChessGame playByCommand(ChessGameCommand gameCommand) {
        throw new IllegalStateException(UNABLE_TO_PLAY);
    }
}
