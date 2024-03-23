package chess.controller;

import chess.domain.ChessGame;
import chess.domain.Command;
import chess.domain.Position;
import chess.domain.dto.BoardDto;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

import static chess.domain.CommandType.*;

public class ChessGameController {

    public void run() {
        ChessGame chessGame = new ChessGame();

        OutputView.printChessGameStartMessage();
        OutputView.printCommandGuideMessage();

        play(chessGame);
    }

    private void play(ChessGame chessGame) {
        try {
            playChess(chessGame);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            play(chessGame);
        }
    }

    private void playChess(ChessGame chessGame) {
        List<String> commands = InputView.inputCommand();
        Command command = new Command(commands.get(0));
        while(!command.isCommand(END)) {
            playOneTurn(chessGame, commands);
            commands = InputView.inputCommand();
            command = new Command(commands.get(0));
        }
    }

    private void playOneTurn(ChessGame chessGame, List<String> commands) {
        Command command = new Command(commands.get(0));
        if (command.isCommand(START)) {
            start(chessGame);
            return;
        }
        if (command.isCommand(MOVE)) {
            move(chessGame, commands);
        }
    }

    private void start(ChessGame chessGame) {
        OutputView.printBoard(BoardDto.of(chessGame.start()));
    }

    private void move(ChessGame chessGame, List<String> commands) {
        Position source = Position.of(commands.get(1));
        Position target = Position.of(commands.get(2));
        OutputView.printBoard(BoardDto.of(chessGame.move(source, target)));
    }
}
