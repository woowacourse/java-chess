package chess.controller;

import chess.domain.Color;
import chess.domain.Pieces;
import chess.domain.Player;
import chess.domain.Players;
import chess.domain.dto.response.PiecesResponse;
import chess.ui.InputView;
import chess.ui.OutputView;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public final class ChessGameController {

    private Players players;

    private final Map<Command, Action> commands = Map.of(
            Command.START, this::start,
            Command.MOVE, this::move,
            Command.END, this::end,
            Command.STATUS, this::status
    );

    public void run() {
        initializeChessBoard();
        OutputView.printStartGame();

        boolean isNotEnd = true;
        while (isNotEnd && players.everyKingAlive()) {
            Commands commandWithArguments = readCommand(InputView::getCommands);
            this.commands.get(commandWithArguments.getCommand()).execute(commandWithArguments.getArgs());
            isNotEnd = commandWithArguments.isNotEnd();
        }
        this.commands.get(Command.END).execute(Collections.emptyList());
    }

    private Commands readCommand(Supplier<List<String>> cccc) {
        try {
            List<String> arguments = cccc.get();
            return new Commands(arguments);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return readCommand(cccc);
        }
    }

    private void start(final List<String> arguments) {
        initializeChessBoard();
        PiecesResponse piecesResponse = new PiecesResponse(players.getPiecesByColor(Color.WHITE), players.getPiecesByColor(Color.BLACK));
        OutputView.printInitializedChessBoard(piecesResponse);
    }

    private void status(final List<String> arguments) {
        OutputView.printStatus(players.calculateScore());
    }

    private void move(final List<String> arguments) {
        String inputMovablePiece = arguments.get(0);
        String inputTargetPosition = arguments.get(1);

        try {
            players.movePiece(inputMovablePiece, inputTargetPosition);
            OutputView.printChessBoardStatus(
                    new PiecesResponse(players.getPiecesByColor(Color.WHITE), players.getPiecesByColor(Color.BLACK)));
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    private void end(final List<String> command) {
        if (!players.everyKingAlive()) { // 부정문으로 바꿔주세요!!
            OutputView.printWinner(players.getWinnerColorName());
        }
    }

    private void initializeChessBoard() {
        Pieces whitePieces = Pieces.createWhitePieces();
        Pieces blackPieces = Pieces.createBlackPieces(whitePieces);

        Player whitePlayer = Player.fromWhitePlayer(whitePieces);
        Player blackPlayer = Player.fromBlackPlayer(blackPieces);

        this.players = Players.of(whitePlayer, blackPlayer);
    }

}
