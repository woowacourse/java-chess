
window.onload = function(){
    const pieces = document.getElementsByClassName('piece');
    Array.from(pieces).forEach((el)=>{
        console.log(el.tagName);
        el.addEventListener('click', click());
    })
}

function printPosition(){
    return function(event){
        console.log(event.target.id);
    }
}

function click(){
    return function(event){
        console.log(event.target.id);

        const position = new Object();
        position.position = event.target.id;
        const jsonData = JSON.stringify(position);

        $.ajax({
            url:"/click",
            type:"POST",
            data:jsonData,
            contentType: "application/json",
            success: function(result) {
                if (result) {
                    alert(result);
                } else {
                    alert("잠시 후에 시도해주세요.");
                }
            },
            error: function() {
                alert("에러 발생");
            }
        })
    }
}