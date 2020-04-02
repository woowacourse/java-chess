$(document).ready(function () {
    for (var i = 0; i < 8; i++) {
        var row = $.parseHTML(`<div class="row"></div>`);

        for (var j = 0; j < 8; j++) {
            if (j % 2 === i % 2) {
                $(row).append(`<div class="col-1 border test">1 of 2</div>`);
            } else {
                $(row).append(`<div class="col-1 border test black">1 of 2</div>`);
            }
        }
        $("#table").append(row);
    }
});
