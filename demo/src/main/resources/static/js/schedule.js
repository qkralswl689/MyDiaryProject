(function () {
    var jsonData = jsonList.replace(/&quot;/g,'"');
    var jsonConvertList = JSON.parse(jsonData);
    var defaultDate = String.prototype.yyyy_mm_dd();

    document.addEventListener('DOMContentLoaded', function() {
        var calendarEl = document.getElementById('calendar');
        var calendar = new FullCalendar.Calendar(calendarEl, {
            plugins: [ 'bootstrap', 'interaction', 'dayGrid', 'timeGrid', 'list' ],
            header: {
                left: 'prev,next today',
                //right: 'dayGridMonth,timeGridWeek,timeGridDay,listWeek'
                right: 'title'
            },
            defaultDate: defaultDate,
            weekNumbers: true,
            weekNumbersWithinDays: true,
            weekNumberCalculation: 0,
            locale: 'ko',
            allDayDefault: false,
            editable: true,
            weekNumbers: false,
            eventLimit: true, // allow "more" link when too many events
            events: eval(JSON.stringify(jsonConvertList.item))
        });
        calendar.render();
    });
})();