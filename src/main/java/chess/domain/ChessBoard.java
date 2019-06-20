package chess.domain;

import chess.domain.exceptions.IllegalSourceException;
import chess.domain.exceptions.IllegalTargetException;
import chess.domain.piece.King;

public class ChessBoard {
    private Team turn;
    private Board board;
    private ResultCounter resultCounter;

    public ChessBoard() {
        this.turn = Team.WHITE;
        this.board = Board.getInstance();
        this.resultCounter = ResultCounter.getInstance();
    }

    public boolean move(Position source, Position target) {
        Piece sourcePiece = board.at(source);
        Piece targetPiece = board.at(target);

        if (sourcePiece == null) {
            throw new IllegalSourceException("해당 위치에 말이 없습니다.");
        }

        if (!sourcePiece.isSameTeam(turn)) {
            throw new IllegalSourceException("당신의 말이 이동할 수 있는 턴이 아닙니다.");
        }

        if (targetPiece != null && sourcePiece.isSameTeam(targetPiece)) {
            throw new IllegalTargetException("같은 팀이 있는 위치로 이동이 불가능합니다.");
        }

        Direction direction = source.direction(target);
        if (!validRoute(source, target, direction)) {
            throw new IllegalTargetException("경로에 말이 존재합니다."); // todo: Exception 이름 변경
        }

        sourcePiece.canMove(source, target);
        board.move(source, target, sourcePiece);
        resultCounter.addCount(targetPiece);
        turn = turn.turnChanged();
        return gameEnd(targetPiece);
    }

    private boolean gameEnd(Piece piece) {
        return !(piece instanceof King);
    }

    public double totalScore(Team team) {
        return resultCounter.totalScore(team);
    }

    private boolean validRoute(final Position source, final Position target, final Direction direction) {
        if (direction == Direction.OTHER) {
            return true;
        }
        for (Position checking = source.add(direction); !checking.equals(target); checking = checking.add(direction)) {
            if (board.at(checking) != null) {
                return false;
            }
        }
        return true;
    }

    public Board getBoard() {
        return this.board;
    }
}
