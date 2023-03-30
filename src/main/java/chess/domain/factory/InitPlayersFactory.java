package chess.domain.factory;

import chess.domain.Color;
import chess.domain.Player;
import chess.domain.Players;
import chess.domain.dao.PieceDao;
import chess.domain.dao.PieceDaoImpl;
import chess.domain.dao.TurnDao;
import chess.domain.dao.TurnDaoImpl;
import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import java.util.List;

public class InitPlayersFactory {

    public static Players initializeChessBoard() {

        PieceDao dao = new PieceDaoImpl();
        TurnDao turnDao = new TurnDaoImpl();

        Pieces whitePieces = getDbPiecesByColor(dao, Color.WHITE);
        Pieces blackPieces = getDbPiecesByColor(dao, Color.BLACK);

        Player whitePlayer = Player.fromPlayerWithColorAndPieces(Color.WHITE, whitePieces);
        Player blackPlayer = Player.fromPlayerWithColorAndPieces(Color.BLACK, blackPieces);

        return Players.of(whitePlayer, blackPlayer, turnDao.getCurrentTurn());
    }

    private static Pieces getDbPiecesByColor(final PieceDao dao, final Color color) {
        List<Piece> dbPiecesByColor = dao.findPieceByColor(color);
        if (dbPiecesByColor.isEmpty()) {
            Pieces pieces = Pieces.createWhitePieces();
            insertAll(dao, pieces, color);
            return pieces;
        }
        return Pieces.from(dbPiecesByColor);
    }

    private static void insertAll(PieceDao dao, Pieces pieces, Color color) {
        for (Piece piece : pieces.getPieces()) {
            dao.create(piece, color);
        }
    }
}
