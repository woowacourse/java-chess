package chess.controller;

import chess.dao.BoardDao;
import chess.dao.PieceDao;
import chess.domain.Command;
import chess.domain.board.BoardBuilder;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.state.command.Finish;
import chess.domain.state.command.Playing;
import chess.dto.PieceDto;
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
    private final BoardDao boardDao;
    private final PieceDao pieceDao;

    public ChessController() {
        state = new Ready();
        board = new Board(new BoardBuilder());
        boardDao = new BoardDao();
        pieceDao = new PieceDao();
    }

    public Map<Position, Piece> start() {
        if (board.isFinish()) {
            state = state.execute(Command.END);
        }
        if (state.isFinish()) {
            board = new Board(new BoardBuilder());
            state = new Ready();
        }
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

    public List<PieceDto> getCurrentImages() {
        Map<Position, Piece> pieceMap = board.getBoard();
        List<PieceDto> boardDtos = new ArrayList<>();
        for (Position position : Position.getReversePositions()) {
            boardDtos.add(new PieceDto(position.toString(), pieceMap.get(position).getName()));
        }
        return boardDtos;
    }

    public Team getCurrentTeam() {
        if (state.isReady() || state.isFinish()) {
            return Team.NEUTRALITY;
        }
        return board.getCurrentTurnTeam();
    }

    public boolean isPlaying() {
        return !state.isReady() && !isFinish();
    }

    public boolean isFinish() { return board.isFinish(); }

    public void load() {
        board = new Board(pieceDao.load(), boardDao.loadState());
        if (board.isFinish()) {
            state = new Finish();
        }
        state = new Playing();
    }

    public void save() {
        boardDao.removeAll();
        boardDao.saveState(board.getState());
        pieceDao.removeAll();
        pieceDao.save(board.getBoard());
    }
}
