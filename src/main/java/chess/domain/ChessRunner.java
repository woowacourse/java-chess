package chess.domain;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import chess.domain.position.Positions;
import chess.domain.score.Score;

import java.util.Optional;

public class ChessRunner {
    private Board board;
    private Team currentTeam;

    public ChessRunner() {
        this.board = new Board();
        this.currentTeam = Team.WHITE;
    }

    public void update(String source, String target) {
        Position sourcePosition = Positions.of(source);
        Position targetPosition = Positions.of(target);
        Piece selectedPiece = this.board.getPiece(sourcePosition);

        validateMovement(sourcePosition, targetPosition, selectedPiece);
        updateBoard(sourcePosition, targetPosition);
        changeTeam();
    }

    private void validateMovement(Position sourcePosition, Position targetPosition, Piece selectedPiece) {
        if (!(currentTeam.isSameTeamWith(selectedPiece.getTeam()))) {
            throw new IllegalArgumentException("현재 차례가 아닙니다.");
        }

        if (!(selectedPiece.movable(sourcePosition, targetPosition, board))) {
            throw new IllegalArgumentException("이동할 수 없는 곳입니다.");
        }
    }

    private void updateBoard(Position sourcePosition, Position targetPosition) {
        this.board.updateBoard(sourcePosition, targetPosition);
    }

    private void changeTeam() {
        this.currentTeam = currentTeam.changeTeam();
    }

    public Optional<Team> findWinner() {
        return this.board.checkWinner();
    }

    public double calculateScore() {
        return Score.calculateScore(board, currentTeam);
//        return board.calculateScore(currentTeam);
    }

    public Board getBoard() {
        return this.board;
    }

    public Team getCurrentTeam() {
        return currentTeam;
    }
}
