window.addEventListener("load", function(){

    var section = this.document.querySelector(".chess-ui");
    var pngs = section.querySelectorAll(".img");
    var positions = "";

    for (var i = 0; i < pngs.length; i++) {
        pngs[i].onclick = function(event) {
            var parentTarget = event.target.parentElement;
            positions += parentTarget.id;

            if (positions.length == 4) {
                var form = document.createElement('form');

                form.setAttribute("charset", "UTF-8");
                form.setAttribute('method', 'post');
                form.setAttribute('action', '/move');

                var command = document.createElement('input');
                command.setAttribute('type', 'hidden');
                command.setAttribute('name', 'command');
                command.setAttribute('value', positions);
                form.appendChild(command);

                document.body.appendChild(form);
                form.submit();
                positions = "";
            }
        }
    }
});