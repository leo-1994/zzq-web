// 扩展数组的contains方法
Array.prototype.contains = function (val) {
    for (let i = 0; i < this.length; i++) {
        if (this[i] === val) {
            return true;
        }
    }
    return false;
};
// 将UTC时间转换成本地时间YYYY-MM-DD hh:mm:ss格式
function formatUtctime(utctime) {
    var unixtimestamp = new Date(utctime);
    var year = 1900 + unixtimestamp.getYear();
    var month = "0" + (unixtimestamp.getMonth() + 1);
    var date = "0" + unixtimestamp.getDate();
    var hour = "0" + unixtimestamp.getHours();
    var minute = "0" + unixtimestamp.getMinutes();
    var second = "0" + unixtimestamp.getSeconds();
    return year + "-" + month.substring(month.length - 2, month.length) + "-" + date.substring(date.length - 2, date.length)
        + " " + hour.substring(hour.length - 2, hour.length) + ":"
        + minute.substring(minute.length - 2, minute.length) + ":"
        + second.substring(second.length - 2, second.length);
}