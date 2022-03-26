package chess.controller;

import chess.Board;
import chess.command.Command;
import chess.command.Init;
import chess.piece.Pieces;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    public void run() {
        OutputView.startGame();

        String input = InputView.inputCommand();
        Command command = new Init(input);
        Pieces pieces = Pieces.create();
        Board board = Board.create(pieces);
        command = command.turnState(input);
        while (!command.isEnd() || board.isDeadKing()){
            if(command.isMove()){
                board.move(command.getCommandPosition());
            }
            OutputView.printBoard(board.getPieces());
            command = command.turnState(InputView.inputCommand());
        }
        OutputView.printFinishMessage();
        command = command.turnFinalState(InputView.inputCommand());
        if(command.isStatus()) {
            OutputView.printFinalResult(board.getWinTeam(), board.getWhiteTeamScore(), board.getBlackTeamScore());
        }
    }
}
