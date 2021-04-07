package chess.service;

import chess.controller.dto.*;
import chess.domain.board.position.Path;
import chess.domain.board.position.Position;
import chess.domain.manager.ChessManager;

import java.util.List;
import java.util.stream.Collectors;

public class ChessService {

    private ChessManager chessManager;

    public ChessService() {
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

    public void newGame() {
        chessManager = new ChessManager();
    }

    public List<PieceResponseDto> boardInfo() {
        return chessManager.getBoard().getBoard().entrySet()
                .stream()
                .map(entry -> new PieceResponseDto(entry.getValue().getSymbol(),
                        entry.getKey().getHorizontal().name().toLowerCase() + entry.getKey().getVerticalIndex()))
                .collect(Collectors.toList());
    }

    public StateResponseDto gameState() {
        return StateResponseDto.toChessManager(chessManager);
    }
}
