package chess.domain.game;

import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import chess.domain.board.Square;
import chess.domain.piece.Color;
import java.util.List;

public class ChessGame {

    public static final String TURN_MESSAGE = "%s의 차례입니다.";
    public static final int SOURCE_INDEX = 1;
    public static final int TARGET_INDEX = 2;

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
        String source = input.get(SOURCE_INDEX);
        String target = input.get(TARGET_INDEX);
        Square sourceSquare = chessBoard.getSquare(Position.of(source));

        validateTurn(sourceSquare);

        chessBoard.move(source, target);
        turn = turn.getOppositeColor();
    }

    private void validateTurn(Square sourceSquare) {
        if (!sourceSquare.hasSameColor(turn)) {
            throw new IllegalArgumentException(String.format(TURN_MESSAGE, turn.name()));
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
