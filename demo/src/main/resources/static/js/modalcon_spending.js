
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
/**
+버튼을 누르면 나오는 모달박스에서
지출 라디오 버튼을 눌렀다면 isCard 값을 받아야하니 visible 해줘야하고
나머지 값들은 값을 숨겨 데이터를 못 받게 한다.
*/
function setSpendingValue(){
if($('input:radio[id=value_expenditure]').is(':checked')){
        $('#iscard_value').show();
    }else{
        $('#iscard_value').hide();
    }
}

