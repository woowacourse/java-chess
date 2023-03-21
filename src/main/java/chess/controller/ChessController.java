package chess.controller;

import chess.controller.command.Command;
import chess.controller.command.CommandType;
import chess.domain.board.ChessBoardFactory;
import chess.domain.game.ChessGame;
import chess.domain.piece.Color;
import chess.domain.piece.position.PiecePosition;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static chess.controller.command.Command.FROM_POSITION_INDEX;
import static chess.controller.command.Command.TO_POSITION_INDEX;

public class ChessController {

    private final Map<CommandType, ChessAction> actionMapping = new EnumMap<>(CommandType.class);

    interface ChessAction {
        void execute(final ChessGame chessGame, final Command command);
    }

    public ChessController() {
        actionMapping.put(CommandType.START, this::start);
        actionMapping.put(CommandType.MOVE, this::move);
        actionMapping.put(CommandType.END, this::end);
        actionMapping.put(CommandType.STATUS, this::status);
    }

    public void run() {
        OutputView.printStartMessage();
        final ChessBoardFactory chessBoardFactory = new ChessBoardFactory();
        final ChessGame chessGame = new ChessGame(chessBoardFactory.create());

        while (chessGame.playable()) {
            play(chessGame);
        }

        OutputView.printWinColor(chessGame.winColor());
    }

    private void play(final ChessGame chessGame) {
        try {
            final List<String> commands = InputView.readCommand();
            final Command command = Command.parse(commands);
            actionMapping.get(command.type()).execute(chessGame, command);
        } catch (Exception e) {
            OutputView.error(e.getMessage());
        }
    }

    private void start(final ChessGame chessGame, final Command command) {
        chessGame.initialize();
        OutputView.showBoard(chessGame.pieces());
    }

    private void move(final ChessGame chessGame, final Command command) {
        final List<PiecePosition> piecePositions = command.moveParameters();
        final PiecePosition from = piecePositions.get(FROM_POSITION_INDEX);
        final PiecePosition to = piecePositions.get(TO_POSITION_INDEX);
        chessGame.movePiece(from, to);
        OutputView.showBoard(chessGame.pieces());
    }

    private void end(final ChessGame chessGame, final Command command) {
        chessGame.end();
    }

    private void status(final ChessGame chessGame, final Command command) {
        final Map<Color, Double> colorDoubleMap = chessGame.calculateScore();
        OutputView.printScore(colorDoubleMap);
    }
}
