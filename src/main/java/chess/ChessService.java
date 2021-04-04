package chess;

import chess.dao.PieceDao;
import chess.domain.Turn;
import chess.domain.game.Game;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import chess.domain.position.Position;
import chess.dto.RequestDto;
import chess.dto.ResponseDto;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ChessService {

    private final PieceDao pieceDao;
    private Game game;

    public ChessService() {
        this.game = new Game();
        this.pieceDao = new PieceDao();
    }

    public void init() throws SQLException {
        if (pieceDao.load() == null) {
            final Pieces pieces = new Pieces();
            pieces.init();
            pieceDao.clear(pieces);
        }
        final List<Piece> pieces = pieceDao.load();
        final String turn = pieceDao.findTurn();
        game = new Game(new Pieces(pieces), new Turn(turn));
    }

    public ResponseDto move(final RequestDto requestDto) {
        final String from = requestDto.from();
        final String to = requestDto.to();
        try {
            game.move(Position.from(from), Position.from(to));
            changeStatusData(from, to);
            changeTurn();
            if (!game.isNotEnd()) {
                return new ResponseDto("300", "끝", game.winner().getColor().name());
            }
            return new ResponseDto("200", "성공", game.currentPlayer().toString());
        } catch (Exception e) {
            return new ResponseDto("400", e.getMessage(), game.currentPlayer().toString());
        }
    }

    private void changeStatusData(final String from, final String to) throws SQLException {
        final Pieces pieces = game.getPieces();
        pieceDao.savePiece(to, pieces.getPieceOf(Position.from(to)));
        pieceDao.deletePiece(from);
    }

    public String turn() throws SQLException {
        return pieceDao.findTurn();
    }

    private void changeTurn() throws SQLException {
        pieceDao.updateTurn(game.getTurn());
    }

    public Map<String, String> getCurrentBoard() throws SQLException {
        final Map<Position, Piece> board = pieceDao.findPieces(game.getPieces());
        final Map<String, String> result = new LinkedHashMap<>();

        for (final Position position : board.keySet()) {
            final String positionName = position.column().value() + position.row().value();
            final String pieceName;
            if (board.get(position).isSameColor(Color.BLACK)) {
                pieceName = "B" + board.get(position).display().toUpperCase();
            } else {
                pieceName = "W" + board.get(position).display().toUpperCase();
            }
            result.put(positionName, pieceName);
        }
        return result;
    }

    public void initChessBoard() throws SQLException {
        game = new Game();
        pieceDao.clear(game.getPieces());
    }
}
