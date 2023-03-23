package chess.controller;

import chess.domain.Color;
import chess.domain.dto.res.PiecesResponse;
import chess.domain.Pieces;
import chess.domain.Player;
import chess.domain.Players;
import chess.ui.InputView;
import chess.ui.OutputView;

import java.util.List;
import java.util.Map;

public final class ChessGameController {

    private final Players players = initializeChessBoard();

    private final Map<Command, Action> commands = Map.of(
            Command.START, this::start,
            Command.MOVE, this::move,
            Command.END, this::end
    );

    public void run() {
        OutputView.printStartGame();
        List<String> commands = InputView.getCommands();
        Command findCommand = Command.findCommand(commands);
        this.commands.get(findCommand).execute(commands);
    }

    private void start(final List<String> strings) {
        Command findCommand = Command.START;

        PiecesResponse piecesResponse = new PiecesResponse(players.getPiecesByColor(Color.WHITE), players.getPiecesByColor(Color.BLACK));
        OutputView.printInitializedChessBoard(piecesResponse);

        while (findCommand.isNotEnd()) {
            List<String> commands = InputView.getCommands();
            findCommand = Command.findCommand(commands);
            this.commands.get(findCommand).execute(commands);
        }
    }

    private void move(List<String> command) {
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

    private void end(final List<String> strings) {
    }

    private Players initializeChessBoard() {
        Pieces whitePieces = Pieces.createWhitePieces();
        Pieces blackPieces = Pieces.createBlackPieces(whitePieces);

        Player whitePlayer = Player.fromWhitePlayer(whitePieces);
        Player blackPlayer = Player.fromBlackPlayer(blackPieces);

        return Players.from(whitePlayer, blackPlayer);
    }
}
