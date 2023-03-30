package chess.controller;

import chess.controller.command.Command;
import chess.controller.command.CommandAction;
import chess.controller.command.Commands;
import chess.domain.model.player.Color;
import chess.domain.model.player.Players;
import chess.domain.dao.PieceDao;
import chess.domain.dao.TurnDao;
import chess.domain.factory.InitPlayersFactory;
import chess.dto.response.PiecesResponse;
import chess.ui.InputView;
import chess.ui.OutputView;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public final class ChessGameController {
    private final CommandAction commandAction = new CommandAction(Map.of(
            Command.START, this::start,
            Command.MOVE, this::move,
            Command.END, this::end,
            Command.STATUS, this::status)
    );
    private final TurnDao turnDao;
    private final PieceDao pieceDao;
    private Players players;

    public ChessGameController(TurnDao turnDao, PieceDao pieceDao) {
        this.turnDao = turnDao;
        this.pieceDao = pieceDao;
    }

    private void start(final Commands commands) {
        players = InitPlayersFactory.initializeChessBoard(pieceDao, turnDao);
        PiecesResponse piecesResponse = new PiecesResponse(players.getPiecesByColor(Color.WHITE), players.getPiecesByColor(Color.BLACK));
        OutputView.printInitializedChessBoard(piecesResponse);
    }

    private void move(final Commands commands) {
        String inputMovablePiece = commands.getMovablePiece();
        String inputTargetPosition = commands.getTargetPosition();

        try {
            players.movePiece(inputMovablePiece, inputTargetPosition);
            OutputView.printChessBoardStatus(
                    new PiecesResponse(players.getPiecesByColor(Color.WHITE), players.getPiecesByColor(Color.BLACK)));
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    private void end(final Commands commands) {
    }

    private void status(final Commands commands) {
        OutputView.printStatus(players.calculateScore());
    }


    public void run() {
        players = InitPlayersFactory.initializeChessBoard(pieceDao, turnDao);
        OutputView.printStartGame();
        boolean isNotEnd = true;
        while (isOnGoing(isNotEnd)) {
            Commands commandWithArguments = readCommand(InputView::getCommands);
            commandAction.execute(commandWithArguments);
            isNotEnd = commandWithArguments.isNotEnd();
        }
        finishGame();
    }

    private boolean isOnGoing(boolean isNotEnd) {
        return isNotEnd && !players.notEveryKingAlive();
    }

    private void finishGame() {
        if (players.notEveryKingAlive()) {
            OutputView.printWinner(players.getWinnerColorName());
            pieceDao.deleteAll();
        }
    }

    private Commands readCommand(Supplier<List<String>> commandReader) {
        try {
            List<String> arguments = commandReader.get();
            return new Commands(arguments);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return readCommand(commandReader);
        }
    }

}
