package chess.service;

import static chess.service.BoardDaoTestImpl.BOARD_ID;
import static org.assertj.core.api.Assertions.assertThat;

import chess.dao.BoardDao;
import chess.dao.PieceDao;
import chess.domain.game.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.dto.MoveRequest;
import java.util.Map;
import java.util.Map.Entry;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ChessServiceTest {

    private static final String MESSAGE_KEY = "message";

    private static ChessService chessService;
    private final ChessGame chessGame = new ChessGame();

    @BeforeAll
    static void init() {
        PieceDao pieceDao = new PieceDaoTestImpl();
        BoardDao boardDao = new BoardDaoTestImpl();
        chessService = new ChessService(pieceDao, boardDao);
    }

    @Test
    void createNewBoard() {
        // when
        int id = chessService.createNewBoard(chessGame);

        // then
        assertThat(id).isEqualTo(BOARD_ID);
    }

    @Test
    void move() {
        // given
        chessService.createNewBoard(chessGame);

        String before = "a2";
        String after = "a3";

        Piece piece = findByPosition(before);

        // when
        chessService.move(chessGame, BOARD_ID, new MoveRequest(before, after));

        // then
        assertThat(piece).isEqualTo(findByPosition(after));
    }

    @Test
    void calculateScore() {
        // given
        chessService.createNewBoard(chessGame);

        // when
        Map<String, Object> result = chessService.calculateScore(chessGame);
        String sentence = (String) result.get(MESSAGE_KEY);

        // then
        assertThat(sentence.contains("무승부")).isTrue();
    }

    private Piece findByPosition(String position) {
        return chessGame.getBoard()
                .getValue()
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().equals(Position.of(position)))
                .map(Entry::getValue)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 위치에는 Piece가 없습니다."));
    }
}