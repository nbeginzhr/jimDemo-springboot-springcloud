
var verDate = new Date();
verDate.setHours(0);
verDate.setMinutes(0);
verDate.setSeconds(0);
verDate.setMilliseconds(0);

var require = {
    waitSeconds: 600,
    urlArgs: 'ver='+ verDate.getTime() ,
    map: {
        '*': {
            'css': appName + 'js/css.min.js'
        }
    },
    paths: {
        'jquery': appName + 'js/jquery-1.11.1',
        'vue': appName + 'js/vue.min',
        'alert': appName + 'js/jquery.alert',
        'vue2':appName + 'js/vue2'
    },
    shim: {

    }
};