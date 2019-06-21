package chess.domain;

import chess.domain.exceptions.InvalidRouteException;
import chess.domain.exceptions.IllegalSourceException;
import chess.domain.exceptions.IllegalTargetException;
import chess.domain.piece.King;
import chess.view.WebUtil;
import javafx.geometry.Pos;

import java.util.HashMap;
import java.util.Map;

public class ChessBoard {
    private Turn turn;
    private Board board;
    private ResultCounter resultCounter;

    public ChessBoard() {
        this.turn = Turn.init();
        this.board = Board.init();
        this.resultCounter = ResultCounter.init();
    }

    public boolean move(Position source, Position target) {
        AbstractPiece sourcePiece = board.at(source);
        AbstractPiece targetPiece = board.at(target);

        validSourceTarget(sourcePiece, targetPiece);
        validRoute(source, target, source.direction(target));

        sourcePiece.canMove(source, target);
        board.move(source, target, sourcePiece);
        resultCounter.addCount(targetPiece);
        turn.turnChanged();
        return gameEnd(targetPiece);
    }

    private void validSourceTarget(final AbstractPiece sourcePiece, final AbstractPiece targetPiece) {
        validSourcePiece(sourcePiece);
        validTurn(sourcePiece);
        validTargetPosition(sourcePiece, targetPiece);
    }

    private void validSourcePiece(final AbstractPiece sourcePiece) {
        if (sourcePiece == null) {
            throw new IllegalSourceException("해당 위치에 말이 없습니다.");
        }
    }

    private void validTurn(final AbstractPiece sourcePiece) {
        if (!sourcePiece.isTurn(turn)) {
            throw new IllegalSourceException("당신의 말이 이동할 수 있는 턴이 아닙니다.");
        }
    }

    private void validTargetPosition(final AbstractPiece sourcePiece, final AbstractPiece targetPiece) {
        if (targetPiece != null && sourcePiece.isSameTeam(targetPiece)) {
            throw new IllegalTargetException("같은 팀이 있는 위치로 이동이 불가능합니다.");
        }
    }

    private void validRoute(final Position source, final Position target, final Direction direction) {
        if (!isValidRoute(source, target, direction)) {
            throw new InvalidRouteException("경로에 말이 존재합니다.");
        }
    }

    private boolean gameEnd(AbstractPiece abstractPiece) {
        return !(abstractPiece instanceof King);
    }

    public double totalScore(Team team) {
        return resultCounter.totalScore(team);
    }

    private boolean isValidRoute(final Position source, final Position target, final Direction direction) {
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

    public Map<Position, AbstractPiece> getBoard() {
        return board.getBoard();
    }

    public Map<String, String> getBoard2() {
        Map<String, String> result = new HashMap<>();
        board.getBoard().forEach((key, value) -> {
            String resultValue = value.getName();
            if (value.getTeam() == Team.BLACK) {
                resultValue = resultValue.toUpperCase();
            }
            result.put(WebUtil.positionParser(key), resultValue);
        });
        return result;
    }

    public Team getWinner() {
        return turn.getTeam();
    }
}
