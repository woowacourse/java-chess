package chess.controller;

import chess.application.ChessGameService;
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

    private final ChessGameService chessGameService;
    private final ChessBoardFactory chessBoardFactory;

    public ChessController(final ChessGameService chessGameService, final ChessBoardFactory chessBoardFactory) {
        this.chessGameService = chessGameService;
        this.chessBoardFactory = chessBoardFactory;
    }

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
                    restartGame(command);
                    return;
                }
                throw new IllegalArgumentException("시작하거나, 재시작해야 합니다");
            } catch (Exception e) {
                OutputView.error(e.getMessage());
            }
        }
    }

    private void createGameAndStart() {
        final Long id = chessGameService.create(chessBoardFactory);
        playGame(id);
    }

    private void restartGame(final Command command) {
        playGame(command.restartParameter());
    }

    private void playGame(final Long chessGameId) {
        ChessGame chessGame = chessGameService.findById(chessGameId);
        if (!chessGame.playable()) {
            throw new IllegalArgumentException("이미 끝난 게임입니다.");
        }
        System.out.println(chessGame.id() + "번 게임을 시작합니다");
        OutputView.showBoard(chessGame.pieces());
        while (chessGame.playable()) {
            try {
                final Command command = readCommand();
                if (command.type() == CommandType.MOVE) {
                    move(chessGameId, command);
                    continue;
                }
                if (command.type() == CommandType.END) {
                    System.out.println("게임 저장 후 종료");
                    return;
                }
                if (command.type() == CommandType.STATUS) {
                    status(chessGameId);
                    continue;
                }
                throw new IllegalArgumentException("움직이거나, 끝내거나");
            } catch (Exception e) {
                OutputView.error(e.getMessage());
            }
        }
        OutputView.printWinColor(chessGame.winColor());
    }

    private Command readCommand() {
        final List<String> commands = InputView.readCommand();
        return Command.parse(commands);
    }

    private void move(final Long chessGameId, final Command command) {
        final List<PiecePosition> piecePositions = command.moveParameters();
        final PiecePosition from = piecePositions.get(FROM_POSITION_INDEX);
        final PiecePosition to = piecePositions.get(TO_POSITION_INDEX);
        chessGameService.movePiece(chessGameId, from, to);

        ChessGame chessGame = chessGameService.findById(chessGameId);
        OutputView.showBoard(chessGame.pieces());
    }

    private void status(final Long chessGameId) {
        ChessGame chessGame = chessGameService.findById(chessGameId);
        final Map<Color, Double> colorDoubleMap = chessGame.calculateScore();
        OutputView.printScore(colorDoubleMap);
    }
}
