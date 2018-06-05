require(['jquery','vue2'], function ($, vue) {
    var url = 'ws://localhost:9090';
    var socket;

    // 登录
    $('#login').on('click',function () {
       initWebSocket();
    });

    function initWebSocket() {
        var username = $('#username').val();
        var password = $('#password').val();
        var loginUrl = url + '?username=' + username + '&password=' + password;
        socket = new WebSocket(loginUrl);

        socket.onerror = function (e) {
            console.log("webSocket 连接错误！",e);
        }

        socket.onopen = function (e) {
            console.log('socket 连接打开',e);
        }

        socket.onmessage = function (e) {
            // console.log('收到群发数据--->',e);
            console.log('收到群发data--->',e.data);
        }

        socket.onclose = function(e){
           console.log('webSocket关闭*************************');
        };
    }



    $('#sendGroup').on('click',function () {
        var createTime = new Date().getTime();
        var username = $('#username').val();
        var content = "hello,我是" + username
        ;
        var msg = "{\"from\": \"1feLHON0SP2L\",\"createTime\":"+createTime+",\"cmd\":11,\"group_id\":\"0fNL5OP0S92B\",\"chatType\":\"1\",\"msgType\":\"0\",\"content\": \""+content+"\"}";
        sendGroup(msg);
    })

    function sendGroup(content) {
        socket.send(content);
    }



})