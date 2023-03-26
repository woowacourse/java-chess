package chess.status;

import chess.chessgame.ChessGame;
import chess.controller.*;

public class Finished implements GameStatus {
    private final ChessGame chessGame;

    public Finished(final ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    @Override
    public GameStatus playGame(final Command command, final PrintAction printAction) {
        validateStatusCommand(command);

        printAction.run(new ResultDto(new ScoreDto(0, 0), new WinnerDto(chessGame.getWinner())));

        return this;
    }

    private void validateStatusCommand(final Command command) {
        if (command.getCommandType() != CommandType.STATUS) {
            throw new IllegalArgumentException("게임이 종료되었습니다");
        }
    }
}
