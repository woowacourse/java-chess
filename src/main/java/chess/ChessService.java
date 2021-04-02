package chess;

import chess.dao.PieceDao;
import chess.domain.game.Game;
import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import chess.domain.position.Position;
import chess.dto.BoardDto;
import chess.dto.RequestDto;
import chess.dto.ResponseDto;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ChessService {

    private Game game;
    private final PieceDao pieceDao;

    public ChessService() {
        this.game = new Game();
        this.pieceDao = new PieceDao();
    }

    public void init() throws SQLException {
//        if (pieceDao.load() == null) {
//            Pieces pieces = new Pieces();
//            pieces.init();
//            pieceDao.clear(pieces);
//        }
//
//        final Map<Position, Piece> board = pieceDao.load();
        System.out.println("어디에서??????????\n");
        pieceDao.clear(game.getPieces());
        System.out.println("에러가???????????????");
    }

    public ResponseDto move(RequestDto requestDto) {
        String from = requestDto.from();;
        String to = requestDto.to();
        try {
            game.move(Position.from(from), Position.from(to));
            changeStatusData(from, to);
            changeTurn();
            if(!game.isNotEnd()) {
                return new ResponseDto("300", "끝", game.winner().getColor().name());
            }
            return new ResponseDto("200", "성공", game.currentPlayer().toString());
        } catch (Exception e) {
            return new ResponseDto("400", e.getMessage(), game.currentPlayer().toString());
        }
    }

    private void changeStatusData(final String from, final String to) throws SQLException {
        final Pieces pieces = game.getPieces();
        pieceDao.savePiece(from, pieces.getPieceOf(Position.from(to)));
    }

    private void changeTurn() throws SQLException {
        pieceDao.updateTurn(game.getTurn());
        game.getTurn().next();
    }

    public Map<String, String> getCurrentBoard() {
        BoardDto boardDto = new BoardDto(game.getPieces());
        return boardDto.getBoard();
    }

    public void initChessBoard() {
        game = new Game();
    }
}
