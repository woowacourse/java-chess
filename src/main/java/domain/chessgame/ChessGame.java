package domain.chessgame;

import domain.board.Board;
import domain.board.Score;
import domain.piece.Color;
import domain.piece.Piece;
import domain.position.Position;
import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

public class ChessGame implements Serializable {

    private final Board board;
    private boolean isPlaying;
    private boolean isBlackTurn;

    public ChessGame() {
        board = new Board();
        board.initChessPieces();
        isPlaying = false;
        isBlackTurn = false;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void move(Position source, Position target) {
        validateTurn(board.piece(source));
        Piece targetPiece = board.piece(target);
        board.move(source, target);
        if (targetPiece.isKing()) {
            exit();
            return;
        }
        changeTurn();
    }

    private void changeTurn() {
        isBlackTurn = !isBlackTurn;
    }

    private void validateTurn(Piece piece) {
        if (piece.isNotEmpty() && piece.isBlack() != isBlackTurn) {
            throw new IllegalArgumentException("[Error] 해당 기물의 턴이 아닙니다.");
        }
    }

    public void start() {
        isPlaying = true;
    }

    public Board board() {
        return board;
    }

    public Map<Position, Piece> pieces() {
        return board.getPieces();
    }

    public Score score(Color color) {
        return board.piecesScore(color);
    }

    public void exit() {
        isPlaying = false;
    }

    public boolean isKingAlive(Color color) {
        return board.isKingAlive(color);
    }

    public void operate(boolean isRestart, boolean isPlaying) {
        if (isRestart) {
            restart();
            return;
        }
        if (isPlaying) {
            this.isPlaying = true;
            return;
        }
        this.isPlaying = false;
    }

    private void restart() {
        board.initChessPieces();
        isPlaying = true;
        isBlackTurn = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessGame chessGame = (ChessGame) o;
        return isPlaying == chessGame.isPlaying && isBlackTurn == chessGame.isBlackTurn
            && Objects.equals(board, chessGame.board);
    }

    @Override
    public int hashCode() {
        return Objects.hash(board, isPlaying, isBlackTurn);
    }
}
