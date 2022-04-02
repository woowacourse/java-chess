package chess.controller;

import chess.domain.Command;
import chess.domain.board.PieceBuilder;
import chess.domain.board.PositionConvertor;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.state.command.Ready;
import chess.domain.state.command.State;
import chess.domain.Team;
import chess.domain.result.StatusResult;

import java.util.Map;

public class ChessController {

    private State state;
    private Board board;

    public ChessController() {
        state = new Ready();
    }

    public Map<Position, Piece> start() {
        state = state.execute(Command.START);
        board = new Board(new PieceBuilder());
        return board.getBoard();
    }

    public Team end() {
        state = state.execute(Command.END);
        Team winner = board.getFinalWinner();
        return winner;
    }

    public Map<Position, Piece> processMove(String rawSource, String rawTarget) {
        Position source = PositionConvertor.to(rawSource);
        Position target = PositionConvertor.to(rawTarget);
        board.move(source, target);
        state = state.execute(Command.MOVE);
        return board.getBoard();
    }

    public StatusResult processStatus() {
        StatusResult result = new StatusResult(
                board.calculateScore(Team.BLACK),
                board.calculateScore(Team.WHITE),
                board.getCurrentWinner()
        );

        state = state.execute(Command.STATUS);
        return result;
    }

    public boolean isFinish() {
        return state.isFinish();
    }
}
