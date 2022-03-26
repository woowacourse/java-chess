package chess.controller;

import chess.Board;
import chess.Position;
import chess.command.Command;
import chess.command.Init;
import chess.command.Move;
import chess.piece.Pieces;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class ChessController {
    public void run() {
        OutputView.startGame();

        String input = InputView.inputCommand();
        Command command = new Init(input);
        Pieces pieces = Pieces.create();
        Board board = Board.create(pieces);
        command = command.turnState(input);
        while (!command.isEnd()){
            if(command.isMove()){
                board.move(command.getCommandPosition());
            }
            OutputView.printBoard(board.getBoard());
            command = command.turnState(InputView.inputCommand());
        }
    }
}
