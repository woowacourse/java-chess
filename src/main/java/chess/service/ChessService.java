package chess.service;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.game.ChessGame;
import chess.dto.BoardDto;

public class ChessService {

    public BoardDto initializeBoard() {
        ChessGame chessGame = new ChessGame(new Board(BoardInitializer.initialize()));
        return BoardDto.of(new Board(BoardInitializer.initialize()));
    }
}
