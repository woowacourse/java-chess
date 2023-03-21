package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Team;

import java.util.List;

public class ChessGame {

    private final Board board;
    private Team nowPlayingTeam;

    public ChessGame(Board board) {
        this.board = board;
        this.nowPlayingTeam = Team.WHITE;
    }

    public void movePiece(Position sourcePosition, Position targetPosition) {
        validateMove(sourcePosition, targetPosition);
        move(sourcePosition, targetPosition);
    }

    private void validateMove(Position sourcePosition, Position targetPosition) {
        board.validateSourceTeam(sourcePosition, nowPlayingTeam);
        board.validateCanMove(sourcePosition, targetPosition);
        List<Position> path = sourcePosition.findPath(targetPosition);
        board.validatePath(path);
    }

    private void move(Position sourcePosition, Position targetPosition) {
        board.movePiece(sourcePosition, targetPosition);
        this.nowPlayingTeam = nowPlayingTeam.getReverseTeam();
    }

    public Board getBoard() {
        return board;
    }
}
