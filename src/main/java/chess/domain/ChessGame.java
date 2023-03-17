package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Team;
import chess.domain.piece.Piece;

import java.util.List;

public class ChessGame {

    private final Board board;
    private Team team;

    public ChessGame(Board board) {
        this.board = board;
        this.team = Team.WHITE;
    }

    public void movePiece(Position sourcePosition, Position targetPosition) {
        validateMove(sourcePosition, targetPosition);
        move(sourcePosition, targetPosition);
    }

    private void validateMove(Position sourcePosition, Position targetPosition) {
        Piece sourcePiece = board.findPiece(sourcePosition);
        Piece targetPiece = board.findPiece(targetPosition);
        sourcePiece.validateTeam(team);
        sourcePiece.validateCanMove(sourcePosition, targetPosition, targetPiece.getTeam());
        List<Position> path = sourcePosition.findPath(targetPosition);
        board.validatePosition(path);
    }

    private void move(Position sourcePosition, Position targetPosition) {
        board.movePiece(sourcePosition, targetPosition);
        this.team = team.getReverseTeam();
    }

    public Board getBoard() {
        return board;
    }
}
