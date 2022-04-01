package chess.service;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.game.ChessGame;
import chess.dto.BoardDto;

public class ChessService {

    public BoardDto initializeGame() {
        // dao에서 현재 보드를 가져와야함
        ChessGame chessGame = new ChessGame(new Board(BoardInitializer.initialize()));
        return BoardDto.of(new Board(BoardInitializer.initialize()));
    }

    public BoardDto endGame() {
        // dao에서 기존 보드 상태를 모두 최초의 상태로 업데이트
        return BoardDto.of(new Board(BoardInitializer.initialize()));
    }
}
