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
        // TODO : Round 생성 및 초기화
    }

    public ChessPlayerDTO fetchWhitePlayer() {
        // TODO : round에서 whitePlayer를 가져온다.

        // TODO : white test
        ChessPlayer whitePlayer = ChessPlayer.from(ChessPiecesBuilder.initializeWhiteChessPieces());

        ChessRoundAssembler chessRoundAssembler = ChessRoundAssembler.getInstance();
        return chessRoundAssembler.makeChessPlayerDTO(whitePlayer);
    }

    public ChessPlayerDTO fetchBlackPlayer() {
        // TODO : round에서 blackPlayer를 가져온다.

        // TODO : black test
        ChessPlayer blackPlayer = ChessPlayer.from(ChessPiecesBuilder.initializeBlackChessPieces());

        ChessRoundAssembler chessRoundAssembler = ChessRoundAssembler.getInstance();
        return chessRoundAssembler.makeChessPlayerDTO(blackPlayer);
    }
}
