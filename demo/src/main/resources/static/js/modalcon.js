
function openModal(modalname){
    document.get
    $("#dim").fadeIn(300);
    $("."+modalname).fadeIn(300);
}


window.onload = function(){
  $(".close").on("click",function(){
    $("#dim").fadeOut(300);
    $(".modal-con").fadeOut(300);
  });
};

