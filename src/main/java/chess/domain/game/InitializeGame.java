package chess.domain.game;

import chess.domain.board.ChessBoard;
import chess.domain.board.Turn;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.position.PiecePosition;

import java.util.List;

public class InitializeGame implements ChessGameStep {

    private final ChessBoard chessBoard;

    public InitializeGame(final ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    @Override
    public ChessGameStep initialize() {
        return new MovePiece(chessBoard, new Turn(Color.WHITE));
    }

    @Override
    public ChessGameStep movePiece(final PiecePosition source, final PiecePosition destination) {
        throw new IllegalArgumentException("아직 게임이 초기화되지 않았습니다.");
    }

    @Override
    public List<Piece> pieces() {
        throw new IllegalArgumentException("아직 게임이 초기화되지 않았습니다.");
    }
}
