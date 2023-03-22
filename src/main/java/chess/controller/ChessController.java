package chess.controller;

import chess.controller.command.Command;
import chess.controller.command.CommandType;
import chess.domain.board.ChessBoardFactory;
import chess.domain.game.ChessGame;
import chess.domain.piece.Color;
import chess.domain.piece.position.PiecePosition;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;
import java.util.Map;

import static chess.controller.command.Command.FROM_POSITION_INDEX;
import static chess.controller.command.Command.TO_POSITION_INDEX;

public class ChessController {

    public void start() {
        OutputView.printStartMessage();
        while (true) {
            try {
                final Command command = readCommand();
                if (command.type() == CommandType.START) {
                    createGameAndStart();
                    return;
                }
                if (command.type() == CommandType.RESTART) {
                    restartGame();
                    return;
                }
                throw new IllegalArgumentException("시작하거나, 재시작해야 합니다");
            } catch (Exception e) {
                OutputView.error(e.getMessage());
            }
        }
    }

    private void createGameAndStart() {
        final ChessBoardFactory chessBoardFactory = new ChessBoardFactory();
        final ChessGame chessGame = new ChessGame(chessBoardFactory.create());
        playGame(chessGame);
    }

    private void restartGame() {
        // TODO - findById 로 ChesGame 가져와서 시작하기
        System.out.println("아직 기능이 없어서 그냥 시작합니다.");
        final ChessBoardFactory chessBoardFactory = new ChessBoardFactory();
        final ChessGame chessGame = new ChessGame(chessBoardFactory.create());
        playGame(chessGame);
    }

    private void playGame(final ChessGame chessGame) {
        OutputView.showBoard(chessGame.pieces());
        while (chessGame.playable()) {
            play(chessGame);
        }
        OutputView.printWinColor(chessGame.winColor());
        saveAndEnd(chessGame);
    }

    private void saveAndEnd(final ChessGame chessGame) {
        if (chessGame.playable()) {
            chessGame.end();
        }
        // TODO SAVE
    }

    private void play(final ChessGame chessGame) {
        final Command command = readCommand();
        if (command.type() == CommandType.MOVE) {
            move(chessGame, command);
            return;
        }
        if (command.type() == CommandType.END) {
            saveAndEnd(chessGame);
            return;
        }
        if (command.type() == CommandType.STATUS) {
            status(chessGame);
            return;
        }
        throw new IllegalArgumentException("움직이거나, 끝내거나");
    }

    private Command readCommand() {
        final List<String> commands = InputView.readCommand();
        return Command.parse(commands);
    }

    private void move(final ChessGame chessGame, final Command command) {
        final List<PiecePosition> piecePositions = command.moveParameters();
        final PiecePosition from = piecePositions.get(FROM_POSITION_INDEX);
        final PiecePosition to = piecePositions.get(TO_POSITION_INDEX);
        chessGame.movePiece(from, to);
        OutputView.showBoard(chessGame.pieces());
    }

    private void status(final ChessGame chessGame) {
        final Map<Color, Double> colorDoubleMap = chessGame.calculateScore();
        OutputView.printScore(colorDoubleMap);
    }
}
