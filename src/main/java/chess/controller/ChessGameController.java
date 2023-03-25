package chess.controller;

import chess.domain.Color;
import chess.domain.Piece;
import chess.domain.Pieces;
import chess.domain.Player;
import chess.domain.Players;
import chess.domain.dao.PieceDao;
import chess.domain.dao.PieceDaoImpl;
import chess.dto.response.PiecesResponse;
import chess.ui.InputView;
import chess.ui.OutputView;

import java.util.List;
import java.util.Map;
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
        while (isOnGoing(isNotEnd)) {
            Commands commandWithArguments = readCommand(InputView::getCommands);
            this.commands.get(commandWithArguments.getCommand()).execute(commandWithArguments);
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
            PieceDao dao = new PieceDaoImpl();
            dao.deleteAll();
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

    private void start(final Commands commands) {
        initializeChessBoard();
        PiecesResponse piecesResponse = new PiecesResponse(players.getPiecesByColor(Color.WHITE), players.getPiecesByColor(Color.BLACK));
        OutputView.printInitializedChessBoard(piecesResponse);
    }

    private void status(final Commands commands) {
        OutputView.printStatus(players.calculateScore());
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

    private void initializeChessBoard() {
        PieceDao dao = new PieceDaoImpl();
        Pieces pieces = new Pieces();

        Pieces whitePieces = getDbWhitePieces(dao, pieces);
        Pieces blackPieces = getDbBlackPieces(dao, pieces);

        Player whitePlayer = Player.fromWhitePlayer(whitePieces);
        Player blackPlayer = Player.fromBlackPlayer(blackPieces);

        this.players = Players.of(whitePlayer, blackPlayer);
    }

    private Pieces getDbWhitePieces(PieceDao dao, Pieces pieces) {
        List<Piece> dbWhitePieces = dao.findPieceByColor(Color.WHITE);
        if (dbWhitePieces.isEmpty()) {
            Pieces whitePieces = pieces.createWhitePieces();
            insertAll(dao, whitePieces, Color.WHITE);
            return whitePieces;
        }
        return Pieces.from(dbWhitePieces);
    }

    private Pieces getDbBlackPieces(PieceDao dao, Pieces pieces) {
        List<Piece> dbBlackPieces = dao.findPieceByColor(Color.BLACK);
        if (dbBlackPieces.isEmpty()) {
            Pieces blackPieces = pieces.createBlackPieces();
            insertAll(dao, blackPieces, Color.BLACK);
            return blackPieces;
        }
        return Pieces.from(dbBlackPieces);
    }

    private void insertAll(PieceDao dao, Pieces pieces, Color color) {
        for (Piece piece : pieces.getPieces()) {
            dao.create(piece, color);
        }
    }

}
