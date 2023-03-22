package chess.domain.game.state;

import chess.domain.board.ChessBoard;
import chess.domain.board.Turn;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.position.PiecePosition;

import java.util.List;
import java.util.Map;

public class InitializeGame implements ChessGameState {

    private final ChessBoard chessBoard;

    public InitializeGame(final ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    @Override
    public boolean playable() {
        return true;
    }

    @Override
    public ChessGameState initialize() {
        return new MovePiece(chessBoard, new Turn(Color.WHITE));
    }

    @Override
    public ChessGameState movePiece(final PiecePosition source, final PiecePosition destination) {
        throw new IllegalArgumentException("아직 게임이 초기화되지 않았습니다.");
    }

    @Override
    public ChessGameState end() {
        throw new IllegalArgumentException("아직 게임이 초기화되지 않았습니다.");
    }

    @Override
    public List<Piece> pieces() {
        throw new IllegalArgumentException("아직 게임이 초기화되지 않았습니다.");
    }

    @Override
    public Color winColor() {
        throw new IllegalArgumentException("아직 게임이 초기화되지 않았습니다.");
    }

    @Override
    public Map<Color, Double> calculateScore() {
        throw new IllegalArgumentException("아직 게임이 초기화되지 않았습니다.");
    }
}
