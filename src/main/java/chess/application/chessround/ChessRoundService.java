package chess.application.chessround;

import chess.domain.chessround.ChessPiecesBuilder;
import chess.domain.chessround.ChessPlayer;
import chess.domain.chessround.ChessRound;
import chess.domain.chessround.dto.ChessPlayerDTO;

public class ChessRoundService {
    private static ChessRoundService chessRoundService = null;
    private static ChessRound chessRound;

    private ChessRoundService() {
    }

    public static ChessRoundService getInstance() {
        if (chessRoundService == null) {
            chessRoundService = new ChessRoundService();
        }
        return chessRoundService;
    }

    public void start() {
//        chessRound = ChessRound.of()
    }

    public ChessPlayerDTO fetchWhitePlayer() {
        // whiltePlayer


        // ChessPlayer whitePlayer = chessRound.getWhitePlayer();

        ChessPlayer whitePlayer = ChessPlayer.from(ChessPiecesBuilder.initializeWhiteChessPieces());

        ChessRoundAssembler chessRoundAssembler = ChessRoundAssembler.getInstance();
        return chessRoundAssembler.makeChessPlayerDTO(whitePlayer);
    }
}
