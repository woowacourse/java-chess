package chess.controller;

import chess.constant.Command;
import chess.domain.board.ChessBoard;
import chess.dto.request.Request;
import chess.view.InputView;
import chess.view.OutputView;

import static java.lang.System.err;

public class ChessController {

    private final ChessBoard chessBoard;

    public ChessController(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public void run() {
        OutputView.printInitMessage();
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
                OutputView.printCurrentTeamScore(chessBoard.currentState().getName(), chessBoard.calculateScore());
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
