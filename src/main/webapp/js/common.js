/* 鼠标移动显示菜单列表 */
$('#show-allMenu').mouseover(function(){
	$('.menu-list').css('display','block');
})
$('.menu-list').mouseout(function(){
	$(this).css('display','none');
})
$('.menu-list').mouseover(function(){
	$(this).css('display','block');
})
/* 登录框事件 */
function login(event){
	this.id = $(event).attr('id')
	if(this.id === 'login'){
		$('.login').css('display','block')
		$('.login-box input[type="submit"]').attr('value','登录')
		$('#username').unbind('blur',verifyUsername)
		$('#login_btn').unbind('click',registerClick)
		$('#login_btn').bind('click',loginClick)
		$('.tips p')[0].childNodes[0].nodeValue = '登录即代表同意';
	}else{
		// 注册事件
		$('.login').css('display','block')
		$('#username').bind('blur',verifyUsername)
		$('.login-box input[type="submit"]').attr('value','注册');
		$('.tips p')[0].childNodes[0].nodeValue = '注册即代表同意';
		$('#login_btn').unbind('click',loginClick)
		$('#login_btn').bind('click',registerClick)
		// $('.tips p').text($('.tips p').text().replace("登录","注册"))
	}
}

// 屏蔽搜索框默认回车事件
$(document).on("keypress", ".search-wrap form", function(event) { 
    return event.keyCode != 13;
});
// 回车绑定搜索按钮
function search() {
  if(event.keyCode==13) {
    $('#search-btn').click()
  }
}

// 关闭登录框 
$('.close').click(function(){
	$('.login').css('display','none');
})

function verifyUsername(){
	$.get(
		'http://localhost:8080/read/user?method=verifyUsername', {
			username: $('#username').val()
		}, function (data) {
			if (data.isOk) {
				alert(data.mess)
			}
		}, 'json');
}

// // 登录框
// $('#login_btn').click()

function loginClick(event){
	event.preventDefault();
	var username = $('#username').val();
	var password = $('#userpwd').val();
	var btn = event.currentTarget.value;
	if (username!= "" && password !="") {
		var reg = /^[A-z]\w{5,8}/g
		if(!reg.test(password)){
			alert("密码必须以字母开头,长度6-8位！")
			return;
		}
	}else{
		alert("用户名或密码不许为空！")
	}
	$.get('http://localhost:8080/read/user?method=login',
		{
			username:username,
			password:password
		},function (data) {
			alert(data.mess)
			if (!data.isOk){
				return false;
			}
			$('.login').css('display','none');
			$('.user-message,.user-avatar').css('display','block');
			$('.user-wrap p').css('display','none');
		},'json'
	)
}

function registerClick(event){
	event.preventDefault();
	var username = $('#username').val();
	var password = $('#userpwd').val();
	var btn = event.currentTarget.value;
	if (username!= "" && password !="") {
		var reg = /^[A-z]\w{5,8}/g
		if(!reg.test(password)){
			alert("密码必须以字母开头,长度6-8位！")
			return;
		}
	}else{
		alert("用户名或密码不许为空！")
	}
	$.get('http://localhost:8080/read/user?method=register',
		{
			username:username,
			password:password
		},function (data) {
			alert(data.mess)
			if (!data.isOk){
				return false;
			}
			setTimeout(location.reload(),1000)
		},'json'
	)
}
// 分类ID与名称 (全部分类)
$.get('/read/category?method=listCategory',function (data){
		var index=0;
		for (let d of data.t) {
			$('.header-nav .menu-list ul').append(`
					<li><a href="./bookAll.html?cid=${encodeURI(d.category_name)}">${d.category_name}</a></li>
				`)
			$('.books-classify .tags').eq(0).append(`
						<span id=${d.id} class=${index++}>${d.category_name}</span>
					`)
		}
		// 完本页面的分类获取，暂不弄分类功能
		if($('.books-classify').length != 0){
			$.each(data.data,(i,value)=>{
				$('.books-classify .tags').eq(0).append(`
						<span id=${value.id} class=${i==0 ? "active":''}>${value.title}</span>
					`)
			})
		}
},'json')

/**
 * 获取URL参数
 	参考 https://blog.csdn.net/suyu_happy/article/details/78643005
 */
function getUrlParam(name){
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg);  //匹配目标参数
	if (r!=null) return decodeURI(r[2]); return null; //返回参数值
}

// 蒙版显示隐藏
function setMask(state="block"){
	$('.mask').css('display',state);
}

// 点击搜索按钮
$('#search-btn').click(function(){
	window.open(`./bookAll.html?search=${encodeURI($('#search-input').val())}`,"_self")
})

// 更改主样式颜色
// $(":root").css('--main-color','pink')
