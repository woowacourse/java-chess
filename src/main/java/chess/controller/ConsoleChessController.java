package chess.controller;

import chess.domain.board.Board;
import chess.domain.piece.service.PieceService;
import chess.domain.piece.state.PiecesState;
import chess.domain.piece.state.Result;
import chess.domain.position.MovingFlow;
import chess.ui.Command;
import chess.ui.Console;
import chess.view.OutputView;

public class ConsoleChessController {
    private final Console console;
    private final PieceService pieceService;

    public ConsoleChessController(Console console, PieceService pieceService) {
        this.console = console;
        this.pieceService = pieceService;
    }

    public Board start() {
        Command command = console.inputStart();
        if (command.isNotStart() && command.isNotEnd()) {
            throw new IllegalArgumentException("입력이 잘못되었습니다.");
        }
        PiecesState piecesState = pieceService.initialize();
        Board board = Board.of(piecesState);
        OutputView.printBoard(board.serialize());
        return board;
    }

    public Board play(Board board) {
        while (board.isNotFinished()) {
            MovingFlow movingFlow = console.inputMovingFlow();
            PiecesState piecesState = pieceService.movePiece(movingFlow);
            board = Board.of(piecesState);
            OutputView.printBoard(board.serialize());
        }
        return board;
    }

    public void showResult(Board board) {
        Command command = console.inputStatus();
        if (command.isNotStatus()) {
            throw new IllegalArgumentException("입력이 잘못되었습니다.");
        }
        Result result = board.concludeResult();
        OutputView.printResult(result.getWinner(), result.getWhiteScore(), result.getBlackScore());
        OutputView.printEnd();
    }
}