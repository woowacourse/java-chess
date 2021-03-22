package chess.controller;

import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardFactory;
import chess.domain.piece.PieceFactory;
import chess.domain.player.Round;
import chess.domain.state.State;
import chess.domain.state.StateFactory;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.*;

import static chess.view.Command.*;

public class ChessController {
    private static final InputView INPUT = new InputView(new Scanner(System.in));
    private static final String DELIMITER = " ";
    private static final int COMMAND_SIZE = 3;

    public void run() {
        if (isStart(INPUT.inputStart())) {
            ChessBoard chessBoard = initializeChessBoard();
            startChessGame(chessBoard);
        }
    }

    private ChessBoard initializeChessBoard() {
        ChessBoard chessBoard = new ChessBoard(ChessBoardFactory.initializeBoard());
        chessBoard.initializeBoard();
        return chessBoard;
    }

    private void startChessGame(final ChessBoard chessBoard) {
        Round round = new Round(StateFactory.initialization(PieceFactory.whitePieces()),
                StateFactory.initialization(PieceFactory.blackPieces()));

        while (true) try {
            inputCommand(chessBoard, round);
            break;
        } catch (RuntimeException runtimeException) {
            System.out.println("[EROOR]: " + runtimeException.getMessage());
        }
    }

    private void inputCommand(ChessBoard chessBoard, Round round) {
        String command = "";
        while (!isEnd(command)) {
            OutputView.showChessBoard(chessBoard.getBoard());
            Queue<String> commands = INPUT.inputCommand();
            command = commands.poll();
            round.move(command, commands.poll(), commands.poll());
        }
    }

    private boolean isStart(final String input) {
        return START.isSame(input);
    }

    private boolean isEnd(final String input) {
        return END.isSame(input);
    }
}
