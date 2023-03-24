package chess.domain;

import java.util.Map;

import chess.domain.dto.GameStatusDto;
import chess.domain.piece.Piece;
import chess.domain.piece.Point;
import chess.domain.piece.Team;
import chess.domain.square.Square;

public class ChessGame {

    private final Board board;
    private Team turn;

    public ChessGame() {
        this.board = Board.create();
        turn = Team.WHITE;
    }

    public ChessGame(Board board) {
        this.board = board;
        turn = Team.WHITE;
    }

    public void move(final Square current, final Square destination) {
        checkTurn(current);
        board.move(current, destination);
        turn = turn.getEnemy();
    }

    private void checkTurn(final Square square) {
        if (board.isPieceTurn(square, turn)) {
            return;
        }
        throw new IllegalArgumentException("상대팀 말을 움직일 수 없습니다.");
    }

    public boolean isKingDead() {
        return !board.hasKing(Team.WHITE) || !board.hasKing(Team.BLACK);
    }

    public Team getWinner() {
        if (board.hasKing(Team.WHITE) && board.hasKing(Team.BLACK)) {
            throw new IllegalStateException("아직 게임이 끝나지 않았습니다.");
        }
        if (board.hasKing(Team.WHITE)) {
            return Team.WHITE;
        }
        if (board.hasKing(Team.BLACK)) {
            return Team.BLACK;
        }
        throw new IllegalStateException("두 킹이 모두 죽어있을 순 없습니다.");
    }

    public double getPoint(Team team) {
        return Point.calculatePointByTeam(team, board.getBoard());
    }

    public GameStatusDto getGameStatus() {
        return GameStatusDto.from(board);
    }

    public Map<Square, Piece> getBoard() {
        return board.getBoard();
    }
}
