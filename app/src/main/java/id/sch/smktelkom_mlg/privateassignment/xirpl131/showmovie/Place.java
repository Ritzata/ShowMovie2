package id.sch.smktelkom_mlg.privateassignment.xirpl131.showmovie;

import com.orm.SugarRecord;

/**
 * Created by LENOVO Z40-75 on 14/05/2017.
 */

public class Place extends SugarRecord {
    String title;
    String description;
    String urlgambar;

    public Place(){

    }

    public Place(String title, String description, String urlgambar){
        this.title = title;
        this.description = description;
        this.urlgambar = urlgambar;
    }
}
