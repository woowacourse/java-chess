package chess.service;

import chess.controller.dto.MovablePathRequestDto;
import chess.controller.dto.MoveRequestDto;
import chess.controller.dto.PathResponseDto;
import chess.controller.dto.StatusResponseDto;
import chess.domain.board.position.Path;
import chess.domain.board.position.Position;
import chess.domain.manager.ChessManager;

public class ChessService {

    private final ChessManager chessManager;

    public ChessService() {
        this.chessManager = new ChessManager();
    }

    public void move(MoveRequestDto moveRequestDto) {
        chessManager.move(Position.of(moveRequestDto.getSource()), Position.of(moveRequestDto.getTarget()));
    }

    public PathResponseDto movablePath(MovablePathRequestDto movablePathRequestDto) {
        Path path = chessManager.movablePath(Position.of(movablePathRequestDto.getSource()));
        return PathResponseDto.toPath(path);
    }

    public StatusResponseDto gameStatus() {
        return StatusResponseDto.toGameStatus(chessManager.gameStatus());
    }
}
