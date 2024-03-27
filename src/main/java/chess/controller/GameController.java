package chess.controller;

import chess.domain.board.ChessBoardFactory;
import chess.domain.game.Game;
import chess.domain.game.GameResult;
import chess.domain.pieces.piece.Piece;
import chess.domain.square.File;
import chess.domain.square.Movement;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import chess.dto.GameCommand;
import chess.dto.GameRequest;
import chess.dto.MoveRequest;
import chess.dto.PieceResponse;
import chess.service.GameService;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Supplier;

public class GameController {
    private static final String GAME_ALREADY_START = "게임이 이미 진행중입니다.";
    private static final String FIRST_START_EXCEPTION = "게임 시작을 위해 start를 입력해주세요.";

    private final InputView inputView;
    private final OutputView outputView;
    private final GameService gameService;

    public GameController(final InputView inputView, final OutputView outputView, final GameService gameService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.gameService = gameService;
    }

    public void start(final long roomId) {
        outputView.printGameStartMessage();
        GameCommand gameCommand = requestUntilValidated(this::readStartCommand);
        if (gameCommand == GameCommand.START) {
            play(loadGame(roomId));
        }
    }

    private GameCommand readStartCommand() {
        GameRequest request = inputView.readStartCommand();
        GameCommand command = request.getCommand();

        if (command == GameCommand.START) {
            return command;
        }
        if (command == GameCommand.END) {
            return command;
        }
        throw new IllegalArgumentException(FIRST_START_EXCEPTION);
    }

    private Game loadGame(final long roomId) {
        List<Movement> moves = gameService.findMoves(roomId);
        return Game.load(roomId, moves, new ChessBoardFactory());
    }

    private void play(final Game game) {
        outputView.printBoard(createBoardResponse(game.getBoardStatus()));
        while (requestUntilValidated(() -> playOneRound(game)) != GameCommand.END) {
            outputView.printBoard(createBoardResponse(game.getBoardStatus()));
        }
    }

    private GameCommand playOneRound(final Game game) {
        GameRequest request = inputView.readStartCommand();
        GameCommand commandType = request.getCommand();
        if (commandType == GameCommand.START) {
            throw new IllegalArgumentException(GAME_ALREADY_START);
        }
        if (commandType == GameCommand.STATUS) {
            outputView.printStatus(game.getResult());
        }
        if (commandType == GameCommand.MOVE) {
            move(game, request);
            GameResult gameResult = game.getResult();
            if (gameResult.isGameOver()) {
                return GameCommand.END;
            }
        }
        return commandType;
    }

    private void move(final Game game, final GameRequest request) {
        MoveRequest moveRequest = request.getMoveRequest();
        String[] source = moveRequest.source().split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
        File sourceFile = File.from(source[0]);
        Rank sourceRank = Rank.from(source[1]);

        String[] target = moveRequest.target().split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
        File targetFile = File.from(target[0]);
        Rank targetRank = Rank.from(target[1]);

        game.movePiece(Square.of(sourceFile, sourceRank), Square.of(targetFile, targetRank));
        gameService.createMove(game.getRoomId(), moveRequest.source(), moveRequest.target());
    }

    private List<PieceResponse> createBoardResponse(final Map<Square, Piece> pieces) {
        List<PieceResponse> responses = new ArrayList<>();
        for (Entry<Square, Piece> squareToPiece : pieces.entrySet()) {
            responses.add(PieceResponse.of(squareToPiece.getKey(), squareToPiece.getValue()));
        }
        return responses;
    }

    private <T> T requestUntilValidated(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return requestUntilValidated(supplier);
        }
    }
}
