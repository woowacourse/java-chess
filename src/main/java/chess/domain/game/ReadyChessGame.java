package chess.domain.game;

import chess.domain.PiecesPosition;
import chess.domain.piece.Camp;

public class ReadyChessGame extends ChessGame {

    private static final String NOT_START_COMMAND = "게임이 시작되지 않았습니다. 게임을 먼저 시작해주세요.";
    private static final Camp FIRST_CAMP = Camp.WHITE;

    public ReadyChessGame(PiecesPosition piecesPosition) {
        super(piecesPosition, FIRST_CAMP);
    }

    @Override
    public boolean isGameRunnable() {
        throw new IllegalStateException(NOT_START_COMMAND);
    }

    @Override
    public ChessGame playByCommand(ChessGameCommand gameCommand) {
        if (gameCommand.isStart()) {
            return new RunningChessGame(getPiecesPosition(), FIRST_CAMP);
        }

        throw new IllegalStateException(NOT_START_COMMAND);
    }
}
