package chess.domain.service;

import chess.domain.DTO.MoveRequestDTO;
import chess.domain.DTO.MoveResultDTO;
import chess.domain.DTO.pieceOnBoardDTO;
import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.result.ResultDto;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import spark.Request;
import spark.Response;

public class WebController {

    private static ChessBoard chessBoard;
    private static Gson gson;

    public WebController(Gson gson) {
        this.gson = gson;
    }

    public static Object start(Request request, Response response) {
        Map<String, pieceOnBoardDTO> pieces = getAllPieces();
        return gson.toJson(pieces);
    }

    public static Object move(Request request, Response response) {
        MoveRequestDTO moveRequestDTO = gson.fromJson(request.body(), MoveRequestDTO.class);
        MoveResultDTO moveResultDTO;
        moveResultDTO = createMoveResultDTO(moveRequestDTO);

        return gson.toJson(moveResultDTO);
    }

    private static MoveResultDTO createMoveResultDTO(MoveRequestDTO moveRequestDto) {
        ResultDto result;
        MoveResultDTO moveResultDTO;
        try {
            chessBoard.move(moveRequestDto.getSource(), moveRequestDto.getTarget());
            checkGameEnd();

            result = chessBoard.result();
            moveResultDTO = getMoveResultDTO(result, true);
        } catch (IllegalArgumentException exception) {
            result = chessBoard.result();
            moveResultDTO = getMoveResultDTO(result, false);
        }
        return moveResultDTO;
    }

    private static MoveResultDTO getMoveResultDTO(ResultDto result, boolean state) {
        return new MoveResultDTO(state,
            chessBoard.isPlaying(),
            result.whiteScore().getScore(),
            result.blackScore().getScore());
    }

    private static void checkGameEnd() {
        if (!chessBoard.isPlaying()) {
            throw new IllegalArgumentException("game End state");
        }
    }

    public static Object reset(Request request, Response response) {
        chessBoard = new ChessBoard();
        return request;
    }

    private static Map<String, pieceOnBoardDTO> getAllPieces() {
        Map<String, pieceOnBoardDTO> pieces = new HashMap<>();
        for (Entry<Position, Piece> entry : chessBoard.getChessBoard().entrySet()) {
            pieces.put(entry.getKey().getPosition(),
                new pieceOnBoardDTO(entry.getValue().getColor(),
                    entry.getValue().getPieceName()));
        }
        return pieces;
    }

    public void setChessBoard(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }
}
