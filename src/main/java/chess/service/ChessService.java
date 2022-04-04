package chess.service;

import chess.Board;
import chess.Team;
import chess.Turn;
import chess.dao.BoardDao;
import chess.dao.BoardDaoImpl;
import chess.dao.PieceDao;
import chess.dao.PieceDaoImpl;
import chess.piece.Piece;
import chess.piece.Pieces;
import chess.piece.position.Position;
import chess.service.dto.MoveDto;
import chess.service.dto.ScoreDto;

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

    public Board move(MoveDto moveDto) {
        Turn turn = boardDao.findTurnById(1L).orElseThrow(() -> new IllegalArgumentException("없는 정보입니다."));

        Board board = Board.create(Pieces.from(pieceDao.findAllByBoardId(1L)), turn);
        Pieces pieces = board.getPieces();

        Piece piece = pieces.findByPosition(Position.from(moveDto.getFrom()));
        board.move(List.of(moveDto.getFrom(), moveDto.getTo()), turn);
        Turn changedTurn = updatePieces(moveDto, turn, piece);

        return Board.create(pieces, changedTurn);
    }

    private Turn updatePieces(MoveDto moveDto, Turn turn, Piece piece) {
        Turn changedTurn = changeTurn(turn);
        pieceDao.updatePieceByPosition("empty", "none", moveDto.getFrom());
        pieceDao.updatePieceByPosition(piece.getType(), piece.getTeam().value(), moveDto.getTo());
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
