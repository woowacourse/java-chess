package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.command.Command;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.score.ScoreCalculator;
import java.util.Map;

import chess.domain.ChessScore;
import chess.domain.position.Position;

public class Running extends State {

    protected static final String CANNOT_MOVE_OPPONENT_PIECE = "상대 말을 움지이게 할 수 없습니다.";
    private static final String CANNOT_START_AGAIN = "게임이 시작된 이후에 또 시작할 수 없습니다.";

    private final Color color;

    protected Running(Map<Position, Piece> pieces, Color color) {
        this.board = new Board(pieces);
        this.color = color;
    }

    @Override
    public State proceed(Command command) {
        validateStart(command);
        if (command.isStatus()) {
            return this;
        }
        if (command.isMove()) {
            validateMoveOpponent(command);
            board.movePiece(command.getFromPosition(), command.getToPosition());
            return checkWhiteKingExist();
        }
        return new Finished(board.getPieces());
    }

    private void validateStart(Command command) {
        if (command.isStart()) {
            throw new IllegalArgumentException(CANNOT_START_AGAIN);
        }
    }

    private void validateMoveOpponent(Command command) {
        if (board.findPiece(command.getFromPosition()).isSameColor(this.color.invert())) {
            throw new IllegalArgumentException(CANNOT_MOVE_OPPONENT_PIECE);
        }
    }

    private State checkWhiteKingExist() {
        if (board.isKingNotExist(this.color.invert())) {
            return new Finished(this.board.getPieces());
        }
        return new Running(board.getPieces(), this.color.invert());
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public ChessScore generateScore() {
        return ScoreCalculator.calculateChessScore(this.board.getPieces());
    }

    @Override
    public boolean isWhite() {
        return this.color == Color.WHITE;
    }

    @Override
    public Color getColor() {
        return this.color;
    }
}
