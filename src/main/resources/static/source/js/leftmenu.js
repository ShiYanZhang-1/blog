$(function () {
    //加载日志博客类别
    $.get("/blogType/typeList",function (data) {
        var blogTypeList = $("#blogTypeList");
        //循环遍历json数组
        $(data).each(function () {
            var li = "<li><span><a href='/index?typeId="+this.id+"'>"+this.typeName+" ("+this.blogCount+")</a></span></li>";
            //将每一个li标签追加到ul标签
            $(blogTypeList).append(li);
        });
    },"json");


    //加载日志博客日期和数量
    $.get("/blog/blogDateList",function (data) {
        var dateList = $("#dateList");
        //循环遍历json数组
        $(data).each(function () {
            var li = "<li><span><a href=/index?releaseDateStr="+this.releaseDateStr+">"+this.releaseDateStr+"("+this.blogCount+")</a></span></li>";
            //将每一个li标签追加到ul标签
            $(dateList).append(li);
        });
    },"json");
})