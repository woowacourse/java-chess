package chess.controller;

import chess.domain.ChessGame;
import chess.domain.ChessMap;
import chess.domain.position.Position;
import chess.domain.generator.BlackGenerator;
import chess.domain.generator.WhiteGenerator;
import chess.domain.player.Player;
import chess.domain.player.Team;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ConsoleChessGameController {

    public void run() {
        final Player whitePlayer = new Player(new WhiteGenerator(), Team.WHITE);
        final Player blackPlayer = new Player(new BlackGenerator(), Team.BLACK);
        final ChessGame chessGame = initializeChessGame(whitePlayer, blackPlayer);
        playGame(chessGame);
        showGameResult(chessGame);
    }

    private ChessGame initializeChessGame(final Player whitePlayer, final Player blackPlayer) {
        InputView.requestStartCommand();
        return new ChessGame(whitePlayer, blackPlayer);
    }

    private void playGame(final ChessGame chessGame) {
        showChessMap(chessGame.createMap());

        Command command;
        do {
            final String[] inputCommand = InputView.requestGameCommand();
            command = Command.from(inputCommand);
            turnEachPlayer(command, inputCommand, chessGame);
            chessGame.changeTurn(command);
        } while (!command.isEnd() && chessGame.isRunning());
    }

    private void turnEachPlayer(final Command command, final String[] inputCommand, final ChessGame chessGame) {
        if (command.isStatus()) {
            showGameResult(chessGame);
        }
        if (command.isMove()) {
            move(chessGame, inputCommand);
            showChessMap(chessGame.createMap());
        }
    }

    private void showChessMap(final ChessMap chessMap) {
        OutputView.printChessMap(chessMap.getChessMap());
    }

    private void move(final ChessGame chessGame, String[] positionInput) {
        final List<Position> positions = findMoveCommandPosition(positionInput[1], positionInput[2]);
        final Position currentPosition = positions.get(0);
        final Position destinationPosition = positions.get(1);

        chessGame.move(chessGame.getCurrentPlayer(), chessGame.getOpponentPlayer(),
                currentPosition, destinationPosition);
    }

    public List<Position> findMoveCommandPosition(final String currentPosition, final String destinationPosition) {
        return List.of(new Position(currentPosition), new Position(destinationPosition));
    }

    private void showGameResult(final ChessGame chessGame) {
        OutputView.printResult(chessGame.findGameResult());
    }
}
