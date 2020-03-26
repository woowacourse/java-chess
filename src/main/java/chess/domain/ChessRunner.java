package chess.domain;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Position;

public class ChessRunner {
    private Board board;
    private Team currentTeam;

    public ChessRunner() {
        this.board = new Board();
        this.currentTeam = Team.WHITE;
    }

    public void update(String source, String target) {
        Position sourcePosition = Position.of(source);
        Position targetPosition = Position.of(target);
        Piece selectedPiece = this.board.getPiece(sourcePosition);

        if ((currentTeam.isEnemy(selectedPiece.getTeam()))) {
            throw new IllegalArgumentException("현재 차례가 아닙니다.");
        }

        if (!(selectedPiece.move(sourcePosition, targetPosition, board))) {
            throw new IllegalArgumentException("이동할 수 없는 곳입니다.");
        }

        this.board.updateBoard(sourcePosition, targetPosition, selectedPiece);

        changeTurn();

    }

    private void changeTurn() {
        this.currentTeam = Team.changeTurn(this.currentTeam);
    }

    public Board getBoard() {
        return this.board;
    }
}
