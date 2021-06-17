function setCategory(){
if($('input:radio[name=category]').is(':checked')){
        $('#category-v').show();
    }else{
        $('#category-v').hide();
    }
}



function warnEmpty() {
    alert("입력해주세요");
}

function submitplace1(){
    const newplaceEL = document.getElementById('txt_1');
    const newplace = newplaceEL.value.trim();

    if(newplace) {

    const contentEL = document.createElement('div');
    contentEL.classList.add('place-content');
    contentEL.innerText = newplace;

    const placeEL = document.createElement('div');
    placeEL.classList.add('place');
    placeEL.appendChild(contentEL);

    document.getElementById('wish-list1').appendChild(placeEL);
    newplaceEL.value="";

}
else warnEmpty();
}
