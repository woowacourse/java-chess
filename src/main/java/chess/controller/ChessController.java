package chess.controller;

import static chess.constant.Command.END;

import chess.constant.Command;
import chess.domain.board.Board;
import chess.domain.board.utils.BoardFactory;
import chess.domain.board.utils.ProductionBoardFactory;
import chess.dto.Request;
import chess.dto.view.InputView;
import chess.dto.view.OutputView;
import chess.turndecider.AlternatingTurnDecider;

public class ChessController {

    public void run() {
        OutputView.printInitMessage();

        BoardFactory boardFactory = ProductionBoardFactory.getInstance();

        Board board = new Board(boardFactory.create(), new AlternatingTurnDecider());
        Command beginCommand = InputView.inputStartCommand();

        if (beginCommand == END) {
            return;
        }

        OutputView.printChessGameBoard(board.getBoard());
        while (true) {
            Request request = InputView.inputCommandInGaming();
            if (request.getCommand() == END) {
                break;
            }

            if (request.getCommand() == Command.STATUS) {
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
