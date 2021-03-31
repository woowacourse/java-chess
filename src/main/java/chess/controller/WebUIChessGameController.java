package chess.controller;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import chess.domain.game.ChessGame;
import chess.dto.BoardDto;
import chess.dto.MovableRequestDto;
import chess.dto.MovableResponseDto;
import chess.dto.MoveRequestDto;
import chess.utils.DtoAssembler;
import java.util.List;

public class WebUIChessGameController {

    private final ChessGame chessGame;

    public WebUIChessGameController() {
        this.chessGame = new ChessGame(new Board());
        this.chessGame.start();
    }

    public BoardDto board() {
        return DtoAssembler.board(this.chessGame.board());
    }

    public MovableResponseDto movablePath(final MovableRequestDto movableRequestDto) {
        List<Position> positions = this.chessGame.movablePath(movableRequestDto.position());
        return DtoAssembler.movableResponse(positions);
    }

    public BoardDto move(final MoveRequestDto moveRequestDto) {
        Position source = Position.of(moveRequestDto.getSource());
        Position target = Position.of(moveRequestDto.getTarget());
        this.chessGame.move(source, target);
        return DtoAssembler.board(this.chessGame.board());
    }
}
