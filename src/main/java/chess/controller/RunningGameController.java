package chess.controller;

import chess.application.ChessGameService;
import chess.controller.command.RunningCommand;
import chess.controller.command.RunningCommandType;
import chess.domain.game.ChessGame;
import chess.domain.piece.Color;
import chess.domain.piece.position.PiecePosition;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;
import java.util.Map;

import static chess.controller.command.RunningCommand.FROM_POSITION_INDEX;
import static chess.controller.command.RunningCommand.TO_POSITION_INDEX;

public class RunningGameController {

    private final ChessGameService chessGameService;

    public RunningGameController(final ChessGameService chessGameService) {
        this.chessGameService = chessGameService;
    }

    public void startGame(final Long chessGameId) {
        ChessGame chessGame = chessGameService.findById(chessGameId);
        validateAlreadyEnd(chessGame);
        OutputView.startGame(chessGame.id());
        runToEnd(chessGameId, chessGame);
    }

    private void validateAlreadyEnd(final ChessGame chessGame) {
        if (!chessGame.playable()) {
            throw new IllegalArgumentException("이미 끝난 게임입니다.");
        }
    }

    private void runToEnd(final Long chessGameId, ChessGame chessGame) {
        do {
            OutputView.showBoard(chessGame.pieces(), chessGame.turnColor());
            final RunningCommand command = readCommand();
            if (command.type() == RunningCommandType.END) {
                OutputView.saveAndEnd();
                return;
            }
            run(chessGameId, command);
            chessGame = chessGameService.findById(chessGameId);
        } while (chessGame.playable());
        OutputView.printWinColor(chessGame.winColor());
    }

    private RunningCommand readCommand() {
        while (true) {
            try {
                final List<String> commands = InputView.readCommand();
                return RunningCommand.parse(commands);
            } catch (Exception e) {
                OutputView.error(e.getMessage());
            }
        }
    }

    private void run(final Long chessGameId, final RunningCommand command) {
        try {
            if (command.type() == RunningCommandType.MOVE) {
                move(chessGameId, command);
            }
            if (command.type() == RunningCommandType.STATUS) {
                status(chessGameId);
            }
        } catch (Exception e) {
            OutputView.error(e.getMessage());
        }
    }

    private void move(final Long chessGameId, final RunningCommand command) {
        final List<PiecePosition> piecePositions = command.moveParameters();
        final PiecePosition from = piecePositions.get(FROM_POSITION_INDEX);
        final PiecePosition to = piecePositions.get(TO_POSITION_INDEX);
        chessGameService.movePiece(chessGameId, from, to);
    }

    private void status(final Long chessGameId) {
        ChessGame chessGame = chessGameService.findById(chessGameId);
        final Map<Color, Double> colorDoubleMap = chessGame.calculateScore();
        OutputView.printScore(colorDoubleMap);
    }
}
