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

    public boolean isKingDead() {
        return board.countKing() == 1;
    }

    public GameResult getWinningResult() {
        return GameResult.from(board);
    }

    public void move(Position source, Position target, Turn thisTurn) {
        Piece sourcePiece = board.get(source);
        Piece targetPiece = board.get(target);
        validateCurrentTurn(thisTurn, sourcePiece);
        MoveType moveType = MoveType.of(sourcePiece, targetPiece);
        if (canMove(source, target, sourcePiece, moveType)) {
            board.move(sourcePiece, source, target);
            return;
        }
        throw new IllegalArgumentException("움직일 수 없습니다.");
    }

    private boolean canMove(Position sourcePosition, Position targetPosition, Piece sourcePiece, MoveType moveType) {
        return sourcePiece.isMovable(sourcePosition, targetPosition, moveType)
                && !hasBlock(sourcePosition, targetPosition, sourcePiece);
    }

    private boolean hasBlock(Position source, Position target, Piece sourcePiece) {
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
