package chess.web.service;

import chess.board.Board;
import chess.board.piece.Piece;
import chess.board.piece.Pieces;
import chess.board.piece.position.Position;
import chess.web.dao.BoardDao;
import chess.web.dao.BoardDaoImpl;
import chess.web.dao.PieceDao;
import chess.web.dao.PieceDaoImpl;
import chess.web.service.dto.MoveDto;
import chess.web.service.dto.ScoreDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ChessServiceTest {

    private final BoardDao boardDao = new BoardDaoImpl();
    private final PieceDao pieceDao = new PieceDaoImpl();
    private final ChessService chessService = new ChessService();
    private Long boardId;

    @BeforeEach
    void setUp() {
        boardId = boardDao.save();
        Pieces pieces = Pieces.createInit();
        pieceDao.save(pieces.getPieces(), boardId);
    }

    @Test
    @DisplayName("64개의 piece을 갖고 있는 게임을 불러왔을 떄, 그떄의 piece 개수도 64개여야한다.")
    void loadGame() {
        Board board = chessService.loadGame(boardId);
        assertThat(board.getPieces().getPieces().size()).isEqualTo(64);
    }

    @Test
    @DisplayName("초기 보드판에서 a2->a3로 이동하면 처음 a2에 있던 piece는 이동 후, a3에 있는 piece와 같다.")
    void move() {
        Board board = boardDao.findById(boardId).get();
        String from = "a2";
        String to = "a3";
        Piece piece = board.getPieces().findByPosition(Position.from(from));
        MoveDto moveDto = new MoveDto(from, to);
        Board movedBoard = chessService.move(moveDto, boardId);
        Piece movedPiece = movedBoard.getPieces().findByPosition(Position.from(to));
        assertAll(
                () -> assertThat(piece.getType()).isEqualTo(movedPiece.getType()),
                () -> assertThat(piece.getName()).isEqualTo(movedPiece.getName()),
                () -> assertThat(piece.getTeam()).isEqualTo(movedPiece.getTeam())
        );

    }

    @Test
    @DisplayName("piece들이 없는 board를 만든 후, initBoard호출하면 piece들까지 초기화된다.")
    void initBoard() {
        Long saveId = boardDao.save();

        Board initBoard = chessService.initBoard(saveId);
        Pieces pieces = initBoard.getPieces();
        assertThat(pieces.getPieces().size()).isEqualTo(64);
    }

    @Test
    @DisplayName("초기 board들의 점수는 black, white팀 모두 38이다.")
    void getStatus() {
        ScoreDto status = chessService.getStatus(boardId);
        assertThat(status.getBlackTeam()).isEqualTo(38D);
        assertThat(status.getWhiteTeam()).isEqualTo(38D);
    }
}
