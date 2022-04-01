package chess.service;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.game.ChessGame;
import chess.domain.game.Score;
import chess.dto.BoardDto;
import chess.dto.StatusDto;

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

    public StatusDto createStatus() {
        // dao에서 현재 보드 가져옴
        Score score = new Score(new Board(BoardInitializer.initialize()).getBoard());
        return StatusDto.of(score);
    }
}
