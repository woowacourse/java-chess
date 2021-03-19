package chess.domain.game;

import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import chess.domain.board.Square;
import chess.domain.piece.Color;
import java.util.List;

public class ChessGame {

    private final ChessBoard chessBoard;
    private Color turn;

    public ChessGame(ChessBoard chessBoard, Color turn) {
        this.chessBoard = chessBoard;
        this.turn = turn;
    }

    public void start() {
        chessBoard.initBoard();
    }

    public void run(List<String> input) {
        String source = input.get(1);
        String target = input.get(2);
        Square sourceSquare = chessBoard.getSquare(new Position(source));

        validateTurn(sourceSquare);

        chessBoard.move(source, target);
        turn = turn.getOppositeColor();
    }

    private void validateTurn(Square sourceSquare) {
        if (!sourceSquare.hasSameColor(turn)) {
            throw new IllegalArgumentException("턴 아님!");
        }
    }

    public boolean isOver() {
        return chessBoard.isOver();
    }

    public Result result() {
        double blackScore = chessBoard.getScore(Color.WHITE);
        double whiteScore = chessBoard.getScore(Color.BLACK);
        return new Result(blackScore, whiteScore);
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }
}
