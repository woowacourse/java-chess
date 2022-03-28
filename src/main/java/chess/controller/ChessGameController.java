package chess.controller;

import chess.domain.ChessGame;
import chess.domain.ChessMap;
import chess.domain.Position;
import chess.domain.generator.BlackGenerator;
import chess.domain.generator.WhiteGenerator;
import chess.domain.player.Player;
import chess.domain.player.Team;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessGameController {

    public void run() {
        final Player whitePlayer = new Player(new WhiteGenerator(), Team.WHITE);
        final Player blackPlayer = new Player(new BlackGenerator(), Team.BLACK);
        final ChessGame chessGame = initializeChessGame(whitePlayer, blackPlayer);
        playGame(chessGame);
        showPlayerResult(whitePlayer, blackPlayer);
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
            command = Command.from(inputCommand[0]);
            turnEachPlayer(command, inputCommand, chessGame);
            chessGame.changeTurn();
        } while (!command.isEnd() && chessGame.isRunning());
    }

    private void turnEachPlayer(Command command, String[] inputCommand, ChessGame chessGame) {
        if (command.isStatus()) {
            showPlayerResult(chessGame.getCurrentPlayer(), chessGame.getOpponentPlayer());
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

    private void showPlayerResult(final Player currentPlayer, final Player opponentPlayer) {
        final String currentPlayerName = currentPlayer.getTeamName();
        final String opponentPlayerName = opponentPlayer.getTeamName();

        final double currentPlayerScore = currentPlayer.calculateScore();
        final double opponentPlayerScore = opponentPlayer.calculateScore();
        OutputView.printResult(currentPlayerName, currentPlayerScore, opponentPlayerName, opponentPlayerScore);
    }
}
