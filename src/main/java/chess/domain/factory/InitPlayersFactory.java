package chess.domain.factory;

import chess.domain.Color;
import chess.domain.Player;
import chess.domain.Players;
import chess.domain.dao.PieceDao;
import chess.domain.dao.PieceDaoImpl;
import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import java.util.List;

public class InitPlayersFactory {

    private static final PieceDao pieceDao = PieceDaoImpl.getInstance();

    public static Players initializePlayers() {
        Pieces whitePieces = getDbPiecesByColor(Color.WHITE);
        Pieces blackPieces = getDbPiecesByColor(Color.BLACK);

        Player whitePlayer = Player.fromPlayerWithColorAndPieces(Color.WHITE, whitePieces);
        Player blackPlayer = Player.fromPlayerWithColorAndPieces(Color.BLACK, blackPieces);

        return Players.of(whitePlayer, blackPlayer);
    }

    private static Pieces getDbPiecesByColor(final Color color) {
        List<Piece> dbPiecesByColor = pieceDao.findPieceByColor(color);
        if (dbPiecesByColor.isEmpty()) {
            return insertPiecesByColor(color);
        }
        return Pieces.from(dbPiecesByColor);
    }

    private static Pieces insertPiecesByColor(final Color color) {
        if (color == Color.WHITE) {
            Pieces pieces = Pieces.createWhitePieces();
            return insertAllByColor(color, pieces);
        }
        Pieces pieces = Pieces.createBlackPieces();
        return insertAllByColor(color, pieces);
    }

    private static Pieces insertAllByColor(final Color color, final Pieces pieces) {
        for (Piece piece : pieces.getPieces()) {
            pieceDao.create(piece, color);
        }
        return pieces;
    }

}
