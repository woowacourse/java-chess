package chess.controller;

import chess.domain.ChessGameImpl;
import chess.exception.ChessException;

public final class AppRunner {

    private static final String COMMAND_SPLIT_REGEX = " ";
    private static final int OPTION_INDEX = 0;

    private AppRunner() {
    }

    public static void main(String[] args) {
        ChessAction chessAction = new ChessAction(ChessGameImpl.emptyGame());
//        OutputView.printStartGame();
        playChess(chessAction);
    }

    private static void playChess(ChessAction chessAction) {
        try {
//            String command = InputView.option();
//            Option option = Option.selectedOption(command.split(COMMAND_SPLIT_REGEX)[OPTION_INDEX]);
//            GameStatus gameStatus = option.execute(chessAction, command);
//            restartIfNotEnd(gameStatus, chessAction);
        } catch (ChessException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
            playChess(chessAction);
        }
    }

    private static void restartIfNotEnd(GameStatus gameStatus, ChessAction chessAction) {
        if (gameStatus == GameStatus.RUN) {
            playChess(chessAction);
        }
    }
}
