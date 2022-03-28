package chess.controller;

import static chess.constant.Command.END;
import static chess.constant.Command.STATUS;

import chess.constant.Command;
import chess.domain.board.Board;
import chess.domain.board.utils.BoardFactory;
import chess.domain.board.utils.ProductionBoardFactory;
import chess.dto.Request;
import chess.turndecider.TurnDecider;
import chess.view.InputView;
import chess.view.OutputView;
import chess.turndecider.AlternatingTurnDecider;

public class ChessController {

    public void run() {
        OutputView.printInitMessage();

        BoardFactory boardFactory = ProductionBoardFactory.getInstance();

        TurnDecider turnDecider = new AlternatingTurnDecider();
        Board board = new Board(boardFactory.create(), turnDecider);
        Command beginCommand = InputView.inputStartCommand();

        if (beginCommand == END) {
            return;
        }

        OutputView.printChessGameBoard(board.getBoard());
        while (turnDecider.isRunning()) {
            Request request = InputView.inputCommandInGaming();
            if (request.getCommand() == END) {
                break;
            }

            if (request.getCommand() == STATUS) {
                OutputView.printCurrentTeamGameScore(board.calculateScore());
                continue;
            }

            boolean isFinished = board.move(request.getSource(), request.getTarget());
            OutputView.printChessGameBoard(board.getBoard());

            if (isFinished) {
                break;
            }
        }
    }
}
