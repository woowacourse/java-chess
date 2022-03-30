package chess.controller;

import static chess.constant.Command.isEnd;
import static chess.constant.Command.isStatus;

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

        if (isEnd(beginCommand)) {
            return;
        }

        OutputView.printChessGameBoard(board.getBoard());

        while (gameFlow.isRunning()) {
            Request request = InputView.inputCommandInGaming();
            if (isEnd(request.getCommand())) {
                break;
            }

            if (isStatus(request.getCommand())) {
                OutputView.printCurrentTeamGameScore(board.calculateScore());
                continue;
            }

            board.movePiece(request.getSourcePosition(), request.getTargetPosition());
            OutputView.printChessGameBoard(board.getBoard());
        }
    }
}
