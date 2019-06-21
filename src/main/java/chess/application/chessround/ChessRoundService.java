package chess.application.chessround;

import chess.domain.chesspoint.ChessPoint;
import chess.domain.chessround.ChessPiecesBuilder;
import chess.domain.chessround.ChessPlayer;
import chess.domain.chessround.ChessRound;
import chess.domain.chessround.dto.ChessPlayerDTO;

public class ChessRoundService {
    private static ChessRoundService chessRoundService = null;
    private ChessRound chessRound;

    private ChessRoundService() {
        // TODO : index controller에서 하는 일을 여기서 임시로 해준다.
        start();
    }

    public static ChessRoundService getInstance() {
        if (chessRoundService == null) {
            chessRoundService = new ChessRoundService();
        }
        return chessRoundService;
    }

    public void start() {
        ChessPiecesBuilder chessPiecesBuilder = ChessPiecesBuilder.getInstance();
        ChessPlayer whitePlayer = ChessPlayer.from(chessPiecesBuilder.initializeWhiteChessPieces());
        ChessPlayer blackPlayer = ChessPlayer.from(chessPiecesBuilder.initializeBlackChessPieces());

        chessRound = ChessRound.of(whitePlayer, blackPlayer);
    }

    public ChessPlayerDTO fetchWhitePlayer() {
        ChessPlayer whitePlayer = chessRound.getWhitePlayer();

        ChessRoundAssembler chessRoundAssembler = ChessRoundAssembler.getInstance();
        return chessRoundAssembler.makeChessPlayerDTO(whitePlayer);
    }

    public ChessPlayerDTO fetchBlackPlayer() {
        ChessPlayer blackPlayer = chessRound.getBlackPlayer();

        ChessRoundAssembler chessRoundAssembler = ChessRoundAssembler.getInstance();
        return chessRoundAssembler.makeChessPlayerDTO(blackPlayer);
    }

    public void move(String sourceId, String targetId) {
        ChessPoint source = parseChessPoint(sourceId);
        ChessPoint target = parseChessPoint(targetId);
        chessRound.move(source, target);
    }

    private ChessPoint parseChessPoint(String pointId) {
        int row = Integer.parseInt(pointId.substring(0, 1));
        int column = Integer.parseInt(pointId.substring(1, 2));
        return ChessPoint.of(row, column);
    }

    public double getWhitePlayerScore() {
        return chessRound.getWhitePlayerScore();
    }

    public double getBlackPlayerScore() {
        return chessRound.getBlackPlayerScore();
    }

    public boolean isWhiteTurn() {
        return chessRound.isWhiteTurn();
    }
}
