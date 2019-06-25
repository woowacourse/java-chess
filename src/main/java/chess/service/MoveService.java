package chess.service;

import chess.domain.board.Board;
import chess.domain.direction.Route;
import chess.domain.direction.core.Square;
import chess.domain.piece.core.Piece;
import chess.domain.piece.core.Team;
import chess.dto.ChessMoveDTO;

public class MoveService {
    public static MoveService getInstance() {
        return MovieServiceHolder.INSTANCE;
    }

//    public Board request(ChessMoveDTO moveDTO) {
//        Board board = Board.drawBoard(moveDTO.getBoard(), Team.valueOf(moveDTO.getTeam()));
//        Piece piece = board.getPiece();
//        Route route = piece.getRoute(source, target);
//        return board.changeBoard(route);
//    }

    private static class MovieServiceHolder {
        static final MoveService INSTANCE = new MoveService();
    }

}
