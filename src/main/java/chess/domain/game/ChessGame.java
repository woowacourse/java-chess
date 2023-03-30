package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;

public class ChessGame {
    private final Board board;
    private final Color firstTurnColor;
    private Turn turn;

    public ChessGame(Board board, Color firstTurnColor) {
        this.board = board;
        this.firstTurnColor = firstTurnColor;
        this.turn = new Turn(firstTurnColor);
    }

    public ChessGame() {
        this(new Board(), Color.WHITE);
    }

    public void move(Position source, Position target) {
        validateTurn(source);
        board.movePiece(source, target);
        turn = turn.next();
    }

    private void validateTurn(Position sourcePosition) {
        Piece piece = board.findPiece(sourcePosition);
        if (turn.isNotCurrentTurn(piece.getColor())) {
            throw new IllegalArgumentException("상대 팀의 말을 옮길 수 없습니다.");
        }
    }

    public void start() {
        board.initialize();
        turn = new Turn(firstTurnColor);
    }

    public boolean isEnd() {
        return board.checkKingDead();
    }

    public boolean isNotInitialize() {
        return board.isNotInitialize();
    }

    public GameResult getResult() {
        return new GameResult(Map.copyOf(board.getBoards()));
    }
}
