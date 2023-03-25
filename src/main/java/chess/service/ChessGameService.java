package chess.service;

import chess.dao.ChessGameDao;
import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.board.BoardResult;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.dto.MoveDto;
import java.util.List;
import java.util.Map;

public class ChessGameService {

    private Board board;
    private final ChessGameDao chessGameDao;

    public ChessGameService() {
        this.board = BoardInitializer.initialize();
        this.chessGameDao = new ChessGameDao();
    }

    public void load() {
        final List<MoveDto> moves = chessGameDao.restart();
        for (MoveDto move : moves) {
            final String source = move.getSource();
            final String target = move.getTarget();

            board.move(source, target);
        }
    }

    public void move(final String source, final String target) {
        board.move(source, target);
        MoveDto moveDto = new MoveDto(source, target);
        chessGameDao.save(moveDto);
    }

    public void start() {
        chessGameDao.clear();
    }

    public double whiteScore() {
        return calculateScore(Color.WHITE);
    }

    public double blackScore() {
        return calculateScore(Color.BLACK);
    }

    private double calculateScore(final Color color) {
        final BoardResult boardResult = BoardResult.create(board());
        return boardResult.calculatePoints(color);
    }

    public boolean isKingDead() {
        return board.isKingDead(Color.WHITE) || board.isKingDead(Color.BLACK);
    }

    public Map<Position, Piece> board(){
        return board.getBoard();
    }
}
