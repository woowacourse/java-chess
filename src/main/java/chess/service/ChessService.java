package chess.service;

import chess.Board;
import chess.Turn;
import chess.dao.BoardDao;
import chess.dao.BoardDaoImpl;
import chess.dao.PieceDao;
import chess.dao.PieceDaoImpl;
import chess.piece.Piece;
import chess.piece.Pieces;
import chess.piece.position.Position;
import chess.service.dto.MoveDto;

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
        Pieces p = Pieces.from(pieceDao.findAllByBoardId(1L));

        Board board = Board.create(p, turn);
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

    public Board initBoard() {
        Board board = Board.create(Pieces.createInit(), Turn.init());
        List<Piece> pieces = board.getPieces().getPieces();
        boardDao.updateTurnById(1L, "white");
        for (Piece piece : pieces) {
            pieceDao.updatePieceByPosition(piece.getType(), piece.getTeam().value(), piece.getPosition().name());
        }
        return board;
    }
}
