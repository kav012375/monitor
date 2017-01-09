/**
 * Created by FengG on 16/7/6.
 */
function ShowNormalDialog(dialogName,content){
    $("#"+dialogName+"").html("<p>"+content+"</p>");
    $("#"+dialogName+"").dialog("open");
}

function CheckLoginStatus() {
    $.post("/user/checklogin.do",function (data) {
        var ret = data;
        if (ret.toString() == "20008"){
            $.ligerDialog.show({
                top:100,
                content: "请登录",
                title:"未登录",
                allowClose: false,
                isDrag : false,
                url:"loginFrame.htm"
            });
        }else {
            alert(data);
        }
    });
}