package chess.domain.chessGame;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;

public class ChessGame {
    private ChessGameState chessGameState = new ReadyChessGameState();
    private final Board board;
    private Color turn = Color.WHITE;

    public ChessGame() {
        board = new Board();
    }

    public ChessGame(Board board, ChessGameState chessGameState, Color turn) {
        this.board = board;
        this.chessGameState = chessGameState;
        this.turn = turn;
    }

    public void start() {
        chessGameState = chessGameState.start();
    }

    public void move(String currentPosition, String nextPosition) {
        Piece movingPiece = board.findPieceByPosition(Position.from(currentPosition));
        Piece targetPiece = board.findPieceByPosition(Position.from(nextPosition));
        chessGameState.validateMove(currentPosition, nextPosition, movingPiece);
        checkTurn(movingPiece);
        board.move(Position.from(currentPosition), Position.from(nextPosition));
        if (isOver(targetPiece)) {
            end();
            return;
        }
        turn = turn.getOppositeColor();
    }

    private boolean isOver(Piece targetPiece) {
        return targetPiece.isKing();
    }

    public void end() {
        chessGameState = chessGameState.end();
    }

    public boolean isPlaying() {
        return chessGameState.isPlaying();
    }

    public boolean isEnd() {
        return chessGameState.isEnd();
    }

    private void checkTurn(Piece movingPiece) {
        if (movingPiece.isOpponent(turn)) {
            throw new IllegalArgumentException("상대방 기물의 턴입니다.");
        }
    }

    public boolean isEmpty() {
        return board.size() == 0;
    }

    public String getTurnName() {
        return turn.toString();
    }

    public Map<Position, String> getPrintingBoard() {
        return chessGameState.getPrintingBoard(board);
    }

    public Map<Color, Double> getScores() {
        return chessGameState.getScores(board);
    }

    public Map<Position, Piece> getBoard() {
        return board.getBoard();
    }

    public String getStatusName() {
        return chessGameState.getName();
    }

}
