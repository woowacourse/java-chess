package chess.controller;

import chess.domain.ChessGame;
import chess.domain.Command;
import chess.domain.Position;
import chess.domain.dto.BoardDto;
import chess.view.InputView;
import chess.view.OutputView;

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
        Command command = Command.of(InputView.inputCommand());
        while(!command.isCommand(END)) {
            playOneTurn(chessGame, command);
            command = Command.of(InputView.inputCommand());
        }
    }

    private void playOneTurn(ChessGame chessGame, Command command) {
        if (command.isCommand(START)) {
            start(chessGame);
            return;
        }
        if (command.isCommand(MOVE)) {
            move(chessGame, command);
        }
    }

    private void start(ChessGame chessGame) {
        OutputView.printBoard(BoardDto.of(chessGame.start()));
    }

    private void move(ChessGame chessGame, Command command) {
        Position source = Position.of(command.getSource());
        Position target = Position.of(command.getTarget());
        OutputView.printBoard(BoardDto.of(chessGame.move(source, target)));
    }
}
