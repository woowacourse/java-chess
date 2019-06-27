$(function () {
    var index = 0;
    var source;

    $('#boards tr td').click(function () {
        var position = $(this).attr('id');
        var value = index % 2;

        if (value === 0) {
            source = position;
        } else {
            if (source === position) {
                return;
            }
            var moveUrl = "/move?source=" + source + "&target=" + position;
            console.log(moveUrl);
            $(location).attr('href', moveUrl);
        }

        index++;
        return false;
    });
});

