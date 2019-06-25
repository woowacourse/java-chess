package chess.domain;

import chess.domain.exceptions.IllegalSourceException;
import chess.domain.exceptions.IllegalTargetException;
import chess.domain.exceptions.InvalidRouteException;
import chess.dto.BoardDto;
import chess.dto.TurnDto;

import java.util.List;

public class ChessBoard {
    private Turn turn;
    private Board board;
    private ResultCounter resultCounter;

    public ChessBoard() {
        this.turn = Turn.init();
        this.board = Board.init();
        this.resultCounter = ResultCounter.init();
    }

    public ChessBoard(Board board, Turn turn, ResultCounter resultCounter) {
        this.turn = turn;
        this.board = board;
        this.resultCounter = resultCounter;
    }

    public Piece move(Position source, Position target) {
        Piece sourcePiece = board.at(source);
        Piece targetPiece = board.at(target);

        validSourceTarget(sourcePiece, targetPiece);
        validRoute(source, target, source.direction(target));

        sourcePiece.canMove(source, target);
        board.move(source, target, sourcePiece);
        resultCounter.addCount(targetPiece);
        turn.turnChanged();
        return targetPiece;
    }

    private void validSourceTarget(final Piece sourcePiece, final Piece targetPiece) {
        validSourcePiece(sourcePiece);
        validTurn(sourcePiece);
        validTargetPosition(sourcePiece, targetPiece);
    }

    private void validSourcePiece(final Piece sourcePiece) {
        if (sourcePiece == null) {
            throw new IllegalSourceException("해당 위치에 말이 없습니다.");
        }
    }

    private void validTurn(final Piece sourcePiece) {
        if (!sourcePiece.isTurn(turn)) {
            throw new IllegalSourceException("당신의 말이 이동할 수 있는 턴이 아닙니다.");
        }
    }

    private void validTargetPosition(final Piece sourcePiece, final Piece targetPiece) {
        if (targetPiece != null && sourcePiece.isSameTeam(targetPiece)) {
            throw new IllegalTargetException("같은 팀이 있는 위치로 이동이 불가능합니다.");
        }
    }

    private void validRoute(final Position source, final Position target, final Direction direction) {
        if (!isValidRoute(source, target, direction)) {
            throw new InvalidRouteException("경로에 말이 존재합니다.");
        }
    }

    private boolean isValidRoute(final Position source, final Position target, final Direction direction) {
        if (direction == Direction.KNIGHT) {
            return true;
        }
        for (Position checking = source.add(direction); !checking.equals(target); checking = checking.add(direction)) {
            if (board.at(checking) != null) {
                return false;
            }
        }
        return true;
    }

    public double totalScore(Team team) {
        return resultCounter.totalScore(team);
    }

    public TurnDto turnToDto() {
        return turn.toDto();
    }

    public List<BoardDto> boardToDto() {
        return board.toDto();
    }

    public Team getWinner() {
        return turn.getTeam() == Team.BLACK ? Team.WHITE : Team.BLACK;
    }
}
