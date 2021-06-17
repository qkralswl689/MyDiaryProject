$(document).ready(function () {
    $("#loginform").validate({
        rules: {
            useremail: {
                required: true,
                remote: {
                    url: "/isDuplicated",
                    type: "POST",
                    data: {
                        id: function () {
                            return $('input[name="useremail"]').val();
                        }
                    }
                }
            },
            userpw: {
                required: true
            },
            username{
                required: true
            }
        },
        messages: {
            useremail: {
                remote: 'This id is duplicated! please use another email.'
            },
            userpw: {
                remote: 'This id is duplicated! please use another password.'
//                equalTo: 'please enter the same password again.'
            },
            username{
            remote: 'This id is duplicated! please use another name.'
            }
        },
        submitHandler: function () {
            $.ajax({
                url: "login/register",
                type: "POST",
                dataType: "text",
                data: $("#loginform").serialize(),
                success: function () {
                    alert("success")
                },
                error: function () {
                    alert("fail");
                }
            })
        }
    });
});