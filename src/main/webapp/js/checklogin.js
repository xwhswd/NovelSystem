function getUser(){
    let user;
    $.get("/read/user?method=checkLogin",
        function (data){
            if (data.isOk){
                let dataObj = data.t;
                $('.login').css('display','none');
                $('.user-message,.user-avatar').css('display','block');
                $('.user-wrap p').css('display','none');
                user=data.t
            }else{
                user=null;
            }
        },'json'
    )
    return user;
}