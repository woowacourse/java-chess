package chess.domain.factory;

import chess.domain.Color;
import chess.domain.Piece;
import chess.domain.Pieces;
import chess.domain.Player;
import chess.domain.Players;
import chess.service.GameService;

import java.util.List;

public class PlayersFactory {

    private final GameService gameService;

    public PlayersFactory(GameService gameService) {
        this.gameService = gameService;
    }

    public Players createChessBoard() {
        Pieces whitePieces = getDbWhitePieces();
        Pieces blackPieces = getDbBlackPieces();

        Player whitePlayer = Player.fromWhitePlayer(whitePieces);
        Player blackPlayer = Player.fromBlackPlayer(blackPieces);

        return Players.of(whitePlayer, blackPlayer, gameService.getCurrentTurn());
    }

    private Pieces getDbWhitePieces() {
        List<Piece> dbWhitePieces = gameService.findPieceByColor(Color.WHITE);
        if (dbWhitePieces.isEmpty()) {
            Pieces whitePieces = Pieces.createWhitePieces();
            insertAll(whitePieces, Color.WHITE);
            return whitePieces;
        }
        return Pieces.from(dbWhitePieces);
    }

    private Pieces getDbBlackPieces() {
        List<Piece> dbBlackPieces = gameService.findPieceByColor(Color.BLACK);
        if (dbBlackPieces.isEmpty()) {
            Pieces blackPieces = Pieces.createBlackPieces();
            insertAll(blackPieces, Color.BLACK);
            return blackPieces;
        }
        return Pieces.from(dbBlackPieces);
    }

    private void insertAll(Pieces pieces, Color color) {
        for (Piece piece : pieces.getPieces()) {
            gameService.create(piece, color);
        }
    }

}
