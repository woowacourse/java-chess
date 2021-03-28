package domain.chessgame;

import domain.board.Board;
import domain.board.Score;
import domain.piece.Piece;
import domain.position.Position;

public class ChessGame {

    protected final Board board;
    private boolean isPlaying;
    private boolean isBlackTurn;

    public ChessGame() {
        board = new Board();
        board.initChessPieces();
        this.isPlaying = false;
        this.isBlackTurn = false;
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
        if (piece.isBlack() != isBlackTurn) {
            throw new IllegalArgumentException("[Error] 해당 기물의 턴이 아닙니다.");
        }
    }

    public void start() {
        isPlaying = true;
    }

    public Board board() {
        return board;
    }

    public Score score(boolean isBlack) {
        return board.piecesScore(isBlack);
    }

    public void exit() {
        isPlaying = false;
    }

    public boolean isKingAlive(boolean isBlack) {
        return board.isKingAlive(isBlack);
    }

}
