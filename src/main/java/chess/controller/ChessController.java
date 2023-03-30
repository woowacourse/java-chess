package chess.controller;

import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardFactory;
import chess.domain.piece.Color;
import chess.domain.piece.position.PiecePosition;
import chess.domain.state.ChessState;
import chess.domain.state.Initialize;
import chess.domain.state.Run;
import chess.domain.state.command.Command;
import chess.service.PieceService;
import chess.service.GameService;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChessController {

    private final GameService gameService;
    private final PieceService pieceService;

    public ChessController(final GameService gameService, final PieceService pieceService) {
        this.gameService = gameService;
        this.pieceService = pieceService;
    }

    public void start() {
        OutputView.printStartMessage();
        final List<Integer> gameIds = gameService.findAllIdsByIsRunning(true);
        initializeWithDB(gameIds);
    }

    private void initializeWithDB(List<Integer> gameIds) {
        if (gameIds.isEmpty()) {
            final ChessBoard chessBoard = ChessBoardFactory.create();
            final int gameId = gameService.create();
            pieceService.create(chessBoard, gameId);
            final ChessState state = new Initialize();
            run(chessBoard, state, gameId);
            return;
        }
        runInProgressGame(gameIds);
    }

    private void runInProgressGame(final List<Integer> gameIds) {
        final ChessBoard chessBoard = new ChessBoard(pieceService.findAllPieces());
        final ChessState state = new Run(gameService.findTurnById(gameIds.get(0)));
        OutputView.printInProgressMessage();
        OutputView.showBoard(chessBoard.pieces());
        run(chessBoard, state, gameIds.get(0));
    }

    private void run(final ChessBoard chessBoard, ChessState state, final int gameId) {
        while (state.isRunnable()) {
            state = chessState(chessBoard, state, gameId);
        }
        if (state.isCheckmate()) {
            pieceService.deleteAll();
            gameService.updateIsRunning(gameId, false);
            OutputView.printCheckmateResult(state.findCurrentTurn());
        }
        OutputView.printEndResult();
    }

    private ChessState chessState(final ChessBoard chessBoard, ChessState state, final int gameId) {
        try {
            final Command command = Command.parse(new ArrayList<>(InputView.readCommand()));
            state = executeByCommand(chessBoard, command, state, gameId);
        } catch (Exception e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
        return state;
    }

    private ChessState executeByCommand(final ChessBoard chessBoard, final Command command, ChessState state,
                                        final int gameId) {
        if (command.isSave()) {
            gameService.updateTurn(gameId, state.findCurrentTurn());
            pieceService.deleteAll();
            pieceService.create(chessBoard, gameId);
        }
        if (command.isStatus()) {
            calculateScore(chessBoard);
            return state;
        }
        state = changeState(chessBoard, command, state);
        gameService.updateTurn(gameId, state.findCurrentTurn());
        OutputView.showBoard(chessBoard.pieces());
        return state;
    }

    private void calculateScore(final ChessBoard chessBoard) {
        final Map<Color, Double> scoreByColor = chessBoard.calculateScore();
        OutputView.printTeamScore(Color.WHITE, scoreByColor.get(Color.WHITE));
        OutputView.printTeamScore(Color.BLACK, scoreByColor.get(Color.BLACK));
    }

    private ChessState changeState(final ChessBoard chessBoard, final Command command, ChessState state) {
        state = state.changeStateByCommand(command);
        if (command.isMove()) {
            state = movePiece(chessBoard, command, state);
        }
        return state;
    }

    private ChessState movePiece(final ChessBoard chessBoard, final Command command, ChessState state) {
        final PiecePosition from = PiecePosition.of(command.getFromParameter());
        final PiecePosition to = PiecePosition.of(command.getToParameter());
        state = chessBoard.movePiece(state, from, to);
        if (state.isRunnable()) {
            return state.changeTurn();
        }
        return state;
    }
}
