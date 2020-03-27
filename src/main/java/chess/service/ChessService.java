package chess.service;

import chess.domain.MoveInfo;
import chess.domain.Turn;
import chess.domain.board.Boards;
import chess.domain.position.Path;

public class ChessService {
    public static void move(Boards boards, Turn turn, MoveInfo moveInfo) {
        String from = turn.key(moveInfo.getFrom());
        String to = turn.key(moveInfo.getTo());

        if (boards.hasPieceIn(Path.of(from, to))) {
            throw new IllegalArgumentException("이동할 수 없는 경로입니다.");
        }
        boards.move(from, to, turn);
    }

    public static double getScore(Boards boards, Turn turn) {
        return boards.getScoreOf(turn);
    }
}
