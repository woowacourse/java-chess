function move(position) {
    console.log(position)
    if (document.getElementsByClassName('sourcePosition').length === 0) {
        document.getElementById(position).classList.add('sourcePosition');
        return;
    }
    document.getElementById(position).innerHTML = document.getElementsByClassName('sourcePosition')[0].innerHTML;
    console.log(document.getElementsByClassName('sourcePosition')[0].id)
    console.log(position)
    const source = document.getElementsByClassName('sourcePosition')[0].id;
    document.getElementsByClassName('sourcePosition')[0].innerHTML = "";
    document.getElementsByClassName('sourcePosition')[0].classList.remove('sourcePosition');
    const object = {
        "source" : source,
        "destination": position
    }
    $.ajax({
        url: "/move",
        type: "POST",
        data: JSON.stringify(object),
        success: function (data) {
            console.log(data);
        },
        error: function (data){
            alert(data);
        }
    })
}
