package chess.domain;

import chess.domain.piece.Piece;

import java.util.Map;

public class ChessGame {
    private final Board board;
    private Team turn;

    public ChessGame() {
        this.board = new Board();
    }

    public Map<Position, Piece> start() {
        if (notStarted()) {
            turn = Team.WHITE;
            return board.getBoard();
        }
        throw new IllegalArgumentException("start는 한 번만 입력될 수 있습니다.");
    }

    public Map<Position, Piece> move(Position source, Position target) {
        validateMove(source, target);
        if (checkTurn(source, turn)) {
            board.movePieceAndRenewBoard(source, target);
            turn = turnChange();
            return board.getBoard();
        }
        throw new IllegalArgumentException(String.format("%s의 차례입니다.", turn.name()));
    }

    private void validateMove(Position source, Position target) {
        if (notStarted()) {
            throw new IllegalArgumentException("start가 입력되지 전에 move를 수행할 수 없습니다.");
        }
        if (source.equals(target)) {
            throw new IllegalArgumentException("source좌표와 target좌표가 같을 수 없습니다.");
        }
    }

    private boolean notStarted() {
        return turn != Team.WHITE && turn != Team.BLACK;
    }

    private boolean checkTurn(Position source, Team turn) {
        return board.isSameTeamFromPosition(source, turn);
    }

    private Team turnChange() {
        if (turn == Team.WHITE) {
            return Team.BLACK;
        }
        return Team.WHITE;
    }
}
