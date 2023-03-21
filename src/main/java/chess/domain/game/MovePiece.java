package chess.domain.game;

import chess.domain.board.ChessBoard;
import chess.domain.board.Turn;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.position.PiecePosition;

import java.util.List;

public class MovePiece implements ChessGameStep {

    private final ChessBoard chessBoard;
    private final Turn turn;

    public MovePiece(final ChessBoard chessBoard, final Turn turn) {
        this.chessBoard = chessBoard;
        this.turn = turn;
    }

    @Override
    public boolean playable() {
        return true;
    }

    @Override
    public ChessGameStep initialize() {
        throw new IllegalStateException("이미 시작된 상태입니다.");
    }

    @Override
    public ChessGameStep movePiece(final PiecePosition source, final PiecePosition destination) {
        chessBoard.movePiece(turn, source, destination);

        return judgeState();
    }

    private ChessGameStep judgeState() {
        if (chessBoard.existKingByColor(turn.enemyColor())) {
            return new MovePiece(chessBoard, turn.change());
        }
        return new EndGame(turn.color());
    }

    @Override
    public ChessGameStep end() {
        return new EndGame(Color.NONE);
    }

    @Override
    public List<Piece> pieces() {
        return chessBoard.pieces();
    }

    @Override
    public Color winColor() {
        throw new IllegalArgumentException("아직 게임이 진행중입니다.");
    }
}
