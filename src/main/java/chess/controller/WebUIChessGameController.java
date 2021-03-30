package chess.controller;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import chess.domain.game.ChessGame;
import chess.dto.MovableRequestDto;
import chess.dto.MovableResponseDto;
import chess.utils.DtoAssembler;
import java.util.List;

public class WebUIChessGameController {

    private final ChessGame chessGame;

    public WebUIChessGameController() {
        this.chessGame = new ChessGame(new Board());
        this.chessGame.start();
    }

    public MovableResponseDto movablePath(final MovableRequestDto movableRequestDto) {
        List<Position> positions = this.chessGame.movablePath(movableRequestDto.position());
        return DtoAssembler.movableResponse(positions);

    }
}
