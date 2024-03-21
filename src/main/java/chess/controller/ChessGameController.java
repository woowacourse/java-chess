package chess.controller;

import static chess.domain.CommandType.END;
import static chess.domain.CommandType.START;

import chess.domain.ChessGame;
import chess.domain.Position;
import chess.domain.dto.BoardDto;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessGameController {

    public void run() {
        ChessGame chessGame = new ChessGame();

        playChess(chessGame);
    }

    private void playChess(ChessGame chessGame) {
        List<String> commands = InputView.inputCommand();
        String command = commands.get(0);
        while(!command.equals(END.getCommandType())) {
            playOneTurn(chessGame, commands);
            commands = InputView.inputCommand();
            command = commands.get(0);
        }
    }

    private void playOneTurn(ChessGame chessGame, List<String> commands) {
        String command = commands.get(0);
        if (command.equals(START.getCommandType())) {
            playOneTurnForStart(chessGame);
            return;
        }
        playOneTurnForMove(chessGame, commands);
    }

    private void playOneTurnForStart(ChessGame chessGame) {
        OutputView.printBoard(BoardDto.of(chessGame.start()));
    }

    private void playOneTurnForMove(ChessGame chessGame, List<String> commands) {
        Position source = Position.of(commands.get(1));
        Position target = Position.of(commands.get(2));
        OutputView.printBoard(BoardDto.of(chessGame.move(source, target)));
    }
}
