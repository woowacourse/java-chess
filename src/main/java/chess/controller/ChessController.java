package chess.controller;

import chess.domain.Command;
import chess.domain.board.PieceBuilder;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.dto.PieceDTO;
import chess.domain.piece.Piece;
import chess.domain.state.command.Ready;
import chess.domain.state.command.State;
import chess.domain.Team;
import chess.domain.result.StatusResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChessController {

    private State state;
    private Board board;

    public ChessController() {
        state = new Ready();
        board = new Board(new PieceBuilder());
    }

    public Map<Position, Piece> start() {
        state = state.execute(Command.START);
        return board.getBoard();
    }

    public Team end() {
        state = state.execute(Command.END);
        Team winner = board.getFinalWinner();
        return winner;
    }

    public Map<Position, Piece> processMove(String rawSource, String rawTarget) {
        state = state.execute(Command.MOVE);
        Position source = Position.of(rawSource);
        Position target = Position.of(rawTarget);
        board.move(source, target);
        return board.getBoard();
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

    public boolean isFinish() {
        return state.isFinish();
    }

    public List<PieceDTO> getCurrentImages() {
        Map<Position, Piece> pieceMap = board.getBoard();
        List<PieceDTO> boardDTOs = new ArrayList<>();
        for (Position position : Position.getReversePositions()) {
            boardDTOs.add(new PieceDTO(position.toString(), pieceMap.get(position).getName()));
        }
        return boardDTOs;
    }

    public Team getCurrentTeam() {
        if (state.isReady() || state.isFinish()) {
            return Team.NEUTRALITY;
        }
        return board.getCurrentTurnTeam();
    }

    public boolean isPlaying() {
        return !state.isReady() && !state.isFinish();
    }
}
