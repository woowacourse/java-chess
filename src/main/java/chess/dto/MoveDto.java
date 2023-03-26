package chess.dto;

import java.util.List;

public class MoveDto {
    private static final int SOURCE_INDEX = 1;
    private static final int DESTINATION_INDEX = 2;
    
    private final String sourceCoordinate;
    private final String destinationCoordinate;
    
    public MoveDto(List<String> coordinates) {
        sourceCoordinate = coordinates.get(SOURCE_INDEX);
        destinationCoordinate = coordinates.get(DESTINATION_INDEX);
    }
    
    public String sourceCoordinate() {
        return sourceCoordinate;
    }
    
    public String destinationCoordinate() {
        return destinationCoordinate;
    }
}
