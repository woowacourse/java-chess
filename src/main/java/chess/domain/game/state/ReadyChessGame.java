package chess.domain.game.state;

import chess.domain.PiecesPosition;
import chess.domain.game.ChessGame;
import chess.domain.game.ChessGameCommand;
import chess.domain.piece.Camp;

public class ReadyChessGame implements ChessGame {

    private static final String NOT_START_COMMAND = "게임이 시작되지 않았습니다. 게임을 먼저 시작해주세요.";
    private static final Camp INIT_CAMP = Camp.WHITE;

    private final PiecesPosition piecesPosition;

    public ReadyChessGame(PiecesPosition piecesPosition) {
        this.piecesPosition = piecesPosition;
    }

    @Override
    public boolean isRunnableGame() {
        throw new IllegalStateException(NOT_START_COMMAND);
    }

    @Override
    public ChessGame playByCommand(ChessGameCommand gameCommand) {
        if (gameCommand.isStart()) {
            return new RunningChessGame(piecesPosition, INIT_CAMP);
        }

        throw new IllegalStateException(NOT_START_COMMAND);
    }

    @Override
    public PiecesPosition getPiecesPosition() {
        return this.piecesPosition;
    }
}
