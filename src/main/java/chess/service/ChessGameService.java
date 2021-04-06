package chess.service;

import chess.domain.game.WebChessGame;
import chess.domain.result.BoardResult;
import chess.dto.BoardRequestDto;
import chess.dto.BoardResponseDto;
import chess.dto.PiecesDto;

public class ChessGameService {

    private WebChessGame webChessGame = new WebChessGame();

    public ChessGameService() {
    }

    public void initializeGame() {
        webChessGame = new WebChessGame();
    }

    public boolean checkMovement(final String source, final String target) {
        return webChessGame.isMovable(makeMoveCommand(source, target));
    }

    public BoardResponseDto move(final BoardRequestDto boardRequestDto) {
        try {
            final BoardResult boardResult = webChessGame.move(
                makeMoveCommand(boardRequestDto.getSource(), boardRequestDto.getTarget())
            );
            final PiecesDto piecesDto = PiecesDto.from(boardResult);
            return BoardResponseDto.createWithSuccess(piecesDto, webChessGame.isRunning());
        } catch (RuntimeException e) {
            return BoardResponseDto.createWithFailure(webChessGame.isRunning(), e.getMessage());
        }
    }

    private String makeMoveCommand(final String source, final String target) {
        return String.format("move %s %s", source, target);
    }
}
