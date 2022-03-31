package chess.controller;

import static java.lang.System.err;

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

        startGame(gameFlow, board);
    }

    private void startGame(GameFlow gameFlow, Board board) {
        while (gameFlow.isRunning()) {
            Request request = InputView.inputCommandInGaming();
            if (request.getCommand().isEnd()) {
                break;
            }

            if (request.getCommand().isStatus()) {
                OutputView.printCurrentTeamScore(board.calculateScore());
                continue;
            }

            movePiece(board, request);
            OutputView.printChessBoard(board.getBoard());
        }
    }

    private void movePiece(Board board, Request request) {
        try {
            board.movePiece(request.getSourcePosition(), request.getTargetPosition());
        } catch (RuntimeException e) {
            err.println(e.getMessage());
            movePiece(board, InputView.inputCommandInGaming());
        }
    }
}
