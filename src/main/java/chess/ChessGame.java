package chess;

import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.Result;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
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

        Piece sourcePiece = chessBoard.findPiece(new Position(source));

        validateTurn(sourcePiece);

        chessBoard.move(source, target);
        turn = turn.getOppositeColor();
    }

    private void validateTurn(Piece sourcePiece) {
        if (turn.isNotTurn(sourcePiece.getColor())) {
            throw new IllegalArgumentException();
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
