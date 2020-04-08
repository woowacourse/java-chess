package converter;

import chess.board.ChessBoard;
import chess.game.ChessGame;
import chess.location.Location;
import chess.piece.type.*;
import chess.team.Team;
import dto.ChessGameDto;
import vo.PieceVo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessGameConverter {
    public static final Map<String, Piece> map = new HashMap<>();

    static {
        map.put("b", new Bishop(Team.WHITE));
        map.put("k", new King(Team.WHITE));
        map.put("n", new Knight(Team.WHITE));
        map.put("p", new Pawn(Team.WHITE));
        map.put("q", new Queen(Team.WHITE));
        map.put("r", new Rook(Team.WHITE));

        map.put("B", new Bishop(Team.BLACK));
        map.put("K", new King(Team.BLACK));
        map.put("N", new Knight(Team.BLACK));
        map.put("P", new Pawn(Team.BLACK));
        map.put("Q", new Queen(Team.BLACK));
        map.put("R", new Rook(Team.BLACK));
    }

    public static ChessGame convert(List<PieceVo> pieceVos, ChessGameDto chessGameDTO) {

        boolean isTurnBlack = chessGameDTO.isTurnBlack();
        Team turn = getTurn(isTurnBlack);

        Map<Location, Piece> board = new HashMap<>();
        for (PieceVo pieceVo : pieceVos) {
            Piece piece = map.get(pieceVo.getName());
            Location location = new Location(pieceVo.getRow(), pieceVo.getCol().charAt(0));
            board.put(location, piece);
        }
        return new ChessGame(new ChessBoard(board), turn);
    }

    private static Team getTurn(boolean isTurnBlack) {
        if (isTurnBlack) {
            return Team.BLACK;
        }
        return Team.WHITE;
    }
}
