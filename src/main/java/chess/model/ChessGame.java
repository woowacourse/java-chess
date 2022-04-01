package chess.model;

import chess.model.board.Board;
import chess.model.command.Command;
import chess.model.piece.Empty;
import chess.model.piece.Piece;
import chess.model.position.Position;

import java.util.List;

public class ChessGame {
    private final Board board;

    public ChessGame(Board board) {
        this.board = board;
    }

    public Turn progress(Command command, Turn turn) {
        if (command.isMove()) {
            move(command.getSourcePosition(), command.getTargetPosition(), turn);
            turn = turn.change();
        }
        return turn;
    }

    public void move(String sourcePosition, String targetPosition, Turn thisTurn) {
        Position source = Position.from(sourcePosition);
        Position target = Position.from(targetPosition);
        Piece sourcePiece = board.get(source);
        Piece targetPiece = board.get(target);
        validateCurrentTurn(thisTurn, sourcePiece);
        if (canMove(source, target, sourcePiece, targetPiece)) {
            board.move(sourcePiece, source, target);
            return;
        }
        throw new IllegalArgumentException("움직일 수 없습니다.");
    }

    private boolean canMove(Position sourcePosition, Position targetPosition, Piece sourcePiece, Piece targetPiece) {
        boolean isAttack = sourcePiece.isOtherTeam(targetPiece);
        return sourcePiece.isMovable(sourcePosition, targetPosition, isAttack) && !hasBlock(sourcePosition, targetPosition, sourcePiece, targetPiece);
    }

    public void move(Position source, Position target, Turn thisTurn) {
        Piece sourcePiece = board.get(source);
        Piece targetPiece = board.get(target);
        validateCurrentTurn(thisTurn, sourcePiece);

    }

    public boolean isKingDead() {
        return board.countKing() == 1;
    }

    public double getWhiteTeamScore() {
        return board.getTotalScore(Team.WHITE);
    }

    public double getBlackTeamScore() {
        return board.getTotalScore(Team.BLACK);
    }

    public Team getWinTeam() {
        if (getWhiteTeamScore() > getBlackTeamScore()) {
            return Team.WHITE;
        }
        if (getBlackTeamScore() > getWhiteTeamScore()) {
            return Team.BLACK;
        }
        return Team.NONE;
    }

    private boolean hasBlock(Position source, Position target, Piece sourcePiece, Piece targetPiece) {
        if (sourcePiece.isSameTeam(targetPiece)) {
            return true;
        }
        List<Position> positions = sourcePiece.getIntervalPosition(source, target);
        return positions.stream()
                .anyMatch(position -> !board.get(position).equals(new Empty()));
    }

    private void validateCurrentTurn(Turn thisTurn, Piece sourcePiece) {
        if (!sourcePiece.isCurrentTurn(thisTurn)) {
            throw new IllegalArgumentException("본인의 말을 움직여야 합니다.");
        }
    }

    public Board getBoard() {
        return board;
    }
}

