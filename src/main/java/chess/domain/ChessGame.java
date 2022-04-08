package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.BoardBuilder;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.result.StatusResult;
import chess.domain.state.turn.State;
import chess.dto.PieceDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChessGame {

    private Board board;

    public ChessGame() {
        board = new Board(new BoardBuilder());
    }

    public ChessGame(Board board) {
        this.board = board;
    }

    public void start() {
        if (board.isFinish()) {
            board = new Board(new BoardBuilder());
        }
    }

    public void end() {
        board.end();
    }

    public void processMove(String rawSource, String rawTarget) {
        Position source = Position.of(rawSource);
        Position target = Position.of(rawTarget);
        board.move(source, target);
    }

    public StatusResult processStatus() {
        StatusResult result = new StatusResult(
                board.calculateScore(Team.BLACK),
                board.calculateScore(Team.WHITE),
                board.getCurrentWinner()
        );
        return result;
    }

    public List<PieceDto> getBoardInformation() {
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
        return board.getCurrentTurnTeam();
    }

    public boolean isFinish() { return board.isFinish(); }

    public boolean isKingDeath() {
        return board.isKingDeath();
    }

    public void move(Position source, Position target) {
        this.board.move(source, target);
    }

    public Team getCurrentWinner() {
        return board.getCurrentWinner().getTeam();
    }

    public State getState() {
        return board.getState();
    }

    public boolean isPlaying() {
        return !board.isFinish();
    }
}
