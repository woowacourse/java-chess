package chess.service;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Path;
import chess.domain.position.Position;
import chess.utils.MoveInfo;

public class ChessService {
    private final Board board;
    private Team team;

    private ChessService(Board board, Team team) {
        this.board = board;
        this.team = team;
    }

    public static ChessService of(Board board, Team team) {
        return new ChessService(board, team);
    }

    public void move(MoveInfo moveInfo) {
        Position from = moveInfo.getFrom();
        Position to = moveInfo.getTo();

        Piece piece = board.get(from);
        validateMove(from, to);

        piece.moveTo(to);
        board.update(from, to);
    }

    private void validateMove(Position from, Position to) {
        if (board.get(from).isNotSameTeam(team)) {
            throw new IllegalArgumentException("아군 기물의 위치가 아닙니다.");
        }
        if (board.hasPieceIn(Path.of(from, to)) || board.get(to).isSameTeam(team)) {
            throw new IllegalArgumentException("이동할 수 없는 경로입니다.");
        }
    }
}
