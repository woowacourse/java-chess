package chess.controller;

import chess.constant.Command;
import chess.domain.board.Board;
import chess.domain.board.factory.BoardFactory;
import chess.domain.board.factory.RegularBoardFactory;
import chess.dto.Request;
import chess.turndecider.GameFlow;
import chess.view.InputView;
import chess.view.OutputView;
import chess.turndecider.AlternatingGameFlow;

public class ChessController {

    public void run() {
        OutputView.printInitMessage();

        BoardFactory boardFactory = RegularBoardFactory.getInstance();

        GameFlow gameFlow = new AlternatingGameFlow();
        Board board = new Board(boardFactory.create(), gameFlow);
        Command beginCommand = InputView.inputStartCommand();

        if (beginCommand.isEnd()) {
            return;
        }

        OutputView.printChessBoard(board.getBoard());

        while (gameFlow.isRunning()) {
            Request request = InputView.inputCommandInGaming();
            if (request.getCommand().isEnd()) {
                break;
            }

            if (request.getCommand().isStatus()) {
                OutputView.printCurrentTeamGameScore(board.calculateScore());
                continue;
            }

            board.movePiece(request.getSourcePosition(), request.getTargetPosition());
            OutputView.printChessBoard(board.getBoard());
        }
    }
}
