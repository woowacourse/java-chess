package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.command.Command;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import java.util.Map;

import chess.domain.ChessScore;
import chess.domain.position.Position;

public class Ready extends State {

    private static final String CANNOT_MOVE = "게임 시작 전에는 움직일 수 없습니다.";
    private static final String NOT_STATUS = "게임 시작 전에는 점수를 확인할 수 없습니다.";

    protected Ready(Map<Position, Piece> board) {
        this.board = new Board(board);
    }

    @Override
    public State proceed(Command command) {
        if (command.isMove()) {
            throw new IllegalArgumentException(CANNOT_MOVE);
        }
        if (command.isStart()) {
            return new Running(board.getPieces(), Color.WHITE);
        }
        if (command.isStatus()) {
            throw new IllegalArgumentException(NOT_STATUS);
        }
        return new Finished(board.getPieces());
    }
}
