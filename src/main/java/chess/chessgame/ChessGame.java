package chess.chessgame;

import chess.chessboard.ChessBoard;
import chess.chessboard.Position;
import chess.chessboard.Side;
import chess.piece.Piece;
import chess.piece.PieceType;

import java.util.Map;

public class ChessGame {

    private final ChessBoard chessBoard;
    private Side turn;
    private Side winner;

    public ChessGame(final Side turn, final ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
        this.turn = turn;
        this.winner = Side.EMPTY;
    }

    public ChessGame(final ChessBoard chessBoard) {
        this(Side.initialTurn(), chessBoard);
    }

    public void move(final Position source, final Position destination) {
        validatePlayerTurn(source);
        validateGameContinuing();

        final Piece capturedPiece = chessBoard.moveAndCapture(source, destination);

        checkWinnerDecided(capturedPiece);

        turn = turn.nextTurn();
    }

    private void validateGameContinuing() {
        if (isKingDead()) {
            throw new IllegalStateException("왕이 죽어서 이동할 수 없습니다");
        }
    }

    private void checkWinnerDecided(final Piece capturedPiece) {
        if (capturedPiece.getPieceType() == PieceType.KING) {
            checkWinner(capturedPiece);
        }
    }

    private void checkWinner(final Piece capturedPiece) {
        if (capturedPiece.getSide() == Side.WHITE) {
            winner = Side.BLACK;
            return;
        }
        winner = Side.WHITE;
    }

    private void validatePlayerTurn(final Position source) {
        final Side side = chessBoard.getPieceSideAt(source);
        if (!turn.isTurnOf(side)) {
            throw new IllegalArgumentException("공격 순서가 잘못되었습니다");
        }
    }

    public boolean isKingDead() {
        return winner != Side.EMPTY;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public double getScore(Side side) {
        final Map<Position, Piece> piecesOfSide = chessBoard.getPieces(side);
        final PlayerScore playerScore = PlayerScore.from(piecesOfSide);
        return playerScore.getPlayerScore();
    }

    public String getWinner() {
        return winner.name();
    }

    public Side getTurn() {
        return turn;
    }
}
