package chess.domain.game;

import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardFactory;
import chess.domain.board.Turn;
import chess.domain.piece.Piece;
import chess.domain.piece.position.PiecePosition;

import java.util.List;

public class ChessGame {

    private final ChessBoardFactory factory;
    private ChessBoard chessBoard;
    private Turn turn;

    public ChessGame(final ChessBoardFactory factory, final Turn turn) {
        this.factory = factory;
        this.turn = turn;
    }

    public boolean isInitialized() {
        return chessBoard != null;
    }

    public void initialize() {
        if (isInitialized()) {
            throw new IllegalArgumentException("이미 초기화되었습니다.");
        }
        this.chessBoard = factory.create();
    }

    public void movePiece(final PiecePosition source, final PiecePosition destination) {
        if (isInitialized()) {
            throw new IllegalArgumentException("아직 게임이 시작되지 않았습니다.");
        }
        chessBoard.movePiece(turn, source, destination);
        turn = turn.change();
    }

    public List<Piece> pieces() {
        return chessBoard.pieces();
    }
}
