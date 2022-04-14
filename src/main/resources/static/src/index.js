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

                var source = document.createElement('input');
                source.setAttribute('type', 'hidden');
                source.setAttribute('name', 'source');
                source.setAttribute('value', positions.substring(0, 2));
                form.appendChild(source);

                var target = document.createElement('input');
                target.setAttribute('type', 'hidden');
                target.setAttribute('name', 'target');
                target.setAttribute('value', positions.substring(2, 4));
                form.appendChild(target);

                document.body.appendChild(form);
                form.submit();
                positions = "";
            }
        }
    }
});