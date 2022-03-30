package chess.controller;

import chess.model.Board;
import chess.model.Turn;
import chess.model.command.Command;
import chess.model.command.Init;
import chess.model.piece.Pieces;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    public void run() {
        OutputView.startGame();
        String input = InputView.inputCommand();

        Command command = new Init(input);
        Board board = Board.create(Pieces.create());
        Turn turn = Turn.init();
        command = command.turnState(input);
        command = progressGame(command, board, turn);
        OutputView.printFinishMessage();
        command = command.turnFinalState(InputView.inputCommand());
        if (command.isStatus()) {
            OutputView.printFinalResult(board.getWinTeam(), board.getWhiteTeamScore(), board.getBlackTeamScore());
        }
    }

    private Command progressGame(Command command, Board board, Turn turn) {
        while (!command.isEnd() || board.isKingDead()) {
            turn = move(command, board, turn);
            OutputView.printBoard(board.getPieces());
            command = command.turnState(InputView.inputCommand());
        }
        return command;
    }

    private Turn move(Command command, Board board, Turn turn) {
        if (command.isMove()) {
            board.move(command.getSourcePosition(), command.getTargetPosition(), turn);
            turn = turn.change();
        }
        return turn;
    }
}
