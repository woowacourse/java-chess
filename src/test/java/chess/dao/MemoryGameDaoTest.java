package chess.dao;

import static org.assertj.core.api.Assertions.*;

import chess.domain.ChessGame;
import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.state.WhiteTurn;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemoryGameDaoTest {

    @Test
    @DisplayName("체스 게임을 저장소에 저장한다.")
    void save() {
        MemoryGameDao repository = new MemoryGameDao();
        ChessGame game = new ChessGame(new WhiteTurn(new Board(BoardInitializer.initBoard())));
        repository.save(game);
        assertThat(repository.findById(1L).get().isBlackTurn()).isEqualTo(game.isBlackTurn());
    }

    @Test
    @DisplayName("저장소에 저장된 모든 게임을 불러온다.")
    void findAll() {
        MemoryGameDao repository = new MemoryGameDao();
        List<ChessGame> games = new ArrayList<>();
        games.add(new ChessGame(new WhiteTurn(new Board(BoardInitializer.initBoard()))));
        games.add(new ChessGame(new WhiteTurn(new Board(BoardInitializer.initBoard()))));
        games.add(new ChessGame(new WhiteTurn(new Board(BoardInitializer.initBoard()))));
        for (ChessGame game : games) {
            repository.save(game);
        }
        assertThat(repository.findAll().size()).isEqualTo(games.size());
    }
}