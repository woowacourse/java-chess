package chess.controller;

import static chess.constant.Command.END;
import static chess.constant.Command.STATUS;

import chess.constant.Command;
import chess.domain.board.Board;
import chess.domain.board.utils.BoardFactory;
import chess.domain.board.utils.ProductionBoardFactory;
import chess.dto.Request;
import chess.turndecider.GameFlow;
import chess.view.InputView;
import chess.view.OutputView;
import chess.turndecider.AlternatingGameFlow;

public class ChessController {

    public void run() {
        OutputView.printInitMessage();

        BoardFactory boardFactory = ProductionBoardFactory.getInstance();

        GameFlow gameFlow = new AlternatingGameFlow();
        Board board = new Board(boardFactory.create(), gameFlow);
        Command beginCommand = InputView.inputStartCommand();

        if (beginCommand == END) {
            return;
        }

        OutputView.printChessGameBoard(board.getBoard());
        while (gameFlow.isRunning()) {
            Request request = InputView.inputCommandInGaming();
            if (request.getCommand() == END) {
                break;
            }

            if (request.getCommand() == STATUS) {
                OutputView.printCurrentTeamGameScore(board.calculateScore());
                continue;
            }

            board.move(request.getSource(), request.getTarget());
            OutputView.printChessGameBoard(board.getBoard());
        }
    }
}
