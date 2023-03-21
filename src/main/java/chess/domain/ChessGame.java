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
        board.movePiece(sourcePosition, targetPosition, nowPlayingTeam);
        this.nowPlayingTeam = nowPlayingTeam.getReverseTeam();
    }

    public Board getBoard() {
        return board;
    }
}
