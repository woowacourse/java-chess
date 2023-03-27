package chess.domain.factory;

import chess.domain.Color;
import chess.domain.Piece;
import chess.domain.Pieces;
import chess.domain.Player;
import chess.domain.Players;
import chess.domain.dao.PieceDao;
import chess.domain.dao.PieceDaoImpl;
import chess.domain.dao.TurnDao;
import chess.domain.dao.TurnDaoImpl;

import java.util.List;

public class InitPlayersFactory {

    public static Players initializeChessBoard() {

        PieceDao dao = new PieceDaoImpl();
        TurnDao turnDao = new TurnDaoImpl();

        Pieces whitePieces = getDbWhitePieces(dao);
        Pieces blackPieces = getDbBlackPieces(dao);

        Player whitePlayer = Player.fromWhitePlayer(whitePieces);
        Player blackPlayer = Player.fromBlackPlayer(blackPieces);

        return Players.of(whitePlayer, blackPlayer, turnDao.getCurrentTurn());
    }

    private static Pieces getDbWhitePieces(PieceDao dao) {
        List<Piece> dbWhitePieces = dao.findPieceByColor(Color.WHITE);
        if (dbWhitePieces.isEmpty()) {
            Pieces whitePieces = Pieces.createWhitePieces();
            insertAll(dao, whitePieces, Color.WHITE);
            return whitePieces;
        }
        return Pieces.from(dbWhitePieces);
    }

    private static Pieces getDbBlackPieces(PieceDao dao) {
        List<Piece> dbBlackPieces = dao.findPieceByColor(Color.BLACK);
        if (dbBlackPieces.isEmpty()) {
            Pieces blackPieces = Pieces.createBlackPieces();
            insertAll(dao, blackPieces, Color.BLACK);
            return blackPieces;
        }
        return Pieces.from(dbBlackPieces);
    }

    private static void insertAll(PieceDao dao, Pieces pieces, Color color) {
        for (Piece piece : pieces.getPieces()) {
            dao.create(piece, color);
        }
    }
}
