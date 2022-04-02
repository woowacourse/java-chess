package chess.domain.game;

import console.controller.Request;
import chess.domain.board.Board;
import chess.domain.board.Point;
import chess.domain.piece.Color;
import chess.dto.BoardAndTurnInfo;
import chess.dto.Response;

import java.util.List;

public class Running extends Started {

    Running(Board board, Color turnColor) {
        super(board, turnColor);
    }

    @Override
    public GameState start() {
        throw new UnsupportedOperationException("[ERROR] 이미 게임이 시작되었습니다.");
    }

    @Override
    public GameState finish() {
        return new Finished(board, turnColor);
    }

    @Override
    public GameState move(List<Point> arguments) {
        Request.validateArgumentSize(arguments);
        board.move(arguments.get(0), arguments.get(1), turnColor);
        if (!board.isKingDead(turnColor.toggle())) {
            return new Running(board, turnColor.toggle());
        }
        return new Finished(board, turnColor);
    }

    @Override
    public GameState status() {
        return new Status(board, turnColor);
    }

    @Override
    public boolean isRunnable() {
        return true;
    }

    @Override
    public Response getResponse() {
        return new BoardAndTurnInfo(board.getPointPieces(), turnColor);
    }
}
