package chess.domain.game;

import static java.lang.String.format;

import chess.domain.board.Board;
import chess.domain.board.BoardSnapShot;
import chess.domain.board.Square;
import chess.domain.game.state.GameState;
import chess.domain.game.state.StartState;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;

public class ChessGame {

    private final Board board;

    private GameState gameState = GameState.INIT;

    public ChessGame(final Board board) {
        this.board = board;
    }

    public void start() {
        gameState.start();
        gameState = new GameState(Color.WHITE, new StartState());
    }

    public double calculateScore(final Color color) {
        return gameState.calculateScoreOfColor(color, this);
    }

    public void move(final Square source, final Square destination) {
        gameState.move(source, destination, this);
        gameState = gameState.nextTurnByMove();
    }

    public void end() {
        gameState.end();
        gameState = gameState.terminate();
    }

    public void movePiece(final Square source, final Square destination) {
        final Piece piece = board.findPieceOf(source)
                .orElseThrow(() -> new IllegalArgumentException("움직일 기물이 존재하지 않습니다."));
        if (!gameState.isTurn(piece)) {
            throw new IllegalArgumentException(format("%s의 차례입니다.", gameState.getTurnColor()));
        }
        movePiece(piece, source, destination);
    }

    private void movePiece(final Piece piece, final Square source, final Square destination) {
        final BoardSnapShot boardSnapShot = board.getBoardSnapShot();
        if (!piece.isMovable(source, destination, boardSnapShot)) {
            throw new IllegalArgumentException("움직일 수 없는 위치입니다.");
        }
        board.move(source, destination);
    }

    public double calculateScoreOfColor(final Color color) {
        return board.calculateScoreOfColor(color);
    }

    public boolean isRunning() {
        return gameState.isRunning();
    }

    public Board getBoard() {
        return board;
    }
}
