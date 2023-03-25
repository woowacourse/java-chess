package chess.chessgame;

import chess.Turn;
import chess.chessboard.ChessBoard;
import chess.chessboard.Side;
import chess.chessboard.Square;
import chess.piece.Piece;

public class ChessGame {
    private final ChessBoard chessBoard;
    private Turn turn;

    public ChessGame(final ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
        this.turn = Turn.initialTurn();
    }

    public Piece move(final Square source, final Square destination) {
        validatePlayerTurn(source);
        final Piece capturedPiece = chessBoard.move(source, destination);

        turn = turn.nextTurn();

        return capturedPiece;
    }

    private void validatePlayerTurn(final Square source) {
        final Side side = chessBoard.getPieceSideAt(source);
        if (!turn.isTurnOf(side)) {
            throw new IllegalArgumentException("공격 순서가 잘못되었습니다");
        }
    }
}
