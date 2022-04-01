package chess.controller;

import chess.model.board.Board;
import chess.model.board.BoardFactory;
import chess.model.ChessGame;
import chess.model.Turn;
import chess.model.command.Command;
import chess.model.command.Init;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    public void run() {
        OutputView.startGame();
        String input = InputView.inputCommand();
        Command command = new Init(input);

        Board board = new Board(BoardFactory.create());
        ChessGame chessGame = new ChessGame(board);
        Turn turn = Turn.init();
        command = command.turnState(input);
        command = progressGame(command, chessGame, turn);
        OutputView.printFinishMessage();
        command = command.turnFinalState(InputView.inputCommand());
        if (command.isStatus()) {
            OutputView.printFinalResult(chessGame.getWinTeam(), chessGame.getWhiteTeamScore(), chessGame.getBlackTeamScore());
        }
    }

    private Command progressGame(Command command, ChessGame chessGame, Turn turn) {
        while (!command.isEnd() || chessGame.isKingDead()) {
            turn = move(command, chessGame, turn);
            OutputView.printBoard(chessGame.getBoard());
            command = command.turnState(InputView.inputCommand());
        }
        return command;
    }

    private Turn move(Command command, ChessGame chessGame, Turn turn) {
        if (command.isMove()) {
            chessGame.move(command.getSourcePosition(), command.getTargetPosition(), turn);
            turn = turn.change();
        }
        return turn;
    }
}
