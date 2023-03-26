package chess.domain.game;

import java.util.Map;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.exception.DifferentTeamException;
import chess.domain.exception.NotPlayableException;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class Game {

    private Team turn;
    private final Board board;

    public Game() {
        this.turn = Team.WHITE;
        this.board = BoardFactory.createBoard();
    }

    public Game(Team turn, Board board) {
        this.turn = turn;
        this.board = board;
    }

    public void movePiece(Position source, Position target) {
        validateNotFinished(board);
        validateTurn(turn, source);
        board.move(source, target);
        changeTurn();
    }

    private void validateNotFinished(Board board) {
        if (isFinished(board)) {
            throw new NotPlayableException("왕 없이 플레이할 수 없습니다");
        }
    }

    private boolean isFinished(Board board) {
        return !board.hasKing(Team.WHITE) || !board.hasKing(Team.BLACK);
    }

    private void validateTurn(Team turn, Position position) {
        if (!board.hasPositionTeamOf(position, turn)) {
            throw new DifferentTeamException("자신의 기물만 움직일 수 있습니다");
        }
    }

    private void changeTurn() {
        turn = turn.alter();
    }

    public Map<Position, Piece> getPieces() {
        return board.getPieces();
    }

    public boolean isFinished() {
        return isFinished(board);
    }
}
