export async function getGridAndPiecesByRoomName(roomName) {
    return await axios({
        method: 'get',
        url: `/grid/${roomName}`,
    });
}

export async function startGameByGridId(gridId) {
    return await axios({
        method: 'post',
        url: `/grid/${gridId}/start`
    })
}

export async function finishGameByGridId(gridId) {
    return await axios({
        method: 'post',
        url: `/grid/${gridId}/finish`
    });
}

export async function restartGameByRoomId(roomId) {
    return await axios({
        method: 'get',
        url: `/room/${roomId}/restart`
    })
}

export async function movePiece(piecesDto, gridDto, sourcePosition, targetPosition) {
    return await axios({
        method: 'post',
        url: '/move',
        data: {
            piecesDto,
            sourcePosition,
            targetPosition,
            gridDto
        }
    });
}