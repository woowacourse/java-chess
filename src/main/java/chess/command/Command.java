package chess.command;

import java.util.List;

import chess.domain.ChessGame;
import chess.view.OutputView;

public abstract class Command {
    private static final String ERROR_MESSAGE_IN_GAME = "[ERROR] 당신.. 이미 게임 중인걸..?";
    private static final String ERROR_MESSAGE_KING_DIE = "[ERROR] 이미 게임 끝났거등? ㅋ";
    private static final String ERROR_MESSAGE_NOT_IN_GAME = "[ERROR] 게임 시작부터 해야지! -3-";
    private static final String ERROR_MESSAGE_NOT_KING_DIE = "[ERROR] 아직 게임 안끝났거등? ㅋ";

    private final CommandType commandType;

    public Command(List<String> commands) {
        this.commandType = CommandType.find(commands.get(0));
    }

    public abstract boolean execute(ChessGame chessGame);

    public boolean canShowScore(ChessGame chessGame) {
        try {
            checkNotInGame(chessGame);
            checkKingNotDie(chessGame);
            return true;
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
        }
        return false;
    }

    public void checkNotInGame(ChessGame chessGame) {
        if (!chessGame.isInGame()) {
            throw new IllegalArgumentException(ERROR_MESSAGE_NOT_IN_GAME);
        }
    }

    public void checkKingNotDie(ChessGame chessGame) {
        if (!chessGame.isKingDie()) {
            throw new IllegalArgumentException(ERROR_MESSAGE_NOT_KING_DIE);
        }
    }

    public void checkInGame(ChessGame chessGame) {
        if (chessGame.isInGame()) {
            throw new IllegalArgumentException(ERROR_MESSAGE_IN_GAME);
        }
    }

    public void checkKingDie(ChessGame chessGame) {
        if (chessGame.isKingDie()) {
            throw new IllegalArgumentException(ERROR_MESSAGE_KING_DIE);
        }
    }

    public CommandType getCommandType() {
        return commandType;
    }
}
