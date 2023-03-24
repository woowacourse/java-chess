package chess.controller;

import chess.controller.status.Start;
import chess.controller.status.Status;
import chess.domain.chess.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ChessController {

    public void run() {
        OutputView.printStartMessage();
        final ChessGame chessGame = new ChessGame();
        play(chessGame, gameStatus -> {
            if (gameStatus.isRun()) {
                printChessBoard(chessGame.getChessBoard());
            }
        });
    }

    private void play(final ChessGame chessGame, Consumer<Status> consumer) {
        Status gameStatus = new Start(chessGame);
        while (gameStatus.isRun()) {
            gameStatus = getStatus(gameStatus);
            consumer.accept(gameStatus);
        }
    }

    private Status getStatus(Status gameStatus) {
        try {
            List<String> commands = InputView.getCommand();
            final Command command = Command.findCommand(commands);
            return gameStatus.checkCommand(command);
        } catch (IllegalArgumentException | IllegalStateException e) {
            OutputView.print(e.getMessage());
            return getStatus(gameStatus);
        }
    }

    private void printChessBoard(Map<Position, Piece> board) {
        ChessBoardDto chessBoardDTO = new ChessBoardDto(board);
        OutputView.print(chessBoardDTO.getBoardMessage().toString());
    }
}
