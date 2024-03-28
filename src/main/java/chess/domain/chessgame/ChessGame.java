package chess.domain.chessgame;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.pieceInfo.Position;
import chess.domain.pieceInfo.Team;

import java.util.Map;

public class ChessGame {
    private final Board board;
    private Team turn;

    public ChessGame() {
        this.board = new Board();
    }

    public void start() {
        if (notStarted()) {
            turn = Team.WHITE;
            return;
        }
        throw new IllegalArgumentException("start는 한 번만 입력될 수 있습니다.");
    }

    public void move(final Position source, final Position target) {
        validateMove(source, target);
        if (checkTurn(source, turn)) {
            boolean isMoved = board.movePieceAndRenewBoard(source, target);
            turn = turnChange(isMoved);
            return;
        }
        throw new IllegalArgumentException(String.format("%s의 차례입니다.", turn.name()));
    }

    public boolean isWin() {
        return board.isKingRemoved();
    }

    public ScoreBoard status() {
        validateStatus();
        return ScoreBoard.of(board);
    }

    public Team findWinner(ScoreBoard scoreBoard) {
        double whiteScore = scoreBoard.getWhiteScore();
        double blackScore = scoreBoard.getBlackScore();
        if (whiteScore > blackScore) {
            return Team.WHITE;
        }
        if (whiteScore < blackScore) {
            return Team.BLACK;
        }
        return Team.NONE;
    }

    private void validateMove(final Position source, final Position target) {
        if (notStarted()) {
            throw new IllegalArgumentException("start가 입력되기 전에 move를 수행할 수 없습니다.");
        }
        if (source.equals(target)) {
            throw new IllegalArgumentException("source좌표와 target좌표가 같을 수 없습니다.");
        }
    }

    private boolean notStarted() {
        return turn != Team.WHITE && turn != Team.BLACK;
    }

    private boolean checkTurn(final Position source, final Team turn) {
        return board.isSameTeamFromPosition(source, turn);
    }

    private Team turnChange(final boolean isMoved) {
        if (!isMoved) {
            throw new IllegalArgumentException("입력한 움직임을 수행할 수 없습니다. 다시 시도해 주십시오.");
        }
        if (turn == Team.WHITE) {
            return Team.BLACK;
        }
        return Team.WHITE;
    }

    private void validateStatus() {
        if (notStarted()) {
            throw new IllegalArgumentException("start가 입력되기 전에 status를 수행할 수 없습니다.");
        }
    }

    public Map<Position, Piece> getBoard() {
        return board.getBoard();
    }

    public Team getTurn() {
        return turn;
    }
}
