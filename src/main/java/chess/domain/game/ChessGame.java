package chess.domain.game;

import chess.domain.board.ChessBoard;
import chess.domain.board.Turn;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.position.PiecePosition;

import java.util.List;
import java.util.Map;

public class ChessGame {

    private final Long id;
    private final ChessBoard chessBoard;
    private final GameState state;
    private final Turn turn;
    private final Color winner;

    private ChessGame(final Long id,
                      final ChessBoard chessBoard,
                      final GameState state,
                      final Turn turn,
                      final Color winner) {
        this.id = id;
        this.chessBoard = chessBoard;
        this.state = state;
        this.turn = turn;
        this.winner = winner;
    }

    public static ChessGame start(final ChessBoard chessBoard) {
        return new ChessGame(
                null,
                chessBoard,
                GameState.RUN,
                new Turn(Color.WHITE),
                null);
    }

    public static ChessGame resume(final Long id, final ChessBoard chessBoard, final Turn turn) {
        return new ChessGame(id, chessBoard, GameState.RUN, turn, null);
    }

    public static ChessGame end(final Long id, final ChessBoard chessBoard, final Color winner) {
        return new ChessGame(id, chessBoard, GameState.END, null, winner);
    }

    public ChessGame movePiece(final PiecePosition source, final PiecePosition destination) {
        validateRunning();
        chessBoard.movePiece(turn, source, destination);
        if (killEnemyKing()) {
            return ChessGame.end(id, chessBoard, turn.color());
        }
        return ChessGame.resume(id, chessBoard, turn.change());
    }

    private void validateRunning() {
        if (state != GameState.RUN) {
            throw new IllegalArgumentException("게임이 진행 중이 아닙니다.");
        }
    }

    private boolean killEnemyKing() {
        return !chessBoard.existKingByColor(turn.enemyColor());
    }

    public boolean playable() {
        return state == GameState.RUN;
    }

    public Color winColor() {
        if (state != GameState.END) {
            throw new IllegalArgumentException("아직 게임이 끝나지 않았습니다.");
        }
        return winner;
    }

    public List<Piece> pieces() {
        return chessBoard.pieces();
    }

    public Map<Color, Double> calculateScore() {
        return chessBoard.calculateScore();
    }

    public Long id() {
        return id;
    }

    public GameState state() {
        return state;
    }

    public Color turnColor() {
        return turn.color();
    }
}
