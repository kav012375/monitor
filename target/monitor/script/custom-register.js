/**
 * Created by FengG on 16/7/6.
 */

function register_onclick(){
    //参数提取
    var user_name = $("#ip_lgUserName").val();
    var user_pwd = $("#ip_lgUserPwd").val();
    var user_cpwd = $("#ip_lgUserCPwd").val();
    var user_ccode = $("#ip_lgUserCCode").val();
    if(user_name == null || user_name == ""){
        ShowNormalDialog("check-dialog","用户名不能为空!");
        return;
    }
    if(user_pwd == null || user_pwd == ""){
        ShowNormalDialog("check-dialog","用户密码不能为空!");
        return;
    }
    if(user_cpwd == null || user_cpwd == ""){
        ShowNormalDialog("check-dialog","用户确认密码不能为空!");
        return;
    }
    if(user_ccode == null || user_ccode == ""){
        ShowNormalDialog("check-dialog","请填写验证码!");
        return;
    }
    if(user_cpwd != user_pwd){
        ShowNormalDialog("check-dialog","两次填写的密码一致!");
        return;
    }
    if(user_name.toString().length<8 || user_name.toString().length>24){
        ShowNormalDialog("check-dialog","用户的账户长度要在8-24位字符之间!");
        return;
    }
    if(user_pwd.toString().length<8 || user_pwd.toString().length>32){
        ShowNormalDialog("check-dialog","用户的密码长度要在8-32位字符之间!");
        return;
    }
    //开始进行post请求
    $.post(
        "/user/register.do",
        {USER_ACCT : user_name , USER_PWD : user_pwd , USER_CCODE : user_ccode},
        function(data){
            if(data != "用户注册成功"){
                ShowNormalDialog("check-dialog",data);
            }else {
                //成功之后刷新验证码
                refresh_validation('ip_lgUserCCodeFromService');
                console.log("注册成功!");
            }
        });
}

function refresh_validation(img_box_id) {
    //加随机后缀，防止图像不刷新
    $("#"+img_box_id).attr('src','/getCode.do?'+Math.random());
}
