package domain.chessgame;

import domain.Player;
import domain.Status;
import domain.piece.Piece;
import domain.position.Position;

public class ChessGame {

    private final ChessBoard chessBoard;
    private Player currentPlayer;

    public ChessGame(final ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
        this.currentPlayer = Player.WHITE;
    }

    public void move(final Position source, final Position target) {
        validateTurn(source);
        chessBoard.move(source, target);
        changeTurn();
    }

    public Status status() {
        double whiteScore = chessBoard.calculateScoreByPlayer(Player.WHITE);
        double blackScore = chessBoard.calculateScoreByPlayer(Player.BLACK);
        return new Status(whiteScore, blackScore);
    }

    private void validateTurn(final Position source) {
        Piece sourcePiece = chessBoard.findPiece(source);
        if (sourcePiece != null && !sourcePiece.isSamePlayer(currentPlayer)) {
            throw new IllegalArgumentException("[ERROR] 상대방의 기물을 움직일 수 없습니다.");
        }
    }

    public void changeTurn() {
        if (currentPlayer.equals(Player.WHITE)) {
            this.currentPlayer = Player.BLACK;
            return;
        }
        this.currentPlayer = Player.WHITE;
    }

    public boolean isFinished() {
        return chessBoard.isKingOnlyOne();
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }
}
