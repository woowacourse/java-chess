package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;

public class ChessGame {

    private final Board board;
    private final Turn turn;
    private GameState gameState;

    public ChessGame(Board board, Color firstTurnColor) {
        this.board = board;
        this.turn = new Turn(firstTurnColor);
        this.gameState = GameState.IDLE;
    }

    public void move(Position sourcePosition, Position targetPosition) {
        validateTurn(sourcePosition);
        board.movePiece(sourcePosition, targetPosition);
        checkKingDead();
        turn.next();
    }

    private void checkKingDead() {
        if (board.checkKingDead()) {
            gameState = GameState.END;
        }
    }

    private void validateTurn(Position sourcePosition) {
        Piece piece = board.findPiece(sourcePosition);
        if (turn.isNotCurrentTurn(piece.getColor())) {
            throw new IllegalArgumentException("상대 팀의 말을 옮길 수 없습니다.");
        }
    }

    public void start() {
        this.gameState = GameState.START;
    }

    public void end() {
        this.gameState = GameState.END;
    }

    public boolean isRunning() {
        return gameState != GameState.END;
    }

    public Board getBoard() {
        return board;
    }

    public Map<Color, Double> calculateCurrentScore() {
        return Map.of(Color.BLACK, board.calculateScore(Color.BLACK),
                Color.WHITE, board.calculateScore(Color.WHITE));
    }
}
