package chess;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Piece;
import chess.domain.square.Square;
import chess.dto.PieceResponse;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Game {
    private final InputView inputView;
    private final OutputView outputView;

    public Game(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        outputView.printGameStartMessage();
        Board board = BoardFactory.createBoard();
        String command = inputView.readStartCommand();

        if (command.equals("start")) {
            outputView.printBoard(createResponses(board.getPieces()));
        }
    }

    private List<PieceResponse> createResponses(final Map<Square, Piece> pieces) {
        List<PieceResponse> responses = new ArrayList<>();
        for (Entry<Square, Piece> positionToPiece : pieces.entrySet()) {
            responses.add(PieceResponse.of(positionToPiece.getKey(), positionToPiece.getValue()));
        }
        return responses;
    }
}
