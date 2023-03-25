package service;

import java.util.Map;
import java.util.stream.Collectors;

import domain.PieceToScoreConverter;
import domain.PieceToStringConverter;
import domain.board.ChessBoard;
import domain.board.File;
import domain.board.Rank;
import domain.board.Square;
import domain.piece.Camp;
import dto.BoardResponseDto;
import dto.CommandRequestDto;

public class ChessService {
    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;

    private final ChessBoard chessBoard;
    private Camp currentCamp;
    private boolean ongoing;

    public ChessService() {
        this.chessBoard = new ChessBoard();
        this.currentCamp = Camp.WHITE;
        PieceToStringConverter.init();
        PieceToScoreConverter.init();
    }

    public void start() {
        if (!ongoing) {
            chessBoard.initialize();
            ongoing = true;
            return;
        }
        throw new IllegalStateException("이미 게임이 실행중입니다.");
    }

    public void end() {
        ongoing = false;
    }

    public void move(CommandRequestDto commandRequestDto) {
        if (ongoing) {
            Square currentSquare = convertToSquare(commandRequestDto.getCurrentSquareName());
            Square targetSquare = convertToSquare(commandRequestDto.getTargetSquareName());

            validateTurn(currentSquare);

            chessBoard.move(currentSquare, targetSquare);

            currentCamp = currentCamp.fetchOppositeCamp();
            return;
        }
        throw new IllegalStateException("게임을 먼저 실행해주세요.");
    }

    private Square convertToSquare(String squareName) {
        return new Square(File.find(squareName.charAt(FILE_INDEX)), Rank.find(squareName.charAt(RANK_INDEX)));
    }

    private void validateTurn(Square currentSquare) {
        if (!chessBoard.isCorrectCamp(currentCamp, currentSquare)) {
            throw new IllegalStateException(currentCamp.name() + "의 턴입니다.");
        }
    }

    public BoardResponseDto toBoardDto() {
        return new BoardResponseDto(chessBoard.getBoard());
    }

    public boolean isOngoing() {
        return ongoing;
    }
}
