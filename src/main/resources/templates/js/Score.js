function score() {
    $.ajax({
        type: 'post',
        url: '/status',
        dataType: 'text',
        success(data, status, jqXHR) {
            console.log(status);
            console.log(jqXHR.responseText);
            alert(data);
        },
        error(jpXHR, textError, errorThrown) {
            console.log(textError);
            console.log(errorThrown);
            alert(jpXHR.responseText);
        }
    });
}