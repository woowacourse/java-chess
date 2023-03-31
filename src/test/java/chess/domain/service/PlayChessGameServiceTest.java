package chess.domain.service;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.PlayChessGameService;
import chess.domain.game.state.ChessGame;
import chess.domain.game.state.ReadyGame;
import chess.domain.game.state.RunGame;
import chess.domain.game.state.StartedGame;
import chess.domain.piece.Camp;
import chess.domain.position.ChessBoard;
import chess.domain.position.ChessBoardFactory;
import chess.domain.position.Position;
import chess.domain.repository.BoardDao;
import chess.domain.repository.PieceDao;
import chess.domain.repository.entity.PieceEntity;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayChessGameServiceTest {

    private BoardDao memoryBoardDao;
    private PieceDao memoryPieceDao;

    @BeforeEach
    void setMemoryDao() {
        memoryBoardDao = new MemoryBoardDao();
        memoryPieceDao = new MemoryPieceDao();
    }

    @Test
    @DisplayName("게임 시작 시, 이전 게임은 지워지고 새로운 게임을 생성한다.")
    void startTest() {
        PlayChessGameService playChessGameService = new PlayChessGameService(memoryBoardDao, memoryPieceDao);
        ChessGame startedGame = playChessGameService.start(new ReadyGame());

        assertThat(startedGame).isInstanceOf(StartedGame.class);
    }

    @Test
    @DisplayName("기물을 움직일 수 있다.")
    void moveTest() {
        PlayChessGameService playChessGameService = new PlayChessGameService(memoryBoardDao, memoryPieceDao);

        PieceEntity pieceEntity = new PieceEntity("a2", "PAWN", "WHITE", 1L);
        ChessBoard chessBoard = ChessBoardFactory.getInitialChessBoard();

        memoryPieceDao.savePieces(List.of(pieceEntity));

        playChessGameService.move(new RunGame(chessBoard, Camp.WHITE), Position.valueOf("a2"),
                Position.valueOf("a4"));

        assertThat(memoryPieceDao.isExistByPosition("a2")).isFalse();
        assertThat(memoryPieceDao.isExistByPosition("a4")).isTrue();
    }
}