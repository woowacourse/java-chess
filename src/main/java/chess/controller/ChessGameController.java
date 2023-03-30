package chess.controller;

import chess.domain.Color;
import chess.domain.CommandAction;
import chess.domain.Players;
import chess.dao.TurnDaoImpl;
import chess.domain.factory.PlayersFactory;
import chess.dao.PieceDao;
import chess.dao.PieceDaoImpl;
import chess.dto.response.PiecesResponse;
import chess.ui.InputView;
import chess.ui.OutputView;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public final class ChessGameController {

    private Players players;
    private final TurnDaoImpl turnDao = new TurnDaoImpl();

    private final CommandAction commandAction = new CommandAction(Map.of(
            Command.START, this::start,
            Command.MOVE, this::move,
            Command.END, this::end,
            Command.STATUS, this::status)
    );

    public void run() {
        players = PlayersFactory.createChessBoard();
        OutputView.printStartGame();
        CommandManagement commandWithArguments;
        do {
            commandWithArguments = readCommand(InputView::getCommands);
            commandAction.execute(commandWithArguments);
        } while (commandWithArguments.isNotEnd() && !players.notEveryKingAlive());
        finishGame();
    }

    private void finishGame() {
        if (players.notEveryKingAlive()) {
            OutputView.printWinner(players.getWinnerColorName());
            PieceDao pieceDao = new PieceDaoImpl();
            pieceDao.deleteAll();
            turnDao.update(Color.WHITE);
        }
    }

    private CommandManagement readCommand(Supplier<List<String>> commandReader) {
        try {
            List<String> arguments = commandReader.get();
            return new CommandManagement(arguments);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return readCommand(commandReader);
        }
    }

    private void start(final CommandManagement commandManagement) {
        players = PlayersFactory.createChessBoard();
        PiecesResponse piecesResponse = new PiecesResponse(players.getPiecesByColor(Color.WHITE), players.getPiecesByColor(Color.BLACK));
        OutputView.printInitializedChessBoard(piecesResponse);
    }

    private void status(final CommandManagement commandManagement) {
        OutputView.printStatus(players.calculateScore());
    }

    private void move(final CommandManagement commandManagement) {
        String inputMovablePiece = commandManagement.getMovablePiece();
        String inputTargetPosition = commandManagement.getTargetPosition();

        try {
            players.movePiece(turnDao, inputMovablePiece, inputTargetPosition);
            OutputView.printChessBoardStatus(
                    new PiecesResponse(players.getPiecesByColor(Color.WHITE), players.getPiecesByColor(Color.BLACK)));
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    private void end(final CommandManagement commandManagement) {
    }

}
