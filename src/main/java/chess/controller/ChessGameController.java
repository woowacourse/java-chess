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
            List<String> commands = InputView.getCommands();
            Command command = Command.getCommand(commands);
            this.commands.get(command).execute(commands);
            isNotEnd = command.isNotEnd();
        }
        this.commands.get(Command.END).execute(Collections.emptyList());
    }

    private void start(final List<String> command) {
        initializeChessBoard();
        PiecesResponse piecesResponse = new PiecesResponse(players.getPiecesByColor(Color.WHITE), players.getPiecesByColor(Color.BLACK));
        OutputView.printInitializedChessBoard(piecesResponse);
    }

    private void status(final List<String> command) {
        OutputView.printStatus(players.calculateScore());
    }

    private void move(final List<String> command) {
        String inputMovablePiece = command.get(1);
        String inputTargetPosition = command.get(2);

        try {
            players.movePiece(inputMovablePiece, inputTargetPosition);
            OutputView.printChessBoardStatus(
                    new PiecesResponse(players.getPiecesByColor(Color.WHITE), players.getPiecesByColor(Color.BLACK)));
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    private void end(final List<String> command) {
        if (!players.everyKingAlive()) {
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
