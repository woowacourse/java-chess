package chess.controller;

import chess.domain.board.ChessBoardFactory;
import chess.domain.game.Game;
import chess.domain.game.GameResult;
import chess.domain.pieces.piece.Piece;
import chess.domain.square.Square;
import chess.dto.GameCommand;
import chess.dto.GameRequest;
import chess.dto.MoveRequest;
import chess.dto.PieceResponse;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class GameController {
    private final InputView inputView;
    private final OutputView outputView;

    public GameController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        outputView.printGameStartMessage();
        GameRequest request = inputView.readStartCommand();
        GameCommand command = request.getCommand();

        if (!command.equals(GameCommand.START)) {
            throw new IllegalArgumentException();
        }

        Game game = new Game(new ChessBoardFactory());
        outputView.printBoard(createBoardResponse(game.getBoardStatus()));

        while (playOneRound(game) == GameCommand.END) {
            outputView.printBoard(createBoardResponse(game.getBoardStatus()));
        }
    }

    private GameCommand playOneRound(final Game game) {
        GameRequest request = inputView.readStartCommand();
        GameCommand commandType = request.getCommand();
        if (commandType == GameCommand.START) {
            throw new IllegalArgumentException("게임이 진행중입니다.");
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
        game.movePiece(moveRequest.source(), moveRequest.target());
    }

    private List<PieceResponse> createBoardResponse(final Map<Square, Piece> pieces) {
        List<PieceResponse> responses = new ArrayList<>();
        for (Entry<Square, Piece> squareToPiece : pieces.entrySet()) {
            responses.add(PieceResponse.of(squareToPiece.getKey(), squareToPiece.getValue()));
        }
        return responses;
    }
}
