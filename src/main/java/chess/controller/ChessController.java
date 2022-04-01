package chess.controller;

import static java.lang.System.err;

import chess.constant.Command;
import chess.domain.board.ChessBoard;
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
        ChessBoard chessBoard = new ChessBoard(boardFactory.create(), gameFlow);
        Command beginCommand = InputView.inputStartCommand();

        if (beginCommand.isEnd()) {
            return;
        }

        OutputView.printChessBoard(chessBoard.getBoard());

        startGame(chessBoard);
    }

    private void startGame(ChessBoard chessBoard) {
        while (chessBoard.isGamePlaying()) {
            Request request = InputView.inputCommandInGaming();
            if (request.getCommand().isEnd()) {
                break;
            }

            if (request.getCommand().isStatus()) {
                OutputView.printCurrentTeamScore(chessBoard.calculateScore());
                continue;
            }

            movePiece(chessBoard, request);
            OutputView.printChessBoard(chessBoard.getBoard());
        }
    }

    private void movePiece(ChessBoard chessBoard, Request request) {
        try {
            chessBoard.movePiece(request.getSourcePosition(), request.getTargetPosition());
        } catch (RuntimeException e) {
            err.println(e.getMessage());
            movePiece(chessBoard, InputView.inputCommandInGaming());
        }
    }
}
