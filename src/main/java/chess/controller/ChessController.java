package chess.controller;

import chess.domain.Position;
import chess.domain.game.state.ChessGame;
import chess.domain.game.state.Ready;
import chess.dto.RequestDto;
import chess.view.InputOption;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public void run() {
        ChessGame chessGame = new Ready();
        OutputView.printGameInitMessage();
        while (!chessGame.isFinish()) {
            chessGame = selectMenu(chessGame, InputView.inputOption());
        }
        try {
            OutputView.printWinner(chessGame.judgeWinner());
        } catch (IllegalStateException exception) {
            OutputView.printError(exception.getMessage());
        }
    }

    public ChessGame selectMenu(ChessGame chessGame, RequestDto dto) {
        InputOption option = dto.getInputOption();
        if (option == InputOption.MOVE) {
            return move(chessGame, dto.getFromPosition(), dto.getToPosition());
        }
        Command command = CommandFactory.playCommand(option);
        return command.run(chessGame);
    }

    public ChessGame move(ChessGame chessGame, String fromPosition, String toPosition) {
        try {
            ChessGame movedGame = chessGame.movePiece(Position.valueOf(fromPosition),
                    Position.valueOf(toPosition));
            OutputView.printInitialChessBoard(movedGame.getBoard());
            return movedGame;
        } catch (IllegalStateException | IllegalArgumentException exception) {
            OutputView.printError(exception.getMessage());
        }
        return chessGame;
    }
}

