package chess.domain;

import chess.domain.piece.Piece;
import chess.view.OutputView;

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

    public void move(Position source, Position target) {
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

    public void status() {
        validateStatus();
        double whiteScore = Score.calculateScore(board, Team.WHITE);
        double blackScore = Score.calculateScore(board, Team.BLACK);
        findWinner(whiteScore, blackScore);
    }

    private void validateMove(Position source, Position target) {
        if (notStarted()) {
            throw new IllegalArgumentException("start가 입력되지 전에 move를 수행할 수 없습니다.");
        }
        if (source.equals(target)) {
            throw new IllegalArgumentException("source좌표와 target좌표가 같을 수 없습니다.");
        }
    }

    private boolean notStarted() {
        return turn != Team.WHITE && turn != Team.BLACK;
    }

    private boolean checkTurn(Position source, Team turn) {
        return board.isSameTeamFromPosition(source, turn);
    }

    private Team turnChange(boolean isMoved) {
        if (!isMoved) {
            throw new IllegalArgumentException("입력한 움직임을 수행할 수 없습니다. 다시 시도해 주십시오.");
        }
        if (turn == Team.WHITE) {
            return Team.BLACK;
        }
        return Team.WHITE;
    }

    private static void findWinner(double whiteScore, double blackScore) {
        if (whiteScore > blackScore) {
            OutputView.printScoreWithWinner(whiteScore, blackScore, Team.WHITE);
        }
        if (whiteScore < blackScore) {
            OutputView.printScoreWithWinner(whiteScore, blackScore, Team.BLACK);
        }
        if (whiteScore == blackScore) {
            OutputView.printScoreWithDraw(whiteScore, blackScore);
        }
    }

    private void validateStatus() {
        if (notStarted()) {
            throw new IllegalArgumentException("start가 입력되지 전에 status를 수행할 수 없습니다.");
        }
    }

    public Map<Position, Piece> getBoard() {
        return board.getBoard();
    }

    public Team getTurn() {
        return turn;
    }
}
