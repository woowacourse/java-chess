function clickSpace(e){
  var click_position = e.getAttribute('id');
  var source_value = document.getElementById("source").value;
  if(source_value == ""){
    document.getElementById("source").value = e.getAttribute('id');
    return;
  }
  if(source_value != click_position){
    document.getElementById("target").value = e.getAttribute('id');
    return;
  }
}
