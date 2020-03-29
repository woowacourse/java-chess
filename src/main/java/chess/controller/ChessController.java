package chess.controller;

import chess.domain.Position;
import chess.domain.chessboard.ChessBoard;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class ChessController {
    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;
    private static final String START_VALUE = "start";

    public static void run() {
        String gameState = InputView.inputGameState();

        if (START_VALUE.equalsIgnoreCase(gameState)) {
            chessStart();
        }

        OutputView.printGameEndMessage();
    }

    private static void chessStart() {
        ChessBoard chessBoard = new ChessBoard();
        OutputView.printChessBoard(chessBoard);

        while (chessBoard.isSurviveKings()) {
            gameRun(chessBoard);
        }
        OutputView.calculateScore(chessBoard);
    }

    private static void gameRun(ChessBoard chessBoard) {
        try {
            List<String> positionsToMove = InputView.inputMoveCommand();
            pieceMove(positionsToMove, chessBoard);
            OutputView.printChessBoard(chessBoard);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            OutputView.printChessBoard(chessBoard);
            gameRun(chessBoard);
        }
    }


    private static void pieceMove(List<String> movePositions, ChessBoard chessBoard) {
        Position source = Position.of(movePositions.get(FILE_INDEX));
        Position target = Position.of(movePositions.get(RANK_INDEX));
        chessBoard.movePiece(source, target);
    }
}
