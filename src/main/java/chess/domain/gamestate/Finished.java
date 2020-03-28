package chess.domain.gamestate;

import chess.domain.Pieces;
import chess.domain.Team;
import chess.domain.board.Board;

public class Finished extends Started {
    private double whiteTeamScore;
    private double blackTeamScore;

    public Finished(Board board) {
        this.board = board;

        Pieces blackPieces = board.findPiecesOf(Team.BLACK);
        Pieces whitePieces = board.findPiecesOf(Team.WHITE);

        this.whiteTeamScore = whitePieces.sumScore();
        this.blackTeamScore = blackPieces.sumScore();
    }

    @Override
    public GameState move(String fromPosition, String toPosition) {
        throw new UnsupportedOperationException("게임이 종료되었으므로 기물을 움직일 수 없습니다.");

    }

    @Override
    public GameState finish() {
        throw new UnsupportedOperationException("게임이 이미 종료되었습니다.");
    }

    @Override
    public double getWhiteTeamScore() {
        return whiteTeamScore;
    }

    @Override
    public double getBlackTeamScore() {
        return blackTeamScore;
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
