package id.sch.smktelkom_mlg.privateassignment.xirpl131.showmovie;

import java.io.Serializable;

/**
 * Created by LENOVO Z40-75 on 08/05/2017.
 */

public class HomeListItem implements Serializable{

    private String imageUrl;
    private String head;
    private String desc;


    public HomeListItem( String imageUrl, String head, String desc) {
        this.imageUrl = imageUrl;
        this.head = head;
        this.desc = desc;
    }
    public String getImageUrl() {return imageUrl;}

    public String getHead() {
        return head;
    }

    public String getDesc() {
        return desc;
    }

}
