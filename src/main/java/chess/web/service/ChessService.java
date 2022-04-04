package chess.web.service;

import chess.board.Board;
import chess.board.Team;
import chess.board.Turn;
import chess.web.dao.BoardDao;
import chess.web.dao.BoardDaoImpl;
import chess.web.dao.PieceDao;
import chess.web.dao.PieceDaoImpl;
import chess.board.piece.Piece;
import chess.board.piece.Pieces;
import chess.board.piece.position.Position;
import chess.web.service.dto.MoveDto;
import chess.web.service.dto.ScoreDto;

import java.util.List;

public class ChessService {

    private final BoardDao boardDao;
    private final PieceDao pieceDao;

    public ChessService() {
        boardDao = new BoardDaoImpl();
        pieceDao = new PieceDaoImpl();
    }

    public Board loadGame() {
        Turn turn = boardDao.findTurnById(1L).orElseThrow(() -> new IllegalArgumentException("없는 차례입니다."));
        List<Piece> pieces = pieceDao.findAllByBoardId(1L);
        return Board.create(Pieces.from(pieces), turn);
    }

    public Board move(final MoveDto moveDto, final Long boardId) {
        Turn turn = boardDao.findTurnById(boardId).orElseThrow(() -> new IllegalArgumentException("없는 정보입니다."));

        Board board = Board.create(Pieces.from(pieceDao.findAllByBoardId(boardId)), turn);
        Pieces pieces = board.getPieces();

        Piece piece = pieces.findByPosition(Position.from(moveDto.getFrom()));
        board.move(List.of(moveDto.getFrom(), moveDto.getTo()), turn);
        Turn changedTurn = updatePieces(moveDto, turn, piece,boardId);

        return Board.create(pieces, changedTurn);
    }

    private Turn updatePieces(MoveDto moveDto, Turn turn, Piece piece, final Long boardId) {
        Turn changedTurn = changeTurn(turn);
        pieceDao.updatePieceByPositionAndBoardId("empty", "none", moveDto.getFrom(), boardId);
        pieceDao.updatePieceByPositionAndBoardId(piece.getType(), piece.getTeam().value(), moveDto.getTo(), boardId);
        return changedTurn;
    }

    private Turn changeTurn(Turn turn) {
        Turn change = turn.change();
        boardDao.updateTurnById(1L, change.getTeam().value());
        return change;
    }

    public Board initBoard(Long boardId) {
        Pieces pieces = Pieces.createInit();
        Board board = Board.create(pieces, Turn.init());
        pieceDao.save(pieces.getPieces(), boardId);
        return board;
    }

    public ScoreDto getStatus() {
        List<Piece> pieces = pieceDao.findAllByBoardId(1L);
        Pieces board = Pieces.from(pieces);

        double blackScore = board.getTotalScore(Team.BLACK);
        double whiteScore = board.getTotalScore(Team.WHITE);
        return new ScoreDto(blackScore, whiteScore);
    }
}
