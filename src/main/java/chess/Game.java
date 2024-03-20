package chess;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Color;
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

        Color nowTurn = Color.WHITE;
        while (command.equals("start")) {
            outputView.printBoard(createResponses(board.getPieces()));
            List<String> movement = inputView.readMovement();
            Square from = Square.from(movement.get(0));
            Square to = Square.from(movement.get(1));
            board.move(from, to, nowTurn);
            nowTurn = flip(nowTurn);
        }
    }

    private List<PieceResponse> createResponses(final Map<Square, Piece> pieces) {
        List<PieceResponse> responses = new ArrayList<>();
        for (Entry<Square, Piece> positionToPiece : pieces.entrySet()) {
            responses.add(PieceResponse.of(positionToPiece.getKey(), positionToPiece.getValue()));
        }
        return responses;
    }

    private Color flip(Color color) {
        if (color.equals(Color.WHITE)) {
            return Color.BLACK;
        }
        return Color.WHITE;
    }
}
