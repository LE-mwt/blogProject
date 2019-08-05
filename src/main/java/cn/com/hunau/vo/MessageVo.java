package cn.com.hunau.vo;

public class MessageVo {
    //与我有关的文章
    private ArticleVo aboutMeArticles;

    //年
    private String year;
    //月
    private String month;
    //日
    private String day;
    //分秒
    private String min;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public ArticleVo getAboutMeArticles() {
        return aboutMeArticles;
    }

    public void setAboutMeArticles(ArticleVo aboutMeArticles) {
        this.aboutMeArticles = aboutMeArticles;
    }

    //
    @Override
    public int hashCode() {
        //修改hashcode
        return this.getAboutMeArticles().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof MessageVo)) {
            return false;
        }
        MessageVo m = (MessageVo) obj;//向下转型
        //id相同则任务是同一个对象
        return this.getAboutMeArticles().equals(m.getAboutMeArticles());
    }
}
