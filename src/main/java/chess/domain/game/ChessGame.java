package chess.domain.game;

import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import chess.domain.piece.Blank;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import java.util.List;

public class ChessGame {

    public static final String TURN_MESSAGE = "[ERROR] %s의 차례입니다.";
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;

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
        Piece sourcePiece = chessBoard.getPiece(Position.of(source));

        validateTurn(sourcePiece);

        try {
            chessBoard.move(source, target);
            turn = turn.getOppositeColor();
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private void validateTurn(Piece sourcePiece) {
        if (sourcePiece.isBlank()) {
            throw new IllegalArgumentException(Blank.BLANK_ERROR);
        }
        if (!sourcePiece.isSameColor(turn)) {
            throw new IllegalArgumentException(String.format(TURN_MESSAGE, turn.name()));
        }
    }

    public boolean isOver() {
        return chessBoard.isOver();
    }

    public Result gameResult() {
        return new Result(chessBoard.getChessBoard());
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }
}
