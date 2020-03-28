package chess.domain.gamestatus;

import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.piece.PieceType;

public class Running extends Started {

    Team currentTeam;

    Running() {
        super();
    }

    private Running(Board board, Team thisTurn) {
        super(board);
        this.currentTeam = thisTurn;
    }

    @Override
    public GameStatus start() {
        throw new UnsupportedOperationException("게임이 이미 시작되었습니다.");
    }

    @Override
    public GameStatus move(String fromPosition, String toPosition) {

        board.move(fromPosition, toPosition);
        return decideNextStatus();
    }

    private GameStatus decideNextStatus() {
        Team nextTeam = currentTeam.opponent();

        if (board.isPieceOnBoard(nextTeam, PieceType.KING)) {
            return new Running(this.board, nextTeam);
        }
        return new Finished();
    }

    @Override
    public String getBoardString() {
        return board.toString();
    }
}
