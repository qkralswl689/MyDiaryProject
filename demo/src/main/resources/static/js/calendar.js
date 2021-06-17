$(function() {
  var memNo = $("#memNo").val();
  
  $('.BtClose').click(function() {
    $('#modal').hide();
  });
  
  $('.writeClose').click(function() {
    event.preventDefault();
    $('#writeModal').hide();
  });
  
  
  $('form[name=frm]').submit(function() {
    $('.infobox').each(function(idx,item) {
      if($(this).text().length()<1 ){
        alert($(this).prev().html()+"을(를) 입력하세요!");
        event.preventDefault();
        $(this).focus();
        return false;
      }
    });
  });

$('DOMContentLoaded', function() {
  var calendarEl = document.getElementById('calendar');

  var calendar = new FullCalendar.Calendar(calendarEl, {
    plugins: [ 'interaction', 'resourceDayGrid', 'resourceTimeGrid' ],
    defaultView: 'dayGridMonth',
    defaultDate: '2020-08-01',
    editable: true,
    selectable: true,
    nowIndicator : true,
    eventLimit: true, // allow "more" link when too many events
    timeZone : 'local', //우리나라 시간맞춤
    buttonText:{ //버튼 텍스트 변환
    today : '오늘',  
    month : '월',  
    week : '주',  
    day : '일',  
    list : '주간 일정표',  
      
    },
    
    //일정 버튼 추가하기
    customButtons: {
        add_event: {
            text: '일정추가',
            click: function() {
              $('#writeModal').show();
              
              //var dateStr = prompt('Enter a date in YYYY-MM-DD format');
                //var date = new Date(dateStr + 'T00:00:00'); // will be in local time
                    
                 $("#frm").submit(function() {
                $.ajax({
                     url:"<c:url value='/schedule/ajaxWrite.do'/>",
                     type:"post",
                     datatype:"json",
                     data: $(this).serializeArray(),
                     success:function(res){
                       calendar.addEvent({
                                     title: res.title,
                                     start: res.startDay,
                                     end: res.endDay,
                                     allDay: true
                                   });
                                   alert('일정이 추가되었습니다!');
                     },
                     error:function(xhr, status, error){
                       alert(status + ", " + error);
                     }
                   });
                 });  
            }
        }
    },
    
    
    
    header: {
      left: 'prev,next today',
      center: 'title',
      right: 'add_event,resourceTimeGridDay,resourceTimeGridTwoDay,timeGridWeek,dayGridMonth'
    },
    views: {
    dayGrid :{
      titleFormat: {
          year:'numeric',
          month: '2-digit'
        }
    }
    },

    //// uncomment this line to hide the all-day slot
    //allDaySlot: false,
  
    resources: [
      { id: 'a', title: 'Room A' },
      { id: 'b', title: 'Room B', eventColor: 'green' },
      { id: 'c', title: 'Room C', eventColor: 'orange' },
      { id: 'd', title: 'Room D', eventColor: 'red' }
    ],
    events: 
      function (start,end,successCallback) {
       $.ajax({
       url:"<c:url value='/schedule/ajaxSelect.do'/>",
       type:"get",
       dataType:"json",
       data:"memNo="+$("#memNo").val(),
       success:function(res){	
         var events = [];
         $.each(res,function(idx,item) {
          //alert(item.startDay);
          //alert(res[idx].title); 잘 나옵니다.
          
          var startDay = res[idx].startDay;
          var endDay = res[idx].startDay;
          var title = res[idx].title;
          events.push({
            id : 'a', // a로 만들어놨습니다.
            start : startDay , 
            end : endDay , 
            title : title
          });
        });// .each()
        alert(events); //여기서부터 문제입니다..
        console.log("events="+events); //콘솔도 안되더라구요,,,,,
        successCallback(events); //왜 안될까요,,
       },
       error: function(jqXHR, exception) {
                 if (jqXHR.status === 0) {
                         alert('Not connect.\n Verify Network.');
                     } 
                     else if (jqXHR.status == 400) {
                         alert('Server understood the request, but request content was invalid. [400]');
                     } 
                     else if (jqXHR.status == 401) {
                         alert('Unauthorized access. [401]');
                     } 
                     else if (jqXHR.status == 403) {
                         alert('Forbidden resource can not be accessed. [403]');
                     } 
                     else if (jqXHR.status == 404) {
                         alert('Requested page not found. [404]');
                     } 
                     else if (jqXHR.status == 500) {
                         alert('Internal server error. [500]');
                     } 
                     else if (jqXHR.status == 503) {
                         alert('Service unavailable. [503]');
                     } 
                     else if (exception === 'parsererror') {
                         alert('Requested JSON parse failed. [Failed]');
                     } 
                     else if (exception === 'timeout') {
                         alert('Time out error. [Timeout]');
                     } 
                     else if (exception === 'abort') {
                         alert('Ajax request aborted. [Aborted]');
                     }
                     else {
                         alert('Uncaught Error.n' + jqXHR.responseText);
                     }
              }
     });
      }
      
           /* 
           { id: '1', resourceId: 'a', start: startDay, end: endDay, title: content },
           { id: '2', resourceId: 'a', start: '2020-08-12 09:00', end: '2020-08-13 14:00', title: 'event 2' },
           { id: '3', resourceId: 'b', start: '2020-08-15', end: '2020-08-17', title: 'event 3' },
           { id: '4', resourceId: 'c', start: '2020-08-18', end: '2020-08-22', title: 'event 4' },
           { id: '5', resourceId: 'd', start: '2020-08-23', end: '2020-08-25', title: 'event 5' } 
            */
     ,
  
   //드래그
    select: function(arg) {
    $('#writeModal').show();
      console.log(
        'select',
        arg.startStr,
        arg.endStr,
        arg.resource ? arg.resource.id : '(no resource)'
      );
      
      calendar.addEvent({
          title: content,
          start: startDay,
          end: endDay,
          allDay: true
       });
    },
    
    //날짜클릭
    dateClick: function(arg) {
    $('#writeModal').show();
      console.log(
        'dateClick',
        arg.date,
        arg.resource ? arg.resource.id : '(no resource)'
      );
      calendar.addEvent({
          title: 'title',
          start: startDay,
          end: endDay,
          allDay: true
       });
      
    },
    
    //일정 보여주기
    eventClick: function(info) {
        $('#modal').show();
        
        //$("#modal-type"),text($("#type").on("selected","selected"));
        $('#modal-title').text(title);
        $('#modal-contents').text($("textarea#content").val());
        
        
        // borderColor 변경
        //info.el.style.borderColor = 'red';
     },
    
    // 한국어 설정
    locale: 'ko'
  });

  calendar.render();
  
});
$("#schedulePicker input").datepicker({
    dateFormat:'yy-mm-dd',
       changeYear:true,
       changeMonth:true,
       dayNamesMin:['일','월','화','수','목','금','토'],
       monthNamesShort: ['1월', '2월', '3월', '4월', '5월',
         '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
 });

});