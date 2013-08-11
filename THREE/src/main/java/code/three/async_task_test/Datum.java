package code.three.async_task_test;

/**
 * Created by qiqicode on 13-8-11.
 */
public class Datum {
    int id;
    String title;

    public Datum(int id, String title)
    {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
