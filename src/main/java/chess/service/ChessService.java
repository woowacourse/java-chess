package chess.service;

import chess.domain.board.Board;
import chess.domain.piece.Team;
import chess.domain.position.Path;
import chess.utils.MoveInfo;

public class ChessService {
    public static void move(Board board, Team team, MoveInfo moveInfo) {
        String from = moveInfo.getFrom();
        String to = moveInfo.getTo();

        if (board.hasPieceIn(Path.of(from, to))) {
            throw new IllegalArgumentException("이동할 수 없는 경로입니다.");
        }
        board.move(from, to, team);
    }
}
