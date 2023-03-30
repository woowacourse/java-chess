package chess.domain.chessGame;

import chess.domain.Board;
import chess.dto.PieceDto;
import chess.domain.piece.PlayingCamp;
import chess.domain.piece.Piece;
import chess.domain.piece.move_rule.*;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.view.OutputView;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadyChessGame implements ChessGame {

    private static final String GAME_NOT_START_ERROR_MESSAGE = "아직 게임을 시작하지 않았습니다.";

    public ReadyChessGame() {
    }

    @Override
    public Map<Position, PieceDto> move(String currentPosition, String nextPosition) {
        throw new IllegalStateException(GAME_NOT_START_ERROR_MESSAGE);
    }

    @Override
    public List<Double> calculateScore() {
        throw new IllegalStateException(GAME_NOT_START_ERROR_MESSAGE);
    }

    @Override
    public ChessGame start() {
        Board board = new Board(initializedBoard());
        return new PlayingChessGame(board);
    }

    @Override
    public void end() {
        throw new IllegalStateException(GAME_NOT_START_ERROR_MESSAGE);
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public Map<Position, PieceDto> getPrintingBoard() {
        return Collections.emptyMap();
    }


    @Override
    public PlayingCamp getThisTurn() {
        return null;
    }

    private Map<Position, Piece> initializedBoard() {
        Map<Position, Piece> board = new HashMap<>();
        board = initializePawnLinePieces(board, Rank.RANK2, PlayingCamp.WHITE);
        board = initializePawnLinePieces(board, Rank.RANK7, PlayingCamp.BLACK);

        board = initializeEndLinePieces(board, Rank.RANK1, PlayingCamp.WHITE);
        board = initializeEndLinePieces(board, Rank.RANK8, PlayingCamp.BLACK);
        return board;
    }

    private Map<Position, Piece> initializePawnLinePieces(Map<Position, Piece> board, Rank rank, PlayingCamp playingCamp) {
        for (File file : File.values()) {
            board.put(Position.of(file, rank), new Piece(PawnMoveRule.from(playingCamp), playingCamp));
        }
        return board;
    }

    private Map<Position, Piece> initializeEndLinePieces(Map<Position, Piece> board, Rank rank, PlayingCamp playingCamp) {
        board.put(Position.of(File.FILE_A, rank), new Piece(RookMoveRule.getInstance(), playingCamp));
        board.put(Position.of(File.FILE_B, rank), new Piece(KnightMoveRule.getInstance(), playingCamp));
        board.put(Position.of(File.FILE_C, rank), new Piece(BishopMoveRule.getInstance(), playingCamp));
        board.put(Position.of(File.FILE_D, rank), new Piece(QueenMoveRule.getInstance(), playingCamp));
        board.put(Position.of(File.FILE_E, rank), new Piece(KingMoveRule.getInstance(), playingCamp));
        board.put(Position.of(File.FILE_F, rank), new Piece(BishopMoveRule.getInstance(), playingCamp));
        board.put(Position.of(File.FILE_G, rank), new Piece(KnightMoveRule.getInstance(), playingCamp));
        board.put(Position.of(File.FILE_H, rank), new Piece(RookMoveRule.getInstance(), playingCamp));
        return board;
    }
}
