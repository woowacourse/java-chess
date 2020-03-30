package chess.service;

import chess.domain.board.Board;
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

    public static ChessService of(Board board) {
        return new ChessService(board, Team.WHITE);
    }

    public void move(MoveInfo moveInfo) {
        Position from = moveInfo.getFrom();
        Position to = moveInfo.getTo();

        if (board.hasPieceIn(Path.of(from, to))) {
            throw new IllegalArgumentException("이동할 수 없는 경로입니다.");
        }
        board.move(from, to, team);
    }
}
