package chess.dao;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.ChessBoard;
import chess.domain.ChessBoardFactory;
import chess.domain.ChessGame;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class ChessDaoTest {

    private ChessDao chessDao;

    @BeforeEach
    void setUp() {
        chessDao = new ChessDao();
    }

    @Test
    void should_저장된체스게임을불러온다_when_게임이한턴이상진행됐을시() {
        //given
        ChessBoard chessBoard = ChessBoardFactory.create();
        chessBoard.move(Position.from("c2"), Position.from("c4"));
        chessBoard.move(Position.from("a7"), Position.from("a5"));
        chessDao.save(chessBoard);

        //when
        ChessGame chessGame = chessDao.load();
        chessGame.initialize();
        final Executable executable = () -> chessGame.executeMove(Position.from("c4"), Position.from("c5"));

        //then
        assertDoesNotThrow(executable);
    }

}