package chess.controller;

import chess.domain.game.Game;
import chess.domain.pieces.piece.Piece;
import chess.domain.square.Square;
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
        String command = inputView.readStartCommand();

        Game game = new Game();
        while (command.equals("start")) {
            outputView.printBoard(createBoardResponse(game.getBoardStatus()));
            MoveRequest moveRequest = inputView.readMovement();
            game.movePiece(moveRequest.source(), moveRequest.target());
        }
    }

    private List<PieceResponse> createBoardResponse(final Map<Square, Piece> pieces) {
        List<PieceResponse> responses = new ArrayList<>();
        for (Entry<Square, Piece> positionToPiece : pieces.entrySet()) {
            responses.add(PieceResponse.of(positionToPiece.getKey(), positionToPiece.getValue()));
        }
        return responses;
    }
}
