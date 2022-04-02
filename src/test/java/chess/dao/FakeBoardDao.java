package chess.dao;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Position;
import chess.domain.piece.Blank;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.dto.ChessDto;
import java.util.Map;

public class FakeBoardDao implements BoardDao {

    public Map<String, String> fakeBoard;

    public FakeBoardDao() {
        Map<Position, Piece> board = getPositionPieceMap();
        fakeBoard = ChessDto.of(new Board(board)).getBoard();
    }

    public static Map<Position, Piece> getPositionPieceMap() {
        Map<Position, Piece> board = BoardFactory.initialize();
        board.put(Position.valueOf("a2"), new Blank());
        board.put(Position.valueOf("a4"), new Pawn(Team.WHITE));
        return board;
    }

    @Override
    public Map<String, String> getBoard() {
        return fakeBoard;
    }

    @Override
    public void updatePosition(final String position, final String piece) {
        fakeBoard.put(position, piece);
    }

    @Override
    public void updateBatchPositions(final Map<String, String> board) {
        fakeBoard = board;
    }
}
