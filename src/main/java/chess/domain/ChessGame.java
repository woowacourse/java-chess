package chess.domain;

import chess.domain.board.BoardBuilder;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.state.command.Finish;
import chess.domain.state.command.Playing;
import chess.dto.PieceDto;
import chess.domain.piece.Piece;
import chess.domain.state.command.Ready;
import chess.domain.state.command.State;

import chess.domain.result.StatusResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChessGame {

    private State state;
    private Board board;

    public ChessGame() {
        state = new Ready();
        board = new Board(new BoardBuilder());
    }

    public ChessGame(Board board) {
        state = new Playing();
        this.board = board;
    }

    public void start() {
        if (board.isFinish()) {
            state = state.execute(Command.END);
        }
        if (state.isFinish()) {
            board = new Board(new BoardBuilder());
            state = new Ready();
        }
        state = state.execute(Command.START);
    }

    public void end() {
        state = state.execute(Command.END);
    }

    public void processMove(String rawSource, String rawTarget) {
        state = state.execute(Command.MOVE);
        Position source = Position.of(rawSource);
        Position target = Position.of(rawTarget);
        board.move(source, target);
    }

    public StatusResult processStatus() {
        state = state.execute(Command.STATUS);
        StatusResult result = new StatusResult(
                board.calculateScore(Team.BLACK),
                board.calculateScore(Team.WHITE),
                board.getCurrentWinner()
        );
        return result;
    }

    public List<PieceDto> getCurrentImages() {
        Map<Position, Piece> pieceMap = board.getBoard();
        List<PieceDto> boardDtos = new ArrayList<>();
        for (Position position : Position.getReversePositions()) {
            boardDtos.add(new PieceDto(position.toString(), pieceMap.get(position).getName()));
        }
        return boardDtos;
    }

    public Map<Position, Piece> getBoard() {
        return board.getBoard();
    }

    public Team getCurrentTurnTeam() {
        if (state.isReady() || state.isFinish()) {
            return Team.NEUTRALITY;
        }

        return board.getCurrentTurnTeam();
    }

    public boolean isPlaying() {
        return !state.isReady() && !isFinish();
    }

    public boolean isFinish() { return state.isFinish() || isKingDeath(); }

    public boolean isKingDeath() {
        return board.isFinish();
    }

    public void load(Map<Position, Piece> pieces, chess.domain.state.turn.State state) {
        this.board = new Board(pieces, state);
        if (board.isFinish()) {
            this.state = new Finish();
            return;
        }
        this.state = new Playing();
    }

    public void move(Position source, Position target) {
        this.board.move(source, target);
    }

    public Team getCurrentWinner() {
        return board.getCurrentWinner().getTeam();
    }

    public chess.domain.state.turn.State getState() {
        return board.getState();
    }
}
