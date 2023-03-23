package chess.controller;

import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardFactory;
import chess.domain.piece.position.PiecePosition;
import chess.domain.state.ChessState;
import chess.domain.state.Initialize;
import chess.domain.state.command.Command;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.ArrayList;

public class ChessController {

    public void start() {
        OutputView.printStartMessage();
        final ChessBoard chessBoard = ChessBoardFactory.create();
        final ChessState state = new Initialize();
        run(chessBoard, state);
    }

    private void run(final ChessBoard chessBoard, ChessState state) {
        while (state.isRunnable()) {
            state = chessState(chessBoard, state);
        }
    }

    private ChessState chessState(final ChessBoard chessBoard, ChessState state) {
        try {
            final Command command = Command.parse(new ArrayList<>(InputView.readCommand()));
            state = changeState(chessBoard, command, state);
            OutputView.showBoard(chessBoard.pieces());
        } catch (Exception e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
        return state;
    }

    private ChessState changeState(final ChessBoard chessBoard, final Command command, ChessState state) {
        state = state.changeStateByCommand(command);
        if (command.isMove()) {
            state = movePiece(chessBoard, command, state);
        }
        return state;
    }

    private ChessState movePiece(final ChessBoard chessBoard, final Command command, ChessState state) {
        final PiecePosition from = PiecePosition.of(command.getFromParameter());
        final PiecePosition to = PiecePosition.of(command.getToParameter());
        chessBoard.movePiece(state, from, to);
        return state.changeTurn();
    }
}
